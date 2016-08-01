package ag.pinguin.issuetracker.api.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ag.pinguin.issuetracker.api.exceptions.MethodNotAllowed;
import ag.pinguin.issuetracker.api.exceptions.ResourceNotFoundException;
import ag.pinguin.issuetracker.business.entities.Bug;
import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.entities.Story;
import ag.pinguin.issuetracker.business.repositories.BugRepository;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;

/**
 * This class is a Spring REST Controller. This class is in charge
 * to manage the {@link Story}, {@link Bug}. 
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see RestController
 */
@RestController
@RequestMapping("/api/v1/issues")
public class IssueRESTController {
	
	/** The bug repository. */
	@Autowired
	BugRepository bugRepository;
	
	/** The story repository. */
	@Autowired
	StoryRepository storyRepository;
	
	/** The developer repository. */
	@Autowired
	DeveloperRepository developerRepository;
	
	/** The entity links. */
	@Autowired
	EntityLinks entityLinks;
	
	/**
	 * Retrieve issues.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> retrieveIssues() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/**
	 * Retrieve bugs.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Bug>> retrieveBugs() {
		Iterable<Bug> bugs = bugRepository.findAll();
		for(Bug bug : bugs) {
			bug.add(linkTo(methodOn(IssueRESTController.class).retrieveBugs(bug.getIssueId())).withSelfRel());
		}
		return ResponseEntity.ok(bugs);
	}
	
	/**
	 * Retrieve stories.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Story>> retrieveStories() {
		Iterable<Story> stories = storyRepository.findAll();
		for(Story story : stories) {
			story.add(linkTo(methodOn(IssueRESTController.class).retrieveStories(story.getIssueId())).withSelfRel());
		}
		return ResponseEntity.ok(stories);
	}
	
	/**
	 * Retrieve all bugs.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<Bug> retrieveBugs(@PathVariable Long id) {
		Bug bug = bugRepository.findOne(id);
		if(bug == null) {
			throw new ResourceNotFoundException("Bug not found");
		}
		return ResponseEntity.ok(bug);
	}
	
	/**
	 * Retrieve all stories.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<Story> retrieveStories(@PathVariable Long id) {
		Story story = storyRepository.findOne(id);
		if(story == null) {
			throw new ResourceNotFoundException("Story not found");
		}
		return ResponseEntity.ok(story);
	}
	
	/**
	 * Adds the developer to story.
	 *
	 * @param storyId the story id
	 * @param developerId the developer id
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories/{storyId:[\\d]+}/developer/{developerId:[\\d]+}/add", method = RequestMethod.GET)
	public ResponseEntity<Story> addDeveloperToStory(@PathVariable Long storyId, @PathVariable Long developerId) {
		Story story = storyRepository.findOne(storyId);
		if(story == null) {
			throw new ResourceNotFoundException("Story not found");
		}
		
		Developer developer = developerRepository.findOne(developerId);
		if(developer == null) {
			throw new ResourceNotFoundException("Developer not found");
		}
		
		story.setDeveloper(developer);
		storyRepository.save(story);
		story.add(linkTo(methodOn(IssueRESTController.class).retrieveStories(story.getIssueId())).withSelfRel());
		story.add(linkTo(DeveloperRESTController.class).slash(developer.getDeveloperId()).withRel("developer"));
		return ResponseEntity.ok(story);
		
	}
	
	/**
	 * Creates the bugs.
	 *
	 * @param bug the bug
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs", method = RequestMethod.POST)
	public ResponseEntity<Bug> createBugs(@Validated @RequestBody Bug bug) {
		bugRepository.save(bug);
		return ResponseEntity.status(HttpStatus.CREATED).body(bug);
	}
	
	/**
	 * Creates the stories.
	 *
	 * @param story the story
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.POST)
	public ResponseEntity<Story> createStories(@Validated @RequestBody Story story) {
		storyRepository.save(story);
		return ResponseEntity.status(HttpStatus.CREATED).body(story);
	}

	/**
	 * Update bugs.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBugs() {
		throw new MethodNotAllowed("It is not allowed to update more than one bug at the same time");
	}
	
	/**
	 * Update stories.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStories() {
		throw new MethodNotAllowed("It is not allowed to update more than one story at the same time");
	}
	
	/**
	 * Update bug.
	 *
	 * @param bug the bug
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs/{id:[\\d]+}", method = RequestMethod.PUT)
	public ResponseEntity<Bug> updateBug(@Valid @RequestBody Bug bug) {
		if(bug == null) {
			throw new ResourceNotFoundException("Bug not found");
		}
		bugRepository.save(bug);
		return ResponseEntity.ok().body(bug);
	}
	
	/**
	 * Update story.
	 *
	 * @param story the story
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories/{id:[\\d]+}", method = RequestMethod.PUT)
	public ResponseEntity<Story> updateStory(@Valid @RequestBody Story story) {
		if(story == null) {
			throw new ResourceNotFoundException("Story not found");
		}
		storyRepository.save(story);
		return ResponseEntity.ok().body(story);
	}
	
	/**
	 * Delete bugs.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBugs() {
		throw new MethodNotAllowed("It is not allowed to delete more than one bug at the same time");
	}
	
	/**
	 * Delete stories.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStories() {
		throw new MethodNotAllowed("It is not allowed to delete more than one story at the same time");
	}
	
	/**
	 * Delete bug.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/bugs/{id:[\\d]+}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBug(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		Bug bug = bugRepository.findOne(id);
		if(bug == null) {
			throw new ResourceNotFoundException("Bug not found");
		}
		bugRepository.delete(bug);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	/**
	 * Delete story.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/stories/{id:[\\d]+}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStory(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		Story story = storyRepository.findOne(id);
		if(story == null) {
			throw new ResourceNotFoundException("Story not found");
		}
		storyRepository.delete(story);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
