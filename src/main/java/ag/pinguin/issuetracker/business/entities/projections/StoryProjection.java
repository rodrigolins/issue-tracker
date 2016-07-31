package ag.pinguin.issuetracker.business.entities.projections;

import org.springframework.data.rest.core.config.Projection;

import ag.pinguin.issuetracker.business.entities.Story;

@Projection(name = "test", types = Story.class)
public interface StoryProjection {
	
	Long getId();
	
	Integer getEstimation();

}
