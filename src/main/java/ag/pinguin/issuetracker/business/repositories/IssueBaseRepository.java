package ag.pinguin.issuetracker.business.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ag.pinguin.issuetracker.business.entities.Issue;

@NoRepositoryBean
public interface IssueBaseRepository<T extends Issue> extends CrudRepository<T, Long> {

}
