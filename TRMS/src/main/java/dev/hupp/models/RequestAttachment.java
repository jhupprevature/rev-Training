package dev.hupp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="request_attachments")
public class RequestAttachment extends DBTable {
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Expose
	@Column(name="doc_url", nullable=false)
	private String docURL;
	
	@Expose
	@ManyToOne(optional=false, targetEntity=ReimbursementRequest.class)
	@JoinColumn(name = "request", referencedColumnName = "id")
	private ReimbursementRequest request;
	
	@Expose
	@Column(name="type") //default="Other")
	private String type;

	public RequestAttachment() {
		super();
	}

	public RequestAttachment(String docURL, ReimbursementRequest requestID, String type) {
		super();
		this.docURL = docURL;
		this.request = requestID;
		this.type = type;
	}

	public RequestAttachment(int id, String docURL, ReimbursementRequest requestID, String type) {
		super();
		this.id = id;
		this.docURL = docURL;
		this.request = requestID;
		this.type = type;
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

	public String getDocURL() {
		return docURL;
	}

	public void setDocURL(String docURL) {
		this.docURL = docURL;
	}

	public ReimbursementRequest getRequestID() {
		return request;
	}

	public void setRequestID(ReimbursementRequest requestID) {
		this.request = requestID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RequestAttachment [id=" + id + ", docURL=" + docURL + ", request=" + request.getID() + ", type=" + type
				+ "]";
	}
}
