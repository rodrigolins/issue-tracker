package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enum class represents a {@link BugPriority}. 
 * 
 * A {@link BugPriority} is the enum representation of
 * the current state of the bug priority.  
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see Bug
 */
public enum BugPriority {
	
	/** The {@link Bug} priority is critical. */
	C("Critical"), 
	/** The {@link Bug} priority is major. */
	A("Major"),
	/** The {@link Bug} priority is minimal. */
	I("Minor");
	
	/** The {@link Bug} priority name. */
	private String name;
	
	/**
	 * Instantiates a new bug priority.
	 * 
	 * This constructor is private and will always used when creating
	 * a new {@link BugPriority}.
	 *
	 * @param name   the {@link String} name of the {@link Bug} priority. 
	 */
	private BugPriority(String name){
		this.name = name;
	}
	
	/**
	 * Gets the {@link Bug} priority name.
	 *
	 * @return the {@link String} name.
	 */
	@JsonValue
	public String getName(){
		return name;
	}
	
}
