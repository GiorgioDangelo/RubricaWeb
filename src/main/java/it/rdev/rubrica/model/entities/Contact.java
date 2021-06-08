package it.rdev.rubrica.model.entities;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "contacts")
@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c" ) //Questa Selecct si riferisce alla classe
//quindi from Contact è la classe Contact
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //ID si riferisce all'attributo chiave ,in questo caso l'attributo id e l'annotazione id
	//hanno lo stesso nome ,ma è solo un caso ,in generale con l'annotazione @ID ci si riferisce alla chiave
	@Column(name = "id") //Si riferisce alla colonna in questione
	private Integer id;
	@Column(name = "name",  length = 50, nullable = false)
	private String name;
	@Column(name = "surname",  length = 50, nullable = false)
	private String surname;
	@OneToMany(mappedBy = "contact",cascade = CascadeType.ALL) //?? forse si riferisce al join della tabella contact con la tabella emails,e mette tutto in un set di dati
	/**
     * (Optional) Whether to apply the remove operation to entities that have
     * been removed from the relationship and to cascade the remove operation to
     * those entities.
     * @sin*/
	private Set<Email> emails;
	//@OneToMany(mappedBy = "contact")
	@OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)//Con fetch lazy stiamo dicendo che 
	//quando facciamo la query estrai solo i contatti e non phone e emails
	private Set<Phone> phones;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Set<Email> getEmails() {
		return emails;
	}
	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
//	public Contact addEmail(String email) {
//		if(this.emails == null) {
//			this.emails = new TreeSet<>();
//		}
//	
//		return this;
//	}

}
