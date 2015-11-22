package model;

import java.util.Date;

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
}
