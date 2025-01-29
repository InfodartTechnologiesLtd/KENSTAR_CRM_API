package com.infodart.kenstar_crm.dto;

public class ResponseDto<T> {

	private String statusCode; 
    private String status; // e.g., "success" or "error"
    private String message; // Informational message
    private T data; // Response data of generic type
    private String error; // Optional error details (if applicable)

    // Constructors
    public ResponseDto() {
    	super();
    }

    public ResponseDto(String statusCode,String status, String message, T data, String error) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    // Static methods for convenience
    public static <T> ResponseDto<T> success(String statusCode, String message, T data) {
        return new ResponseDto<>(statusCode, "success", message, data, null);
    }

    public static <T> ResponseDto<T> error(String statusCode, String message, String error) {
        return new ResponseDto<>(statusCode, "error", message, null, error);
    }

    // Getters and Setters
    
    
    
    public String getStatus() {
        return status;
    }

    public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
