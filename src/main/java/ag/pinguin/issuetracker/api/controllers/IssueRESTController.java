package ag.pinguin.issuetracker.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ag.pinguin.issuetracker.business.entities.Bug;
import ag.pinguin.issuetracker.business.entities.Story;
import ag.pinguin.issuetracker.business.repositories.BugRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueRESTController {
	
	@Autowired
	BugRepository bugRepository;
	
	@Autowired
	StoryRepository storyRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> retrieveIssues() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value = "/bugs", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Bug>> retrieveBugs() {
		return ResponseEntity.ok(bugRepository.findAll());
	}
	
	@RequestMapping(value = "/stories", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Story>> retrieveStories() {
		return ResponseEntity.ok(storyRepository.findAll());
	}
	
	@RequestMapping(value = "/bugs", method = RequestMethod.POST)
	public ResponseEntity<Bug> createBugs(@Validated @RequestBody Bug bug) {
		if(bug == null) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		bugRepository.save(bug);
		return ResponseEntity.status(HttpStatus.CREATED).body(bug);
	}
	
	@RequestMapping(value = "/stories", method = RequestMethod.POST)
	public ResponseEntity<Story> createStories(@Validated @RequestBody Story story) {
		if(story == null) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		storyRepository.save(story);
		return ResponseEntity.status(HttpStatus.CREATED).body(story);
	}

	@RequestMapping(value = "/bugs", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBugs() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value = "/stories", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStories() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value = "/bugs/{id:[\\d]+}", method = RequestMethod.PUT)
	public ResponseEntity<Bug> updateBug(@Valid @RequestBody Bug bug) {
		if(bug == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		bugRepository.save(bug);
		return ResponseEntity.ok().body(bug);
	}
	
	@RequestMapping(value = "/stories/{id:[\\d]+}", method = RequestMethod.PUT)
	public ResponseEntity<Story> updateStory(@Valid @RequestBody Story story) {
		if(story == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		storyRepository.save(story);
		return ResponseEntity.ok().body(story);
	}
	
	@RequestMapping(value = "/bugs", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBugs() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value = "/stories", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStories() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(value = "/bugs/{id:[\\d]+}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBug(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		Bug bug = bugRepository.findOne(id);
		if(bug == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		bugRepository.delete(bug);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@RequestMapping(value = "/stories/{id:[\\d]+}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStory(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		Story story = storyRepository.findOne(id);
		if(story == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		storyRepository.delete(story);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
