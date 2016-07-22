package ag.pinguin.issuetracker.business.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BugPriority {
	
	C("Critical"), A("Major"), I("Minor");
	
	private String name;
	
	private BugPriority(String name){
		this.name = name;
	}
	
	@JsonValue
	public String getName(){
		return name;
	}
	
}
