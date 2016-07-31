package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enum class represents a {@link BugStatus}. 
 * 
 * A {@link BugStatus} is the enum representation of
 * the current state of a bug status.  
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see Bug
 */
public enum BugStatus {
	
	/** The {@link Bug} status is new. */
	N("New"),
	/** The {@link Bug} status is verified. */
	V("Verified"), 
	/** The {@link Bug} status is resolved. */
	R("Resolved");
	
	/** The {@link Bug} status name. */
	private String name;
	
	/**
	 * Instantiates a new bug staus.
	 * 
	 * This constructor is private and will always used when creating
	 * a new {@link BugStatus}.
	 *
	 * @param name   the {@link String} name of the {@link Bug} status. 
	 */
	private BugStatus(String name){
		this.name = name;
	}
	
	/**
	 * Gets the {@link Bug} status name.
	 *
	 * @return the {@link String} name.
	 */
	@JsonValue
	public String getName(){
		return name;
	}
}
