package com.xiang.restserver;

public enum UploadErrorType {
	empty_file(0),
	empty_port(1),
	error_port(2),
	duplicate_port(3),
	illegal_port(4),
	illegal_ip(5),
	illegal_localip(6),
	empty_group_alias(7),
	empty_remark(8),
	empty_nick(9),
	empty_username(10),
	empty_password(11),
	duplicate_username(12), 
	permission_maxnum(13),
	over_size_200(14);
	int type;
	
	private UploadErrorType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	@Override
	public String toString() {
		return this.type +"";
	}


}
