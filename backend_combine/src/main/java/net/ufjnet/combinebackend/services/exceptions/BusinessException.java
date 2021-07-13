package net.ufjnet.combinebackend.services.exceptions;

public class BusinessException extends RuntimeException {
	public static final long serialVersionUID;

	static {
		serialVersionUID = 1L;
	}

	public BusinessException(String msg) {
		super(msg);
	}
}
