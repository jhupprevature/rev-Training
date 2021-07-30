package dev.hupp.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="communications")
public class Communication extends DBTable {
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@ManyToOne(optional = false, targetEntity = Employee.class)
	@JoinColumn(name = "sender", referencedColumnName = "id")
	private Employee sender;
	
	@Expose
	@ManyToOne(optional = false, targetEntity = Employee.class)
	@JoinColumn(name = "recipient", referencedColumnName = "id")
	private Employee recipient;
	
	@Expose
	@Column(name = "message_date", nullable = false)
	private Date messageDate;
	
	@Expose
	@Column(name = "message", nullable = false)
	private String message;
	
	@ManyToOne(optional=false, targetEntity = ReimbursementRequest.class)
	@JoinColumn(name = "request", referencedColumnName = "id")
	private ReimbursementRequest request;

	public Communication() {
		super();
	}

	public Communication(Employee sender, Employee recipient, Date messageDate, String message,
			ReimbursementRequest request) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.messageDate = messageDate;
		this.message = message;
		this.request = request;
	}

	public Communication(int id, Employee sender, Employee recipient, Date messageDate, String message,
			ReimbursementRequest request) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.messageDate = messageDate;
		this.message = message;
		this.request = request;
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

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public Employee getRecipient() {
		return recipient;
	}

	public void setRecipient(Employee recipient) {
		this.recipient = recipient;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReimbursementRequest getRequest() {
		return request;
	}

	public void setRequest(ReimbursementRequest request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Communication [id=" + id + ", sender=" + sender.getName() + ", recipient=" + recipient.getName() + ", messageDate="
				+ messageDate + ", message=" + message + ", request=" + request.getID() + "]";
	}

	
}
