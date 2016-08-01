package ag.pinguin.issuetracker.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

/**
 * This class represents a {@link Developer}.
 * 
 * A {@link Developer} represents a person that can
 * work with {@link Story} and {@link Bug}.
 * 
 * @see Issue, Story and Bug.
 * 
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 */
@Entity
@Table(name = "DEVELOPER")
public class Developer extends ResourceSupport {
	
	/** The {@link Developer} id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEVELOPER_ID")
	private Long id;
	
	/** The {@link Developer} name. */
	@NotNull
	@Column(name = "DEVELOPER_NAME", unique = true)
	private String name;
	
//	@OneToMany
//	@JoinColumn(name = "DEVELOPER_ID", insertable = false, updatable = false)
//	@Where(clause = "ISSUE_TYPE='S'")
//	@JsonManagedReference
//	private List<Story> stories;
	
//	@OneToMany
//	@JoinColumn(name = "DEVELOPER_ID", insertable = false, updatable = false)
//	@Where(clause = "ISSUE_TYPE='B'")
//	@JsonManagedReference
//	private List<Bug> bugs;
	
	/**
	 * The default constructor for a new {@link Developer}.
	 */
	public Developer() {}
	
	/**
	 * The {@link Developer} constructor with all arguments.
	 *
	 * @param name   the {@link String} name
	 */
	public Developer(String name) {
		super();
		this.name = name;
	}

	/**
	 * Gets the {@link Developer} id.
	 *
	 * @return the {@link Long} representation of the {@link Developer} id
	 */
	public Long getDeveloperId() {
		return id;
	}

	/**
	 * Sets the {@link Developer} id.
	 *
	 * @param id  the {@link Developer} new id
	 */
	public void setDeveloperId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the {@link Developer} name.
	 *
	 * @return the {@link Developer} name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the {@link Developer} name.
	 *
	 * @param name    the {@link Developer} new name
	 */
	public void setName(String name) {
		this.name = name;
	}

//	public List<Bug> getBugs() {
//		return bugs;
//	}
//	
//	public void setBugs(List<Bug> bugs) {
//		this.bugs = bugs;
//	}
//	
//	public List<Story> getStories() {
//		return stories;
//	}
//	
//	public void setStories(List<Story> stories) {
//		this.stories = stories;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Developer other = (Developer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * The {@link String} representation of a {@link Developer}.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Developer [id=" + getDeveloperId() + ", name=" + name + "]";
	}
	
}
