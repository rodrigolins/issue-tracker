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
import ag.pinguin.issuetracker.business.entities.WeekSchedule;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

@RestController
@RequestMapping("/api/v1/planning")
public class PlanningRESTController {
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<WeekSchedule>>  makePlan() {
		
		Iterable<Developer> developers = developerRepository.findAll();
		Long developerCount = developerRepository.count();
		
		List<Story> storyList = storyRepository.getNotAssignedStories();
		
		List<WeekSchedule> plan = new ArrayList<WeekSchedule>();
//		List<Map<Developer, List<Story>>> plan = new ArrayList<Map<Developer,List<Story>>>();
		
		WeekSchedule currentWeek = null;
//		Map<Developer, List<Story>> week = null;
		int weeksFromToday = 0;
		
		while(!storyList.isEmpty()) {
			if(currentWeek == null) {
				System.out.println("New week");
				currentWeek = new WeekSchedule(weeksFromToday);
				weeksFromToday++;
			}
			for(Developer d : developers) {
				System.out.println("StoryList counter: " + storyList.size());
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
				System.out.println("highest: " + highest);
				int estimationDeveloperCount = 0;
				for(Story developerStory : developerStories) {
					estimationDeveloperCount += developerStory.getEstimation();
				}
				if((estimationDeveloperCount + highest.getEstimation()) <= 10) {
//					developerStories.add(highest);
					currentWeek.addStoryToDeveloper(d, highest);
					storyList.remove(highest);
				} else {
					developerCount--;
					System.out.println(developerCount);
					if(developerCount == 0L) {
						System.out.println("StoryList size: " + storyList.size());
						developerCount = developerRepository.count();
						plan.add(currentWeek);
						currentWeek = null;
						break;
					}
				}
			}
		}
//		while(!storyList.isEmpty()) {
//			if(week == null) {
//				System.out.println("New week");
//				week = new Hashtable<Developer, List<Story>>();
//			}
//			for(Developer d : developers) {
//				System.out.println("StoryList counter: " + storyList.size());
//				if(storyList.isEmpty()){
//					plan.add(week);
//					break;
//				}
//				List<Story> developerStories = week.get(d);
//				if(developerStories == null) {
//					developerStories = new ArrayList<Story>();
//				}
//				Story highest = null;
//				for(Story s: storyList) {
//					if(highest == null) {
//						highest = s;
//						continue;
//					}
//					int aux = s.getEstimation();
//					if(aux > highest.getEstimation()) {
//						highest = s; 
//					}
//				}
//				System.out.println("highest: " + highest);
//				int estimationDeveloperCount = 0;
//				for(Story developerStory : developerStories) {
//					estimationDeveloperCount += developerStory.getEstimation();
//				}
//				if((estimationDeveloperCount + highest.getEstimation()) <= 10) {
//					developerStories.add(highest);
//					week.put(d, developerStories);
//					storyList.remove(highest);
//				} else {
//					developerCount--;
//					System.out.println(developerCount);
//					if(developerCount == 0L) {
//						System.out.println("StoryList size: " + storyList.size());
//						developerCount = developerRepository.count();
//						plan.add(week);
//						week = null;
//						break;
//					}
//				}
//			}
//		}
		return ResponseEntity.ok().body(plan);
	}

}
