package sbtl.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Uebung {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double gewicht;
	private Integer wiederholung;
	
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

	public Double getGewicht() {
		return gewicht;
	}

	public void setGewicht(Double gewicht) {
		this.gewicht = gewicht;
	}

	public Integer getWiederholung() {
		return wiederholung;
	}

	public void setWiederholung(Integer wiederholung) {
		this.wiederholung = wiederholung;
	}

	public Set<Tag> getIstEnthalten() {
		return istEnthalten;
	}

	public void setIstEnthalten(Set<Tag> istEnthalten) {
		this.istEnthalten = istEnthalten;
	}

	
	
	
	
	
	
}
