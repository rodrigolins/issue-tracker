package ag.pinguin.issuetracker.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This exception is to be used when a resource is not found.
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see RestController
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1l;
	
	/**
	 * Instantiates a new resource not found exception.
	 */
	public ResourceNotFoundException() {};
	
	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the error message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the error message
	 * @param cause the error cause
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
