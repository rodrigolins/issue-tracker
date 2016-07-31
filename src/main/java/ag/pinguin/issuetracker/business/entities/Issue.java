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
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class represents a {@link Issue}.
 * 
 * A {@link Issue} can be a {@link Story} or a {@link Bug}.
 *
 * @author Rodrigo Lins de Oliveira
 * @version 0.1
 * Date: 18.07.2016
 * @see Issue
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ISSUE_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "ISSUE")
public abstract class Issue extends ResourceSupport {
	
	/** The {@link Long} instance representation of the id. */
	@Id
	@GeneratedValue
	@Column(name = "ISSUE_ID")
	private Long id;
	
	/** The {@link String} instance representation of the title. */
	@NotNull
	@Column(name = "ISSUE_TITLE")
	private String title;
	
	/** The {@link String} instance representation of the description. */
	@Column(name = "ISSUE_DESCRIPTION")
	private String description;
	
	/** The {@link Calendar} instance representation of the creation date. */
	@Column(name = "ISSUE_CREATION_DATE")
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
	@CreationTimestamp
	private Calendar creationDate;

	/** The {@link Developer} instance which this instance is associated to. */
	@ManyToOne
	@JoinColumn(name = "DEVELOPER_ID")
	@JsonBackReference
	private Developer developer;
	
	/**
	 * The default constructor without any parameters. 
	 */
	public Issue() { }
	
	/**
	 * The constructor that receive all parameters.
	 *
	 * @param title the {@link Issue} title
	 * @param description the {@link Issue} description
	 * @param developer the {@link Issue} developer associated to.
	 */
	public Issue(String title, String description, Developer developer) {
		this.title = title;
		this.description = description;
		this.developer = developer;
	}

	/**
	 * Gets the {@link Issue} id.
	 *
	 * @return the id
	 */
	public Long getIssueId() {
		return id;
	}

	/**
	 * Sets the {@link Issue} id.
	 *
	 * @param id the new id
	 */
	public void setIssueId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the {@link Issue} title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the {@link Issue} title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the {@link Issue} description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the {@link Issue} description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the {@link Issue} creation date.
	 *
	 * @return the creation date
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * Gets the {@link Developer} that refer to the {@link Issue}.
	 *
	 * @return the developer
	 */
	public Developer getDeveloper() {
		return developer;
	}

	/**
	 * Sets the {@link Developer} to the {@link Issue}.
	 *
	 * @param developer the new developer
	 */
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	/**
	 *  The {@link String} representation of the {@link Issue}.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", description="
				+ description + ", creationDate=" + creationDate
				+ ", developer=" + developer + "]";
	}
	
}
