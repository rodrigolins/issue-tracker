package ag.pinguin.issuetracker.web.controllers.forms;

import ag.pinguin.issuetracker.business.entities.BugPriority;
import ag.pinguin.issuetracker.business.entities.BugStatus;
import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.entities.StoryStatus;

public class IssueForm {
	
	public enum FormType {

		BUG("Bug"), STORY("Story");
		
		private String name;
		
		private FormType(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
	}
	
	private FormType formType;

	// From Issue
	private String title;
	private String description;
	private Developer developer;
	
	// From Bug
	private BugStatus bugStatus;
	private BugPriority bugPriority;
	
	// From Story
	private Integer estimation;
	private StoryStatus storyStatus;
	public FormType getFormType() {
		return formType;
	}
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public BugStatus getBugStatus() {
		return bugStatus;
	}
	public void setBugStatus(BugStatus bugStatus) {
		this.bugStatus = bugStatus;
	}
	public BugPriority getBugPriority() {
		return bugPriority;
	}
	public void setBugPriority(BugPriority bugPriority) {
		this.bugPriority = bugPriority;
	}
	public Integer getEstimation() {
		return estimation;
	}
	public void setEstimation(Integer estimation) {
		this.estimation = estimation;
	}
	public StoryStatus getStoryStatus() {
		return storyStatus;
	}
	public void setStoryStatus(StoryStatus storyStatus) {
		this.storyStatus = storyStatus;
	}
	
}
