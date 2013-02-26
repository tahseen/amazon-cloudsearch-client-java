package com.bryght.services.cloudsearch;

/**
 * This Exception is thrown when there is a problem with the request.
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class AwsCSMalformedRequestException extends Exception {
	private static final long serialVersionUID = -8012139351879601142L;
	public String response;
	public String request;

	public AwsCSMalformedRequestException(Throwable t) {
		super(t);
	}	
	
	public AwsCSMalformedRequestException(String message, Throwable t) {
		super(message, t);
	}	
	
	public AwsCSMalformedRequestException(String message) {
		super(message);
	}

	public AwsCSMalformedRequestException(String message, String request, String response) {
		super(message);
		this.request = request;
		this.response = response;
	}
}
