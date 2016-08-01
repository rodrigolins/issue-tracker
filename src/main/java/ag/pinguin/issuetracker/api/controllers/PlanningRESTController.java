package ag.pinguin.issuetracker.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.entities.Story;
import ag.pinguin.issuetracker.business.entities.WeekScheduleResult;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

/**
 * This class is a Spring REST Controller. This class is in charge
 * to calculate the weekly plan. 
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see RestController
 */
@RestController
@RequestMapping("/api/v1/planning")
public class PlanningRESTController {
	
	/** The story repository. */
	@Autowired
	private StoryRepository storyRepository;
	
	/** The developer repository. */
	@Autowired
	private DeveloperRepository developerRepository;
	
	/**
	 * Make the weekly plan.
	 *
	 * @return the weekly plan
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<WeekScheduleResult>>  makePlan() {
		Iterable<Developer> developers = developerRepository.findAll();
		Long developerCount = developerRepository.count();
		
		List<Story> storyList = storyRepository.getNotAssignedStories();
		
		List<WeekScheduleResult> plan = new ArrayList<WeekScheduleResult>();
		
		WeekScheduleResult currentWeek = null;
		int weeksFromToday = 0;
		
		while(!storyList.isEmpty()) {
			if(currentWeek == null) {
				currentWeek = new WeekScheduleResult(weeksFromToday);
				weeksFromToday++;
			}
			for(Developer d : developers) {
				if(storyList.isEmpty()){
					plan.add(currentWeek);
					break;
				}
				
				List<Story> developerStories = currentWeek.getStoriesFromDeveloper(d);
				if(developerStories == null) {
					developerStories = new ArrayList<Story>();
				}
				Story highest = null;
				for(Story s: storyList) {
					if(highest == null) {
						highest = s;
						continue;
					}
					int aux = s.getEstimation();
					if(aux > highest.getEstimation()) {
						highest = s; 
					}
				}
				int estimationDeveloperCount = 0;
				for(Story developerStory : developerStories) {
					estimationDeveloperCount += developerStory.getEstimation();
				}
				if((estimationDeveloperCount + highest.getEstimation()) <= 10) {
					currentWeek.addStoryToDeveloper(d, highest);
					storyList.remove(highest);
				} else {
					developerCount--;
					if(developerCount == 0L) {
						developerCount = developerRepository.count();
						plan.add(currentWeek);
						currentWeek = null;
						break;
					}
				}
			}
		}
		return ResponseEntity.ok().body(plan);
	}

}
