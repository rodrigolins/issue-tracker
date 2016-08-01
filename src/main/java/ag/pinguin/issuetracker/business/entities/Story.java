package ag.pinguin.issuetracker.business.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * This class represents a {@link Story}. A {@link Story} is a
 * concrete class from {@link Issue}.  
 * 
 * @see Issue
 * 
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 */
@Entity
@DiscriminatorValue("S")
public class Story extends Issue {
	
	/** The {@link Integer} instance representation of the estimation value. */
	@NotNull(message = "Estimation cannot be null")
	@Column(name = "STORY_ESTIMATION")
	private Integer estimation;
	
	/** The {@link StoryStatus} instance representation of the {@link Story} status. */
	@NotNull(message = "Status cannot be null")
	@Column(name = "STORY_STATUS")
	private StoryStatus status;
	
	/**
	 * Default constructor of a {@link Story}.
	 */
	public Story() { }
	
	/**
	 * {@link Story} constructor that receives all parameters.
	 *
	 * @param title the {@link Story} title
	 * @param description the {@link Story} description
	 * @param developer the developer assigned to the {@link Story} instance
	 * @param estimation the {@link Story} estimation
	 * @param status the {@link Story} status
	 */
	public Story(String title, String description, Developer developer, 
			Integer estimation, StoryStatus status) {
		super(title, description, developer);
		this.estimation = estimation;
		this.status = status;
	}
	
	/**
	 * Gets the estimation value for a {@link Story}.
	 *
	 * @return the estimation
	 */
	public Integer getEstimation() {
		return estimation;
	}
	
	/**
	 * Sets the estimation value for a {@link Story}.
	 *
	 * @param a {@link Integer} representing the {@link Story} estimation.
	 */
	public void setEstimation(Integer estimation) {
		this.estimation = estimation;
	}
	
	/**
	 * Gets the {@link Story} status.
	 *
	 * @see StoryStatus
	 * @return the status of a {@link Story}.
	 */
	public StoryStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets {@link Story} the status.
	 *
	 * @see StoryStatus
	 * @param {@link StoryStatus} of a {@link Story}.
	 */
	public void setStatus(StoryStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((estimation == null) ? 0 : estimation.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		if (estimation == null) {
			if (other.estimation != null)
				return false;
		} else if (!estimation.equals(other.estimation))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	/**
	 * The {@link String} representation of the {@link Story}.
	 * 
	 * @see ag.pinguin.issuetracker.business.entities.Issue#toString()
	 */
	@Override
	public String toString() {
		return "Story [estimation=" + estimation + ", status=" + status
				+ ", id=" + getIssueId() + ", title=" + getTitle()
				+ ", description=" + getDescription()
				+ ", creationDate=" + getCreationDate()
				+ ", developer=" + getDeveloper() + "]";
	}

}