package dev.hupp.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="event_types")
public class EventType extends DBTable {
	
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@Column(name="type_name", nullable=false)
	private String typeName;
	
	@Expose
	@Column(name="coverage_percent", nullable=false)
	private BigDecimal coveragePercent;
	
	@OneToMany(mappedBy = "eventType", cascade = {CascadeType.MERGE})
	private List<Event> events;

	public EventType() {
		super();
	}

	public EventType(String typeName, double coveragePercent) {
		super();
		this.typeName = typeName;
		this.coveragePercent = BigDecimal.valueOf(coveragePercent);
	}

	public EventType(int id, String typeName, double coveragePercent) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.coveragePercent =  BigDecimal.valueOf(coveragePercent);
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getCoveragePercent() {
		return coveragePercent.doubleValue();
	}

	public void setCoveragePercent(double coveragePercent) {
		this.coveragePercent =  BigDecimal.valueOf(coveragePercent);
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", typeName=" + typeName + ", coveragePercent=" + coveragePercent + "]";
	}
	
	
}
