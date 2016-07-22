package ag.pinguin.issuetracker.business.entities;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ISSUE_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "ISSUE")
public abstract class Issue {
	
	@Id
	@GeneratedValue
	@Column(name = "ISSUE_ID")
	private Long id;
	
	@NotNull
	@Column(name = "ISSUE_TITLE")
	private String title;
	
	@Column(name = "ISSUE_DESCRIPTION")
	private String description;
	
	@Column(name = "ISSUE_CREATION_DATE")
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
	@CreationTimestamp
	private Calendar creationDate;
	
	@ManyToOne
	@JoinColumn(name = "DEVELOPER_ID")
	@JsonBackReference
	private Developer developer;
	
	public Issue() { }
	
	public Issue(String title, String description, Developer developer) {
		this.title = title;
		this.description = description;
		this.developer = developer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", description="
				+ description + ", creationDate=" + creationDate
				+ ", developer=" + developer + "]";
	}
	
}
