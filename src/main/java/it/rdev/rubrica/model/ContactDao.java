package it.rdev.rubrica.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import it.rdev.rubrica.model.entities.Contact;
import it.rdev.rubrica.model.entities.Email;
import it.rdev.rubrica.model.entities.Phone;
import it.rdev.rubrica.model.util.DBUtil;

public class ContactDao {

	public static Contact getContact(Integer id) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		return em.find(Contact.class, id);
	}

	public static void insert(Contact c) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(c);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void insertEma(Email o) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(o);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void update(Contact c) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(c);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Contact c) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		EntityTransaction trans = em.getTransaction();
	
		try {
			trans.begin();
			em.remove(em.merge(c));
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
     
	// Mi restituisco la tupla con quell'id se esiste
	public static List<Contact> findByID_(Integer id) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		String qString = "Select c from Contact c where c.id=:name";  //:name praticamente
		// è il nome che gli passiamo nella select
		TypedQuery<Contact> q = em.createQuery(qString, Contact.class);
		q.setParameter("name", id); //qui viene valorizzato name
		List<Contact> contacts = null;
		try {
			contacts = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return contacts;
	}
	// Controllo se esiste la tupla con questo id e mi ritorno un valore booleano in cui mi dice se è stato trovato o meno
	public static boolean findByID(Integer id) {
		boolean valore=false;
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		String qString = "Select c from Contact c where c.id=:name";  //:name praticamente
		// è il nome che gli passiamo nella select
		TypedQuery<Contact> q = em.createQuery(qString, Contact.class);
		q.setParameter("name", id); //qui viene valorizzato name
		List<Contact> contacts = null;
		try {
			contacts = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		//(contacts.iterator().next().getId()!=null)
		// Se la query mi ha restituito un risultato significa che ha trovato l'id
		//quindi l'utente da modificare esiste
		if (contacts.size()>0) {
			valore=true;
		}
		return valore;
	}
	
	public static List<Contact> findAll() {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		String qString = "Select c from Contact c LEFT JOIN c.phones p";
		TypedQuery<Contact> q = em.createQuery(qString, Contact.class);
		List<Contact> contacts = null;
		
		try {
			contacts = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return contacts;
	}

	
	public static List<Email> dettagliEmail(Integer id) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		String qString = "Select e from Contact c join c.emails e where c.id=:name";
		//TypedQuery<Contact> q = em.createQuery(qString, Contact.class);
		TypedQuery<Email> q = em.createQuery(qString, Email.class);
		q.setParameter("name", id);
		//List<Contact> contacts = null;
		List<Email>emails =null;
		try {
			emails = q.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return emails;
	}
	public static List<Phone> dettagliPhone(Integer id) {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		String qString = "Select e from Contact c join c.phones e where c.id=:name";
		TypedQuery<Phone> q = em.createQuery(qString, Phone.class);
		q.setParameter("name", id);
		List<Phone>cell =null;
		try {
			cell = q.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return cell;
	}
	
	public static List<Contact> findAllNamedQuery() {
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
		return em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
	}
	
	public static List<Contact> findAllCriteria() { //Query con jpa
		
		// DBUtil.RUBRICA_WEB_PU è una stringa ed è RubricaWeb
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);//questo metodo ritorna un'instanza di entity manager (QUa si blocca)
		//Che si riferisce all'inizializzazione della persistenza
		CriteriaBuilder cb = em.getCriteriaBuilder();
		/* CriteriaBuilder Return an instance of <code>Metamodel</code> interface for access to the
     * metamodel of the persistence unit.*/
		CriteriaQuery<Contact> c = cb.createQuery(Contact.class); // Create a <code>CriteriaQuery</code> object that returns a tuple of 
	     // objects as its result.  Praticamente stiamo creando istanze per ogni tupla del database
		Root<Contact> cont = c.from(Contact.class); //Create and add a query root corresponding to the given entity,
		cont.fetch("phones", JoinType.LEFT); //Fa il join con la tabella phones
		cont.fetch("emails", JoinType.LEFT); //Join con la tabella emails
		c.select(cont).distinct(true);//Torna il risultato della query
		TypedQuery<Contact> q = em.createQuery(c);
		/**
	     * Create an instance of <code>Query</code> for executing a criteria
	     * update query.
	     * @param updateQuery  a criteria update query object
	     * @return the new query instance*/
		return q.getResultList();
	}
	
public static List<Contact> findAllData() { //Query con jpa
		
		// DBUtil.RUBRICA_WEB_PU è una stringa ed è RubricaWeb
		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);//questo metodo ritorna un'instanza di entity manager (QUa si blocca)
		//Che si riferisce all'inizializzazione della persistenza
		CriteriaBuilder cb = em.getCriteriaBuilder();
		/* CriteriaBuilder Return an instance of <code>Metamodel</code> interface for access to the
     * metamodel of the persistence unit.*/
		CriteriaQuery<Contact> c = cb.createQuery(Contact.class); // Create a <code>CriteriaQuery</code> object that returns a tuple of 
	     // objects as its result.  Praticamente stiamo creando istanze per ogni tupla del database
		Root<Contact> cont = c.from(Contact.class); //Create and add a query root corresponding to the given entity,
		cont.fetch("phones", JoinType.INNER); //Fa il join con la tabella phones
		cont.fetch("emails", JoinType.INNER); //Join con la tabella emails
		c.select(cont).distinct(true);//Torna il risultato della query
		TypedQuery<Contact> q = em.createQuery(c);
		/**
	     * Create an instance of <code>Query</code> for executing a criteria
	     * update query.
	     * @param updateQuery  a criteria update query object
	     * @return the new query instance*/
		return q.getResultList();
	}
	
//	public static List<Contact> findByName(String name) {
//		EntityManager em = DBUtil.getEntityManager(DBUtil.RUBRICA_WEB_PU);
//		String qString = "Select c from Contact c where c.name=:name";  //:name praticamente
//		// è il nome che gli passiamo nella select
//		TypedQuery<Contact> q = em.createQuery(qString, Contact.class);
//		q.setParameter("name", name); //qui viene valorizzato name
//		List<Contact> contacts = null;
//		try {
//			contacts = q.getResultList();
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			em.close();
//		}
//		return contacts;
//	}
}
