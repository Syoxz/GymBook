package sbtl.model;

import java.util.Set;

import javax.persistence.*;

//Anfang der neuen Datenstruktur, aktuell noch keine Beachtung schenken!
@Entity
public class Uebung {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double gewicht;
	private int wiederholung;
	
	@ManyToMany
	@JoinTable(
			name = "tag_uebung",
			joinColumns = @JoinColumn(name = "uebung_id"),
			inverseJoinColumns =  @JoinColumn(name ="tag_id")
	)
	Set<Tag> istEnthalten;

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
