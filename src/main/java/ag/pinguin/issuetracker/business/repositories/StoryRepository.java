package ag.pinguin.issuetracker.business.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import ag.pinguin.issuetracker.business.entities.Story;

@Transactional
public interface StoryRepository extends IssueBaseRepository<Story> {
	
	@Query("SELECT s FROM Story s WHERE s.status = "
			+ "ag.pinguin.issuetracker.business.entities.StoryStatus.NEW_STORY")
	public List<Story> getNewStories();
	
	@Query("SELECT s FROM Story s WHERE s.developer is null")
	public List<Story> getNotAssignedStories();
	
	@Query("SELECT sum(s.estimation) FROM Story s WHERE s.developer is null")
	public Integer getNotAssignedStoryPoints();
}
