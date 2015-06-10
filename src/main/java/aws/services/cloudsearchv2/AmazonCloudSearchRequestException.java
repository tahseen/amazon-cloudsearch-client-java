package aws.services.cloudsearchv2;

import com.amazonaws.util.json.JSONObject;


/**
 * This Exception is thrown when there is a problem with the request.
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class AmazonCloudSearchRequestException extends Exception {
	private static final long serialVersionUID = -8012139351879601142L;
	public String response;
	public String request;

	public AmazonCloudSearchRequestException(Throwable t) {
		super(t);
	}	
	
	public AmazonCloudSearchRequestException(String message, Throwable t) {
		super(message, t);
	}	
	
	public AmazonCloudSearchRequestException(String message) {
		super(message);
	}

	public AmazonCloudSearchRequestException(String request, String response) {
	    super(response);
	    this.request = request;
		this.response = response;
	}
}
