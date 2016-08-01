package ag.pinguin.issuetracker.business.entities;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ag.pinguin.issuetracker.api.controllers.DeveloperRESTController;
import ag.pinguin.issuetracker.api.controllers.IssueRESTController;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class represents a weekly plan regarding the {@link Developer} and {@link Story}.
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 */
public class WeekScheduleResult {
	
	/** The initial date. */
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar initialDate;
	
	/** The final date. */
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Calendar finalDate;
	
	/** The week. */
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
	public WeekScheduleResult(Integer weeksFromToday) {
		week = new Hashtable<Developer, List<Story>>();
		Calendar date = Calendar.getInstance();
		
		date.add(Calendar.DATE, (7 * weeksFromToday));
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		this.initialDate = (Calendar) date.clone();
		
		date.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		this.finalDate = (Calendar) date.clone();
	}

	/**
	 * Gets the initial date.
	 *
	 * @return the initial date
	 */
	public Calendar getInitialDate() {
		return initialDate;
	}

	/**
	 * Gets the final date.
	 *
	 * @return the final date
	 */
	public Calendar getFinalDate() {
		return finalDate;
	}
	
	/**
	 * Gets the week.
	 *
	 * @return the week
	 */
	public Set<Developer> getWeek() {
		Set<Developer> developersKeys = week.keySet();
		
		for(Developer developer : developersKeys) {
			List<Story> stories = week.get(developer);
			if(stories != null) {
				for(Story s : stories) {
					developer.add(linkTo(methodOn(IssueRESTController.class).retrieveStories(s.getIssueId())).withRel("story"));
				}
			}
			developer.add(linkTo(methodOn(DeveloperRESTController.class).retrieveDevelopers(developer.getDeveloperId())).withSelfRel());
		}
		return developersKeys;
	}
	
	/**
	 * Gets the stories from developer.
	 *
	 * @param developer the developer
	 * @return the stories from developer
	 */
	public List<Story> getStoriesFromDeveloper(Developer developer) {
		return week.get(developer);
	}
	
	/**
	 * This method is used to add a story to the weekly developer schedule.
	 *
	 * @param developer the developer
	 * @param story the story
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<Developer> developerKeys = week.keySet();
		for(Developer d : developerKeys) {
			sb.append("Developer: " + d);
			List<Story> stories = week.get(d);
			for(Story s : stories) {
				sb.append("Story: " + s);
			}
		}
		return sb.toString();
	}
}
