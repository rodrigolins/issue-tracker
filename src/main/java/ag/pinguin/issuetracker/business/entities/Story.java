package ag.pinguin.issuetracker.business.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("S")
public class Story extends Issue {
	
	@NotNull(message = "Estimation cannot be null")
	@Column(name = "STORY_ESTIMATION")
	private Integer estimation;
	
	@NotNull(message = "Status cannot be null")
	@Column(name = "STORY_STATUS")
	private StoryStatus status;
	
//	private Calendar assignedDate;
	
	public Story() { }
	
	public Story(Long id, String title, String description, Developer developer, 
			Integer estimation, StoryStatus status) {
		super(title, description, developer);
		this.estimation = estimation;
		this.status = status;
	}
	
	public Integer getEstimation() {
		return estimation;
	}
	
	public void setEstimation(Integer estimation) {
		this.estimation = estimation;
	}
	
	public StoryStatus getStatus() {
		return status;
	}
	
	public void setStatus(StoryStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Story [estimation=" + estimation + ", status=" + status
				+ ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription()
				+ ", getCreationDate()=" + getCreationDate()
				+ ", getDeveloper()=" + getDeveloper() + "]";
	}

}