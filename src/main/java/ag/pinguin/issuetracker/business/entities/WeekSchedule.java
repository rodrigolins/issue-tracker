package ag.pinguin.issuetracker.business.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WeekSchedule {
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar initialDate;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar finalDate;
	
	private Map<Developer, List<Story>> week;
	
	/**
	 * This class creates a proposed weekly work plan for a {@link Developer}.
	 * 
	 * The first work day is always a Monday and the last a Friday.
	 * The first Monday is from the current week.
	 * 
	 * This constructor accepts a Integer that is the week to be created.
	 * 0 means the current week. 1 represents the next week and so on.
	 * 
	 * @param weeksFromToday represent the week that should be created.
	 */
	public WeekSchedule(Integer weeksFromToday) {
		week = new Hashtable<Developer, List<Story>>();
		Calendar date = Calendar.getInstance();
		
		date.add(Calendar.DATE, (7 * weeksFromToday));
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		this.initialDate = (Calendar) date.clone();
		
		date.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		this.finalDate = (Calendar) date.clone();
	}

	public Calendar getInitialDate() {
		return initialDate;
	}

	public Calendar getFinalDate() {
		return finalDate;
	}
	
	public Map<Developer, List<Story>> getWeek() {
		return week;
	}
	
	public List<Story> getStoriesFromDeveloper(Developer developer) {
		return week.get(developer);
	}
	
	/**
	 * This method is used to add a story to the weekly developer schedule.
	 * 
	 * 
	 * @param developer
	 * @param story
	 */
	public void addStoryToDeveloper(Developer developer, Story story) {
		List<Story> stories = week.get(developer);
		if(stories == null) {
			stories = new ArrayList<Story>();
			stories.add(story);
			week.put(developer, stories);
		} else {
			stories.add(story);
			week.put(developer, stories);
		}
	}
}
