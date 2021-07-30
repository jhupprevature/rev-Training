package dev.hupp.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="events")
public class Event extends DBTable {
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;

	@Expose
	@Column(name = "event_date", nullable = false)
	private Date eventDate;

	@Expose
	@Column(name = "event_time", nullable = false)
	private Time eventTime;

	@Expose
	@Column(name = "event_location", nullable = false)
	private String eventLocation;

	@Expose
	@Column(name = "description", nullable = false)
	private String description;

	@Expose
	@Column(name = "cost", nullable = false)
	private BigDecimal cost;

	@Expose
	@ManyToOne(optional = false, targetEntity = EventType.class)
	@JoinColumn(name = "event_type", referencedColumnName = "id")
	private EventType eventType;

	@Expose
	@ManyToOne(optional = false, targetEntity = GradingFormat.class)
	@JoinColumn(name = "grading_format", referencedColumnName = "id")
	private GradingFormat gradingFormat;

	@Column(name = "passing_Grade")
	private String passingGrade;
	
	@OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE})
	private List<ReimbursementRequest> requests;

	public Event() {
		super();
	}

	public Event(Date eventDate, Time eventTime, String eventLocation, String description, double cost, EventType eventType,
			GradingFormat gradingFormat, String passingGrade) {
		super();
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.description = description;
		this.cost = BigDecimal.valueOf(cost);
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
	}

	public Event(int id, Date eventDate, Time eventTime, String eventLocation, String description, double cost,
			EventType eventType, GradingFormat gradingFormat, String passingGrade) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.description = description;
		this.cost = BigDecimal.valueOf(cost);
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Time getEventTime() {
		return eventTime;
	}

	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost.doubleValue();
	}

	public void setCost(double cost) {
		this.cost = BigDecimal.valueOf(cost);
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventDate=" + eventDate + ", eventTime=" + eventTime + ", eventLocation="
				+ eventLocation + ", description=" + description + ", cost=" + cost + ", eventType=" + eventType.getTypeName()
				+ ", gradingFormat=" + gradingFormat.getFormatName() + ", passingGrade=" + passingGrade + "]";
	}

}
