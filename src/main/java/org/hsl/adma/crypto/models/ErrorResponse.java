package org.hsl.adma.crypto.models;

public class ErrorResponse {
    private String type;
    private Long httpStatus;
    private Long statusCode;
    private String cause;
    
    public ErrorResponse(String type, Long httpStatus, Long statusCode, String cause) {
    	this.type = type;
    	this.httpStatus = httpStatus;
    	this.statusCode = statusCode;
    	this.cause = cause;
    }
    
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getHttpStatus() {
		return httpStatus;
	}
	
	public void setHttpStatus(Long httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public Long getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
    
    
}
