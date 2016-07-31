package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enum class represents a {@link StoryStatus}. 
 * 
 * A {@link StoryStatus} is the enum representation of
 * the current status state of a story.  
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see Bug
 */
public enum StoryStatus {
	
	/** The {@link Story} status is new. */
	NEW_STORY("New"), 
	/** The {@link Story} status is estimated. */
	ESTIMATED("Estimated"),
	/** The {@link Story} status is complete. */
	COMPLETE("Complete");
	
	/** The {@link Story} status name. */
	private String name;
	
	/**
	 * Instantiates a new story staus.
	 * 
	 * This constructor is private and will always used when creating
	 * a new {@link StoryStatus}.
	 *
	 * @param name   the {@link String} name of the {@link Story} status. 
	 */
	private StoryStatus(String name){
		this.name = name;
	}
	
	/**
	 * Gets the {@link Story} status name.
	 *
	 * @return the {@link String} name.
	 */
	@JsonValue
	public String getName() {
		return name;
	}
	
}
