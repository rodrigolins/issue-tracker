package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BugStatus {
	
	N("New"), V("Verified"), R("Resolved");
	
	private String name;
	
	private BugStatus(String name){
		this.name = name;
	}
	
	@JsonValue
	public String getName(){
		return name;
	}
}
