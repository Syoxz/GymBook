package sbtl.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tagName;
	private LocalDate date = LocalDate.now();

	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "tag_uebung",
			joinColumns = @JoinColumn(name = "tag_id"),
			inverseJoinColumns =  @JoinColumn(name ="uebung_id")
	)
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
	public void deleteUebung (Uebung uebung) {
		enthaelt.remove(uebung);
	}
}
