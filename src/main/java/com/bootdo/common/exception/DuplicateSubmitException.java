package com.bootdo.common.exception;

public class DuplicateSubmitException extends BDException {

	/**
	 * 重复提交异常
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateSubmitException(String msg) {
		super(msg);

	}

	public DuplicateSubmitException(String msg, int code, Throwable e) {
		super(msg, code, e);

	}

	public DuplicateSubmitException(String msg, int code) {
		super(msg, code);

	}

	public DuplicateSubmitException(String msg, Throwable e) {
		super(msg, e);

	}
	
	

}
