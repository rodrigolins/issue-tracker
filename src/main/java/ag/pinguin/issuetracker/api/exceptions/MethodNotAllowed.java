package ag.pinguin.issuetracker.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This exception is to be used when a method that is not allowed is
 * called.
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see RestController
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MethodNotAllowed extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1l;
	
	/**
	 * Instantiates a new method not allowed.
	 */
	public MethodNotAllowed() {};
	
	/**
	 * Instantiates a new method not allowed.
	 *
	 * @param message the error message
	 */
	public MethodNotAllowed(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new method not allowed.
	 *
	 * @param message the error message
	 * @param cause the cause of the error
	 */
	public MethodNotAllowed(String message, Throwable cause) {
		super(message, cause);
	}

}
