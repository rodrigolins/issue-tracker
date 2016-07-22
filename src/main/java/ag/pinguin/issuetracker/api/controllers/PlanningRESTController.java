package ag.pinguin.issuetracker.api.controllers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.entities.Story;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/api/v1/planning")
public class PlanningRESTController {
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Story>>  makePlan() {
		System.out.println(storyRepository.getNotAssignedStories());
//		developerRepository.
		Integer storyPoints = storyRepository.getNotAssignedStoryPoints();
		Long developerCount = developerRepository.count();
		Integer totalPoints = developerCount.intValue() * 10; // Per week
		Iterable<Developer> developers = developerRepository.findAll();
		
		Iterable<Story> stories = storyRepository.getNotAssignedStories();
		List<Story> storiesList = Lists.newArrayList(stories);
		List<Map<Developer, List<Story>>> plan = new ArrayList<Map<Developer,List<Story>>>();
		Map<Developer, List<Story>> week = new Hashtable<Developer, List<Story>>();
		
		while(!storiesList.isEmpty()) {
			for(Developer d : developers) {
				for(Story s : storiesList) {
					List<Story> devStories = week.get(d);
					if(devStories.isEmpty()) {
						devStories = new ArrayList<Story>();
						devStories.add(s);
						storiesList.remove(s);
						week.put(d, devStories);
					} else {
						int estimationPoints = 0;
						for(Story st : devStories) {
							estimationPoints += st.getEstimation();
						}
						if((estimationPoints + s.getEstimation()) <= 10) {
							devStories.add(s);
							storiesList.remove(s);
						}
					}
				}
			}
		}
		
		
		
		
		System.out.println(developerCount);
		return ResponseEntity.ok().body(storyRepository.getNotAssignedStories());
	}

}
