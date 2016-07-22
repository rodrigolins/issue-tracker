package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StoryStatus {
	
	NEW_STORY("New"), ESTIMATED("Estimated"), COMPLETE("Complete");
	
	private String name;
	
	private StoryStatus(String name){
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
}
