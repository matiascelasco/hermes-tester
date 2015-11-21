package model;

import java.util.Date;

import utils.Converter;

import annotations.Mock;
import annotations.MockStringAttribute;
import annotations.MockTodayAttribute;

@Mock
public class Notification {
	
	@MockStringAttribute({"Emociones", "Predeterminada", "Alimentos", "Actividades y paseos"})
	public String category;
	
	@MockStringAttribute({"Alegre", "Molesto", "Entusiasmado"})
	public String content;
	
	@MockStringAttribute({"Establo-Terapia", "Pista", "Hogar"})
	public String context;
	
	@MockStringAttribute({"Bruce Wayner", "Dick Grayson", "Jimmy Olsen"})
	public String kid;
	
	@MockTodayAttribute
	public Date sent;
	
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
	
	public String toString(){
		return String.format("category=%s, kid=%s, context=%s, content=%s, sent=%s", category, kid, context, content, Converter.dateToString(sent));
	}
}
