package ag.pinguin.issuetracker.api.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ag.pinguin.issuetracker.api.exceptions.MethodNotAllowed;
import ag.pinguin.issuetracker.api.exceptions.ResourceNotFoundException;
import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;

/**
 * This class is a Spring REST Controller. This class is in charge
 * to manage the {@link Developer} instance via REST. 
 * 
 * @see RestController
 * 
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 */
@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRESTController {
	
	/** The developer repository. */
	@Autowired
	DeveloperRepository developerRepository;

	/**
	 * This method is responsible to retrieve all developers via GET method.
	 *
	 * @return all developers
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Developer>> retrieveDevelopers() {
		Iterable<Developer> developers = developerRepository.findAll();
		for(Developer developer : developers) {
			developer.add(linkTo(methodOn(DeveloperRESTController.class).retrieveDevelopers(developer.getDeveloperId())).withSelfRel());
		}
		return ResponseEntity.ok(developers);
	}
	
	/**
	 * Creates the developer via POST method. If all required parameters from
	 * {@link Developer} are not provided a BAD_REQUEST is returned.
	 *
	 * @param the developer to be created
	 * @return the created developer object
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Developer> createDevelopers(@Valid @RequestBody Developer developer) {
		if(developer.getName() == null) {
			throw new ResourceNotFoundException("The developer name should not be null");
		}
		developerRepository.save(developer);
		developer.add(linkTo(methodOn(DeveloperRESTController.class).retrieveDevelopers(developer.getDeveloperId())).withSelfRel());
		return ResponseEntity.status(HttpStatus.CREATED).body(developer);
	}
	
	/**
	 * If one try to update more than one {@link Developer} a BAD_REQUEST
	 * response is provided.
	 *
	 * @return BAD_REQUEST
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDevelopers() {
		throw new MethodNotAllowed("It is not allowed to update more than one developer at the same time");
	}
	
	/**
	 * If one try to update more than one {@link Developer} the response is
	 * a BAD_REQUEST. 
	 *
	 * @return BAD_REQUEST
	 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDevelopers() {
		throw new MethodNotAllowed("It is not allowed to delete more than one developer at the same time");
	}
	
	/**
	 * Retrieve developers.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<Developer> retrieveDevelopers(@PathVariable Long id) {
		Developer developer = developerRepository.findOne(id);
		if(developer == null) {
			throw new ResourceNotFoundException("Developer not found");
		}
		developer.add(linkTo(methodOn(DeveloperRESTController.class).retrieveDevelopers(id)).withSelfRel());
		return ResponseEntity.ok(developer);
	}
	
	/**
	 * Creates the developers.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.POST)
	public ResponseEntity<String> createDevelopers() {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
	}
	
	/**
	 * Update developers.
	 *
	 * @param developer the developer
	 * @return the response entity
	 */
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDevelopers(@Valid @RequestBody Developer developer) {
		if(developer == null) {
			throw new ResourceNotFoundException("Developer not found");
		}
		developerRepository.save(developer);
		developer.add(linkTo(methodOn(DeveloperRESTController.class).retrieveDevelopers(developer.getDeveloperId())).withSelfRel());
		return ResponseEntity.ok().body(null);
	}
	
	/**
	 * Delete a developer.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDevelopers(@PathVariable Long id) {
		if(id == null) {
			throw new ResourceNotFoundException("Developer not found");
		}
		
		Developer developer = developerRepository.findOne(id);
		if(developer == null) {
			throw new ResourceNotFoundException("Developer not found");
		}
		developerRepository.delete(developer);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
