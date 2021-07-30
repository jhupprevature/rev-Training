package dev.hupp.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
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
@Table(name="reimbursement_requests")
public class ReimbursementRequest extends DBTable {

//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@ManyToOne(optional = false, targetEntity = Employee.class)
	@JoinColumn(name = "requestor", referencedColumnName = "id")
	private Employee requestor;
	
	@Expose
	@ManyToOne(optional = false, targetEntity = Event.class)
	@JoinColumn(name = "event", referencedColumnName = "id")
	private Event event;
	
	@Expose
	@Column(name = "justification", nullable = false)
	private String justification;
	
	@Expose
	@Column(name = "time_requirement", nullable = false)
	private BigDecimal timeRequirement;
	
	@Expose
	@Column(name = "last_updated", nullable = false)
	private Date lastUpdated;
	
	//TODO Investigate ENUM
	@Expose
	@Column(name = "pending_response_from", nullable = false)
	private String pendingResponseFrom;
	
	@Expose
	@Column(name = "proposed_amount")
	private BigDecimal proposedAmount;
	
	//TODO ENUM?
	@Expose
	@Column(name = "decision")
	private String decision;
	
	@Expose
	@Column(name = "decision_comments")
	private String decisionComments;
	
	@Expose
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "decider", referencedColumnName = "id")
	private Employee decider;
	
	@Expose
	@Column(name = "disbursed")
	private boolean disbursed;
	
	@OneToMany(mappedBy="request", cascade = {CascadeType.MERGE})
	private List<RequestAttachment> attachments;
	
	@OneToMany(mappedBy="request", cascade = {CascadeType.MERGE})
	private List<Communication> requestMessages;

	public ReimbursementRequest() {
		super();
	}

	public ReimbursementRequest(Employee requestor, Event event, String justification, double timeRequirement,
			Date lastUpdated, String pendingResponseFrom, double proposedAmount, String decision,
			String decisionComents, Employee decider, boolean disbursed) {
		super();
		this.requestor = requestor;
		this.event = event;
		this.justification = justification;
		this.timeRequirement = BigDecimal.valueOf(timeRequirement);
		this.lastUpdated = lastUpdated;
		this.pendingResponseFrom = pendingResponseFrom;
		this.proposedAmount = BigDecimal.valueOf(proposedAmount);
		this.decision = decision;
		this.decisionComments = decisionComents;
		this.decider = decider;
		this.disbursed = disbursed;
	}
	
	public ReimbursementRequest(Employee requestor, Event event, String justification, double timeRequirement) {
		super();
		this.requestor = requestor;
		this.event = event;
		this.justification = justification;
		this.timeRequirement = BigDecimal.valueOf(timeRequirement);
		this.lastUpdated = Date.valueOf(LocalDate.now());
		this.pendingResponseFrom = "sup";
		this.proposedAmount = BigDecimal.valueOf(event.getEventType().getCoveragePercent() * event.getCost());
		this.decision = "pending";
		this.decisionComments = null;
		this.decider = null;
		this.disbursed = false;
	}
	
	public ReimbursementRequest(Employee requestor, Event event, String justification, double timeRequirement, double proposedAmount) {
		super();
		this.requestor = requestor;
		this.event = event;
		this.justification = justification;
		this.timeRequirement = BigDecimal.valueOf(timeRequirement);
		this.lastUpdated = Date.valueOf(LocalDate.now());
		this.pendingResponseFrom = "sup";
		this.proposedAmount = BigDecimal.valueOf(proposedAmount);
		this.decision = "pending";
		this.decisionComments = null;
		this.decider = null;
		this.disbursed = false;
	}

	public ReimbursementRequest(int id, Employee requestor, Event event, String justification, double timeRequirement,
			Date lastUpdated, String pendingResponseFrom, double proposedAmount, String decision,
			String decisionComents, Employee decider, boolean disbursed) {
		super();
		this.id = id;
		this.requestor = requestor;
		this.event = event;
		this.justification = justification;
		this.timeRequirement = BigDecimal.valueOf(timeRequirement);
		this.lastUpdated = lastUpdated;
		this.pendingResponseFrom = pendingResponseFrom;
		this.proposedAmount = BigDecimal.valueOf(proposedAmount);
		this.decision = decision;
		this.decisionComments = decisionComents;
		this.decider = decider;
		this.disbursed = disbursed;
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

	public Employee getRequestor() {
		return requestor;
	}

	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public double getTimeRequirement() {
		return timeRequirement.doubleValue();
	}

	public void setTimeRequirement(double timeRequirement) {
		this.timeRequirement = BigDecimal.valueOf(timeRequirement);
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPendingResponseFrom() {
		return pendingResponseFrom;
	}

	public void setPendingResponseFrom(String pendingResponseFrom) {
		this.pendingResponseFrom = pendingResponseFrom;
	}

	public double getProposedAmount() {
		return proposedAmount.doubleValue();
	}

	public void setProposedAmount(double proposedAmount) {
		this.proposedAmount = BigDecimal.valueOf(proposedAmount);
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getDecisionComents() {
		return decisionComments;
	}

	public void setDecisionComents(String decisionComents) {
		this.decisionComments = decisionComents;
	}

	public Employee getDecider() {
		return decider;
	}

	public void setDecider(Employee decider) {
		this.decider = decider;
	}

	public boolean isDisbursed() {
		return disbursed;
	}

	public void setDisbursed(boolean disbursed) {
		this.disbursed = disbursed;
	}

	public List<RequestAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<RequestAttachment> attachments) {
		this.attachments = attachments;
	}

	public List<Communication> getRequestMessages() {
		return requestMessages;
	}

	public void setRequestMessages(List<Communication> requestMessages) {
		this.requestMessages = requestMessages;
	}

	@Override
	public String toString() {
		return "ReimbursementRequest [id=" + id + ", requestor=" + requestor.getName() + ", event=" + event.getID() + ", justification="
				+ justification + ", timeRequirement=" + timeRequirement + ", lastUpdated=" + lastUpdated
				+ ", pendingResponseFrom=" + pendingResponseFrom + ", proposedAmount=" + proposedAmount + ", decision="
				+ decision + ", decisionComments=" + decisionComments + ", decider=" + (decider == null ? "none" : decider.getName()) + ", disbursed="
				+ disbursed + "]";
	}
	
}
