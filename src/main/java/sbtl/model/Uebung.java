package sbtl.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Anfang der neuen Datenstruktur, aktuell noch keine Beachtung schenken!
@Entity
public class Uebung {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double gewicht;
	private int wiederholung;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "enthaelt")
	Set<Tag> istEnthalten = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public int getWiederholung() {
		return wiederholung;
	}

	public void setWiederholung(int wiederholung) {
		this.wiederholung = wiederholung;
	}

	public Set<Tag> getIstEnthalten() {
		return istEnthalten;
	}

	public void setIstEnthalten(Set<Tag> istEnthalten) {
		this.istEnthalten = istEnthalten;
	}

	
	
	
	
	
	
}
