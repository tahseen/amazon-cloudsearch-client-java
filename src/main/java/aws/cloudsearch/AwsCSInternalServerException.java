package aws.cloudsearch;

/**
 * This Exception is thrown when there is a internal server error. This might indicate a temporary situation and retrying might help.
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class AwsCSInternalServerException extends Exception {
	private static final long serialVersionUID = -6321292963167579340L;

	public AwsCSInternalServerException(Throwable t) {
		super(t);
	}	
	
	public AwsCSInternalServerException(String message, Throwable t) {
		super(message, t);
	}	
	
	public AwsCSInternalServerException(String message) {
		super(message);
	}
}
