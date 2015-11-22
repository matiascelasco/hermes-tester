package model;

import java.util.Date;

public class Notification {
	private String category;
	private String content;
	private String context;
	private String kid;
	private Date sent;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public Date getSent() {
		return sent;
	}
	public void setSent(Date sent) {
		this.sent = sent;
	}
	
	
}
