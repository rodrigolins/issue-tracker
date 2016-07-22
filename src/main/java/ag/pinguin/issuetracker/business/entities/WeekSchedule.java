package ag.pinguin.issuetracker.business.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WeekSchedule {
	
	@Column(name = "WEEK_INITIAL_DAY")
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar initialDate;
	
	@Column(name = "WEEK_FINAL_DAY")
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar finalDate;
	
	private Map<Developer, List<Story>> plan;
	
	public WeekSchedule() {
		plan = new Hashtable<Developer, List<Story>>();
	}

	public Calendar getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Calendar initialDate) {
		this.initialDate = initialDate;
	}

	public Calendar getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Calendar finalDate) {
		this.finalDate = finalDate;
	}
	
	/**
	 * This method is used to add a story to the weekly developer schedule.
	 * 
	 * 
	 * @param developer
	 * @param story
	 * @return true is the story is added successfully and false if not
	 */
	public boolean addDeveloperStories(Developer developer, Story story) {
		List<Story> stories = plan.get(developer);
		if(stories == null) {
			stories = new ArrayList<Story>();
			stories.add(story);
			plan.put(developer, stories);
			return true;
		}
		Integer totalEspecification = 0;
		for(Story aStory : stories) {
			totalEspecification += aStory.getEstimation();
		}
		
//		if(totalEspecification >=)
		
		return false;
		
	}
	
	
	
//	private List<Developer> developers;
	

}
