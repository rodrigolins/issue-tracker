package ag.pinguin.issuetracker.business.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "DEVELOPER")
public class Developer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEVELOPER_ID")
	private Long id;
	
	@NotNull
	@Column(name = "DEVELOPER_NAME", unique = true)
	private String name;
	
	@OneToMany
	@JoinColumn(name = "DEVELOPER_ID", insertable = false, updatable = false)
	@Where(clause = "ISSUE_TYPE='S'")
	@JsonManagedReference
	@JsonIgnore
	private List<Story> stories;
	
	@OneToMany
	@JoinColumn(name = "DEVELOPER_ID", insertable = false, updatable = false)
	@Where(clause = "ISSUE_TYPE='B'")
	@JsonManagedReference
	@JsonIgnore
	private List<Bug> bugs;
	
	public Developer() {}
	
	public Developer(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bug> getBugs() {
		return bugs;
	}
	
	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}
	
	public List<Story> getStories() {
		return stories;
	}
	
	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + "]";
	}
	
//	@JsonValue
//	public String getUri() {
//		return "developers/" + this.id;
//	}

}
