package aws.services.cloudsearchv2;

/**
 * This Exception is thrown when there is a internal server error. This might indicate a temporary situation and retrying might help.
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class AmazonCloudSearchInternalServerException extends Exception {
	private static final long serialVersionUID = -6321292963167579340L;

	public AmazonCloudSearchInternalServerException(Throwable t) {
		super(t);
	}	
	
	public AmazonCloudSearchInternalServerException(String message, Throwable t) {
		super(message, t);
	}	
	
	public AmazonCloudSearchInternalServerException(String message) {
		super(message);
	}
}
