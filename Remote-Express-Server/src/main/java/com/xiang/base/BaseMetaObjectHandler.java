package com.xiang.base;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
public class BaseMetaObjectHandler implements MetaObjectHandler{

	@Override
	public void insertFill(MetaObject metaObject) {
		Date datetime=new Date();
		this.setInsertFieldValByName("gmtCreate", datetime, metaObject);
		this.setInsertFieldValByName("gmtModified", datetime, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		System.out.println("updateFill");
		this.setUpdateFieldValByName("gmtModified", new Date(), metaObject);
	}

}
