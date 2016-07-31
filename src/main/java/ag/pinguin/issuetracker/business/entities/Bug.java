package ag.pinguin.issuetracker.business.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * This class represents a {@link Bug}. A {@link Bug} is a
 * concrete class from {@link Issue}.
 * 
 * @see Issue
 * 
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 */
@Entity
@DiscriminatorValue("B")
public class Bug extends Issue {

	/** The {@link BugStatus} instance representation of the {@link Bug} status. */
	@NotNull
	@Column(name = "BUG_STATUS")
	private BugStatus status;
	
	/** The {@link BugPriority} instance representation of the {@link Bug} priority. */
	@NotNull
	@Column(name = "BUG_PRIORITY")
	private BugPriority priority;
	
	/**
	 * Gets the {@link Bug} status.
	 * 
	 * @see BugStatus
	 * @return the {@link Bug} status.
	 */
	public BugStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the {@link Bug} status.
	 *
	 * @param status  the {@link Bug} status.
	 */
	public void setStatus(BugStatus status) {
		this.status = status;
	}
	
	/**
	 * Gets the {@link Bug} priority.
	 *
	 * @return the {@link Bug} priority.
	 */
	public BugPriority getPriority() {
		return priority;
	}
	
	/**
	 * Sets the {@link Bug} priority.
	 *
	 * @param priority  the {@link Bug} priority.
	 */
	public void setPriority(BugPriority priority) {
		this.priority = priority;
	}
	
	/**
	 * The default constructor.
	 */
	public Bug() { }
	
	/**
	 * The constructor that accepts all parameters to create
	 * a new {@link Bug}.
	 *
	 * @param title the title
	 * @param description the description
	 * @param developer the developer
	 * @param status the status
	 * @param priority the priority
	 */
	public Bug(String title, String description, Developer developer, 
			BugStatus status, BugPriority priority) {
		super(title, description, developer);
		this.status = status;
		this.priority = priority;
	}
	
	/**
	 * The {@link String} representation of a {@link Bug}.
	 * @see ag.pinguin.issuetracker.business.entities.Issue#toString()
	 */
	@Override
	public String toString() {
		return "Bug [status=" + status + ", priority=" + priority
				+ ", id=" + getId() + ", title=" + getTitle()
				+ ", description=" + getDescription()
				+ ", creationDate=" + getCreationDate()
				+ ", developer=" + getDeveloper() + "]";
	}
	
}
