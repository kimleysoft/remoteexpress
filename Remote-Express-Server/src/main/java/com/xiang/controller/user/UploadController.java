package com.xiang.controller.user;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.po.Remote;
import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.User;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.constants.RegExpFilter;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.LoginToken;
import com.xiang.restserver.UploadError;
import com.xiang.restserver.UploadErrorType;
import com.xiang.server.RemoteServer;
import com.xiang.server.SysGroupServer;
import com.xiang.server.UserServer;

@RestController
@RequestMapping(value = "/admin/upload")
public class UploadController {
	@Resource
	private UserServer  userServer;
	@Resource
	private RemoteServer remoteServer;
	@Resource
	private IdService idService;
	@Resource
	private SysGroupServer sysGroupServer;

	private static Long MB_MAX = 1024 * 1024L;
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/user" , method = RequestMethod.POST)
	@ResponseBody
	public Object uploadUser(@LoginToken XAuthToken authToken,@RequestParam("file") MultipartFile file,
	HttpServletRequest request, HttpServletResponse response) {

		try {
			String filename = file.getOriginalFilename();
			if(!filename.endsWith(".xls") && !filename.endsWith(".xlsx")) {//验证文件后缀
				return ErrorCodes.UPLOAD_EXT_ERROR;
			}
			if(file.getSize() > MB_MAX) {
				return ErrorCodes.UPLOAD_OVER_SIZE;
			}
			InputStream inputStream = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			List<ExUser> list = new ArrayList<ExUser>();//用户数据
			List<String> unames = new ArrayList<String>();//不重复的用户名
			List<String> aliases = new ArrayList<String>();//用户组别名
			if(sheet.getLastRowNum() > 200) {//一次性最大不能超过200行
				return new UploadError(sheet.getLastRowNum() + 1, UploadErrorType.over_size_200);
			}
			for(int rowNum = 1;rowNum <= sheet.getLastRowNum(); rowNum++ ){//封装数据
				XSSFRow row = sheet.getRow(rowNum);
				ExUser u = new ExUser();
				row.getCell(0).setCellType(CellType.STRING);
				row.getCell(1).setCellType(CellType.STRING);
				row.getCell(2).setCellType(CellType.STRING);
				u.setNick(row.getCell(0).getStringCellValue());
				u.setUserName(row.getCell(1).getStringCellValue());
				u.setPassword(row.getCell(2).getStringCellValue());
				u.setGroupAlias(row.getCell(3).getStringCellValue());
				if(StringUtils.isEmpty(u.getNick())) {
					return new UploadError(rowNum, UploadErrorType.empty_nick);
				}
				if(StringUtils.isEmpty(u.getUserName())) {
					return new UploadError(rowNum, UploadErrorType.empty_username);
				}
				if(StringUtils.isEmpty(u.getPassword())) {
					return new UploadError(rowNum, UploadErrorType.empty_password);
				}
				if(unames.contains(u.getUserName())) {
					return new UploadError(rowNum, UploadErrorType.duplicate_username);
				}
				if(StringUtils.isEmpty(u.getGroupAlias())) {
					return new UploadError(rowNum, UploadErrorType.empty_group_alias);
				}
				if(!aliases.contains(u.getGroupAlias())) {
					aliases.add(u.getGroupAlias());
				}
				unames.add(u.getUserName());
				list.add(u);
			}
			if(unames.isEmpty()) {
				return new UploadError(-1, UploadErrorType.empty_file);
			}
			List<String> exp = userServer.isExistsUsers(unames);
			if(null != exp && !exp.isEmpty()) {
				return new UploadError(-1, UploadErrorType.duplicate_username);
			}
			//查询组别是否存在，不存在则存入新组别，返回所有的group的alias-id键值对
			Map<String, Long> groupMap = validAndAddGroups(aliases, 1);
			List<User> data = new ArrayList<>();
			Date now = new Date();
			for(ExUser exu : list) {
				User u = new User();
				u.setId(idService.genId());
				u.setDel(false);
				u.setUserName(exu.getUserName());
				u.setPassword(DigestUtils.md5Hex(exu.getPassword()));
				u.setNick(exu.getNick());
				u.setGmtCreate(now);
				u.setGmtModified(now);
				u.setGroupId(groupMap.get(exu.getGroupAlias()));
				u.setLoginOutFlag(false);
				data.add(u);
			}
			userServer.batchSave(data);
		} catch(APIException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return ErrorCodes.UPLOAD_ERROR;
		}
		return ErrorCodes.OK;
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/remote" , method = RequestMethod.POST)
	@ResponseBody
	public Object uploadRemote(@LoginToken XAuthToken authToken,@RequestParam("file") MultipartFile file,
	HttpServletRequest request, HttpServletResponse response) {
		try {
			String filename = file.getOriginalFilename();
			if(!filename.endsWith(".xls") && !filename.endsWith(".xlsx")) {//验证文件后缀
				return ErrorCodes.UPLOAD_EXT_ERROR;
			}
			if(file.getSize() > MB_MAX) {
				return ErrorCodes.UPLOAD_OVER_SIZE;
			}
			InputStream inputStream = file.getInputStream();
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			List<Integer> ports = new ArrayList<Integer>();//数据中不重复的port
			List<String> aliases = new ArrayList<String>();//用户组别名
			List<ExRemote> exdata = new ArrayList<ExRemote>();//封装后的数据
			
			if(sheet.getLastRowNum() > 200) {//一次性最大不能超过200行
				return new UploadError(sheet.getLastRowNum() + 1, UploadErrorType.over_size_200);
			}
			int rowNum = 0;
			Cell cell = null;
			Iterator<Row> iterator = sheet.rowIterator();
			while(iterator.hasNext()){//封装数据
				Row row = iterator.next();
				if(rowNum == 0) {
					rowNum ++;
					continue ;
				}
				ExRemote remote = new ExRemote();
				remote.setRemark(row.getCell(0).getStringCellValue());
				remote.setLocalip(row.getCell(1).getStringCellValue());
				try {
					remote.setLocalport(doubleToInteger(row.getCell(2).getNumericCellValue()));
				} catch (Exception e) {
					remote.setLocalport(Integer.valueOf(row.getCell(2).getStringCellValue()));
				}
				cell = row.getCell(3);
				if(cell != null) {
					cell.setCellType(CellType.STRING);
					remote.setClientType(getClientType(cell.getStringCellValue()));
				}else {
					remote.setClientType(1);
				}
				remote.setIp(row.getCell(4).getStringCellValue());
				try {
					remote.setPort(doubleToInteger(row.getCell(5).getNumericCellValue()));
				} catch (Exception e) {
					remote.setPort(Integer.valueOf(row.getCell(5).getStringCellValue()));
				}
				remote.setGroupAlias(row.getCell(6).getStringCellValue());
				cell = row.getCell(7);
				if(cell != null) {
					cell.setCellType(CellType.STRING);
					remote.setAccount(row.getCell(7).getStringCellValue());
				}
				cell = row.getCell(8);
				if(cell != null) {
					cell.setCellType(CellType.STRING);
					remote.setPassword(row.getCell(8).getStringCellValue());
				}
				//数据验证
				if(null == remote.getPort()) {
					return new UploadError(rowNum, UploadErrorType.empty_port);
				}
				if(ports.indexOf(remote.getPort()) > -1) {
					return new UploadError(rowNum, UploadErrorType.duplicate_port);
				}
				if(0 > remote.getPort().compareTo(33000) || remote.getPort().compareTo(34000) > 0) {
					return new UploadError(rowNum, UploadErrorType.illegal_port);
				}
				if(!RegExpFilter.validIp(remote.getIp())) {
					return new UploadError(rowNum, UploadErrorType.illegal_ip);
				}
				if(!RegExpFilter.validIp(remote.getLocalip())) {
					return new UploadError(rowNum, UploadErrorType.illegal_localip);
				}
				if(StringUtils.isEmpty(remote.getGroupAlias())) {
					return new UploadError(rowNum, UploadErrorType.empty_group_alias);
				}
				if(StringUtils.isEmpty(remote.getRemark())) {
					return new UploadError(rowNum, UploadErrorType.empty_remark);
				}
				if(!aliases.contains(remote.getGroupAlias())) {
					aliases.add(remote.getGroupAlias());
				}
				ports.add(remote.getPort());
				exdata.add(remote);
				rowNum ++;
			}
			if(null == ports || ports.isEmpty()) {
				return new UploadError(-1, UploadErrorType.empty_file);
			}
			List<Integer> exp = remoteServer.existPort(ports);
			if(null != exp && !exp.isEmpty()) {
				return new UploadError(-1, UploadErrorType.duplicate_port);
			}
			//查询组别是否存在，不存在则存入新组别，返回所有的group的alias-id键值对
			Map<String, Long> groupMap = validAndAddGroups(aliases, 2);
			List<Remote> data = new ArrayList<Remote>();//待存储的数据
			for(ExRemote r : exdata) {
				r.setId(idService.genId());
				r.setDel(false);
				r.setGmtCreate(new Date());
				r.setConneBreakFlag(false);
				r.setGroupid(groupMap.get(r.getGroupAlias()));
				Remote d = new Remote();
				BeanUtils.copyProperties(r, d);
				data.add(d);
			}
			remoteServer.batchAdd(data);
		} catch(APIException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			return ErrorCodes.UPLOAD_ERROR;
		}
		return ErrorCodes.OK;
	}
	
	private Integer getClientType(String clientType) {
		if("2".equals(clientType) || "SSH".equals(clientType))
			return 2;
		else if("3".equals(clientType) || "VNC".equals(clientType))
			return 3;
		return 1;
	}
	private Map<String, Long> validAndAddGroups(List<String> aliases, Integer flagtype) {
		Map<String, Long> groupMap = new HashMap<String, Long>();
		List<SysGroup> groups = sysGroupServer.getByAlias(aliases, flagtype);
		for(SysGroup g : groups) {
			if(aliases.indexOf(g.getAlias()) > -1) {
				aliases.remove(g.getAlias());
				groupMap.put(g.getAlias(), g.getId());
			}
		}
		List<SysGroup> records = new ArrayList<>();
		for(String alias : aliases) {
			SysGroup g = new SysGroup();
			g.setAlias(alias);
			g.setDel(false);
			g.setGmtCreate(new Date());
			g.setGmtModified(new Date());
			g.setId(idService.genId());
			g.setRemark(alias);
			g.setTypeflag(flagtype);
			records.add(g);
			groupMap.put(g.getAlias(), g.getId());
		}
		if(!records.isEmpty())
			sysGroupServer.batchAdd(records);
		groups.addAll(records);
		return groupMap;
	}
	
	private Integer doubleToInteger(Double source) {
		return source.intValue();
	}
	public class ExRemote extends Remote {
		private String groupAlias;

		public String getGroupAlias() {
			return groupAlias;
		}

		public void setGroupAlias(String groupAlias) {
			this.groupAlias = groupAlias;
		}

	}
	public class ExUser extends User {
		private String groupAlias;

		public String getGroupAlias() {
			return groupAlias;
		}

		public void setGroupAlias(String groupAlias) {
			this.groupAlias = groupAlias;
		}
	}
}
