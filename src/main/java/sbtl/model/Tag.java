package sbtl.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	    private String tagName;
	    private LocalDate date = LocalDate.now();



	@ManyToMany(mappedBy = "istEnthalten")
		Set<Uebung> enthaelt;

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
	
	 
	 
}
