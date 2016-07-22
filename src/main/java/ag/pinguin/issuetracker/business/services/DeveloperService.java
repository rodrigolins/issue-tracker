package ag.pinguin.issuetracker.business.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.entities.Story;
import ag.pinguin.issuetracker.business.entities.WeekSchedule;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

public class DeveloperService {
	
	private final Integer MAX_ESTIMATION_WEEKLY_POINTS = 10;

	@Autowired
	private DeveloperRepository developerRepository;
	
	@Autowired
	private StoryRepository storyRepository;
	
	public void assignStoriesToDeveloper() {
		
		List<Story> newStories = storyRepository.getNewStories();
		if(newStories.isEmpty()){
			return;
		}
		
		List<WeekSchedule> schedule = new ArrayList<WeekSchedule>();
		WeekSchedule aweek = new WeekSchedule();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		aweek.setInitialDate(date);
		date.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		aweek.setFinalDate(date);
		
		Iterable<Developer> developers = developerRepository.findAll();
		
		for(Story story : newStories) {
			for(Developer developer : developers) {
				List<Story> developerStories = developer.getStories();
				if(developerStories.isEmpty() || story.getEstimation() > MAX_ESTIMATION_WEEKLY_POINTS) {
					story.setDeveloper(developer);
					storyRepository.save(story);
					continue;
				}
				Integer weeklyStoryPoints = 0;
				for(Story develoverStory : developerStories) {
					
				}
			}
		}
		
		
	}
}
