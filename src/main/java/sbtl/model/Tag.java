package sbtl.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	    private String tagName;
	    private LocalDate date = LocalDate.now();


	@JsonIgnore
	@ManyToMany(mappedBy = "istEnthalten")
		Set<Uebung> enthaelt = new HashSet<>();

	public Set<Uebung> getEnthaelt() {
		return enthaelt;
	}

	public void setEnthaelt(Set<Uebung> enthaelt) {
		this.enthaelt = enthaelt;
	}
	    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void enrollUebung(Uebung uebung) {
		enthaelt.add(uebung);
	}
	 
	 
}
