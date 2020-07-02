package com.xiang.restserver;

public class UploadError {
	int line;
	UploadErrorType type;
	
	public UploadError(int line, UploadErrorType type) {
		this.line = line;
		this.type = type;
	}

	public int getLine() {
		return line;
	}

	public UploadErrorType getType() {
		return type;
	}
	
}
