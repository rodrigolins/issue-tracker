package ag.pinguin.issuetracker.business.repositories;

import org.springframework.data.repository.CrudRepository;

import ag.pinguin.issuetracker.business.entities.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long>{
	
	public Developer findDeveloperByName(String name);

}
