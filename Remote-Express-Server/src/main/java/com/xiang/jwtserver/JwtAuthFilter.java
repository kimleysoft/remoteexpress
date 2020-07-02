package com.xiang.jwtserver;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

public class JwtAuthFilter extends AuthenticatingFilter {
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(JWTAuth.TOKENHEADER);
		if (ObjectUtils.isEmpty(token)) {
			token = httpServletRequest.getParameter(JWTAuth.TOKENHEADER);
		}
		if (!ObjectUtils.isEmpty(token)) {
			return createJWTToken(token);
		}
		return null;
	}

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			return true;
		}
		return super.onPreHandle(request, response, mappedValue);
	}

	@Override
	protected AuthenticationToken createToken(String username, String password, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(JWTAuth.TOKENHEADER);
		return createJWTToken(token);
	}
	private JWTToken createJWTToken(String token) {
		if (!ObjectUtils.isEmpty(token)) {
			JWTToken jWTToken=new JWTToken(token);
			return jWTToken;
		}
		return null;
	}
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(createToken(request, response));
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		if (!subject.isAuthenticated()) {
			return false;
		}
		// 拥有其中一个角色就算成立
		String[] rolesArray = (String[]) mappedValue;
		if (rolesArray == null || rolesArray.length == 0) {
			return true;
		}
		for (int i = 0; i < rolesArray.length; i++) {
			if (subject.hasRole(rolesArray[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			super.postHandle(request, response);
			return;
		}
		JWTToken jwtToken = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Object principal = subject.getPrincipal();
			if (principal instanceof JWTToken) {
				jwtToken = (JWTToken) principal;
			}
		}
		if (jwtToken == null) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			if (httpServletRequest != null) {
				String token = httpServletRequest.getHeader(JWTAuth.TOKENHEADER);
				if (ObjectUtils.isEmpty(token)) {
					token = httpServletRequest.getParameter(JWTAuth.TOKENHEADER);
				}
				try {
					jwtToken = createJWTToken(token);
				}catch(Exception ex) {
					ex.printStackTrace();
					return;
				}
				
			}
		}
		if (jwtToken != null) {
			try {
				String token = JWTAuth.refreshToken(jwtToken);
				if (!StringUtils.isEmpty(token)) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setHeader(JWTAuth.TOKENHEADER, token);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		super.postHandle(request, response);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		return false;
	}

}
