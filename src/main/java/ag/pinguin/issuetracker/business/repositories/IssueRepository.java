package ag.pinguin.issuetracker.business.repositories;

import javax.transaction.Transactional;

import ag.pinguin.issuetracker.business.entities.Issue;

@Transactional
public interface IssueRepository extends IssueBaseRepository<Issue> {

}
