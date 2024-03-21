package com.medrecord.responsedto;

import org.springframework.stereotype.Service;

@Service
public class ServiceResponse 
{
	public boolean status;
	public String message;
	
	public ServiceResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ServiceResponse()
	{
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
