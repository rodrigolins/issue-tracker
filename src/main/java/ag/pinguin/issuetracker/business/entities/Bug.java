package ag.pinguin.issuetracker.business.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("B")
public class Bug extends Issue {

	@NotNull
	@Column(name = "BUG_STATUS")
	private BugStatus status;
	
	@NotNull
	@Column(name = "BUG_PRIORITY")
	private BugPriority priority;
	
	public BugStatus getStatus() {
		return status;
	}
	public void setStatus(BugStatus status) {
		this.status = status;
	}
	public BugPriority getPriority() {
		return priority;
	}
	public void setPriority(BugPriority priority) {
		this.priority = priority;
	}
	
	public Bug() { }
	
	public Bug(String title, String description, Developer developer, 
			BugStatus status, BugPriority priority) {
		super(title, description, developer);
		this.status = status;
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Bug [status=" + status + ", priority=" + priority
				+ ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription()
				+ ", getCreationDate()=" + getCreationDate()
				+ ", getDeveloper()=" + getDeveloper() + "]";
	}
	
}
