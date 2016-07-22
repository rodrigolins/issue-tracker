package ag.pinguin.issuetracker.business.repositories;

import javax.transaction.Transactional;

import ag.pinguin.issuetracker.business.entities.Bug;

@Transactional
public interface BugRepository extends IssueBaseRepository<Bug> { }
