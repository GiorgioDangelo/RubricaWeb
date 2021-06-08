package it.rdev.rubrica.model.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBUtil {
	
	public static final String RUBRICA_WEB_PU = "RubricaWeb"; //E' il nome che deve essere uguale al nome
	//persistence che si trova in persistence.xml <persistence-unit name="RubricaWeb"
	//transaction-type="RESOURCE_LOCAL">
    //Entity manager è una classe propria di Javax

	public static EntityManager getEntityManager(String s) {
		return Persistence.createEntityManagerFactory(s).createEntityManager();   //Si va a cercare in RubricaWeb il file persistence.xml
/* Return an instance of <code>CriteriaBuilder</code> for the creation of
     * <code>CriteriaQuery</code> objects.*/
	}

}
