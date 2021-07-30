package dev.hupp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="grading_formats")
public class GradingFormat extends DBTable {

//	@Id
//	@Column(name="id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@Column(name="format_name", nullable=false)
	private String formatName;
	
	@Expose
	@Column(name="description", nullable=false)
	private String description;
	
	@Expose
	@Column(name="default_grade", nullable=false)
	private String defaultGrade;
	
	@OneToMany(mappedBy = "gradingFormat", cascade = {CascadeType.MERGE})
	private List<Event> events;

	public GradingFormat() {
		super();
	}

	public GradingFormat(String formatName, String description, String defaultGrade) {
		super();
		this.formatName = formatName;
		this.description = description;
		this.defaultGrade = defaultGrade;
	}

	public GradingFormat(int id, String formatName, String description, String defaultGrade) {
		super();
		this.id = id;
		this.formatName = formatName;
		this.description = description;
		this.defaultGrade = defaultGrade;
	}
	
//	@Override
//	public int getID() {
//		return id;
//	}
//
//	@Override
//	public void setID(int id) {
//		this.id = id;
//	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultGrade() {
		return defaultGrade;
	}

	public void setDefaultGrade(String defaultGrade) {
		this.defaultGrade = defaultGrade;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "GradingFormatRepo [id=" + id + ", formatName=" + formatName + ", description=" + description
				+ ", defaultGrade=" + defaultGrade + "]";
	}
	
}
