package it.rdev.rubrica.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.rdev.rubrica.dto.User;
import it.rdev.rubrica.model.ContactDao;
import it.rdev.rubrica.model.entities.Contact;
import it.rdev.rubrica.model.entities.Email;
import it.rdev.rubrica.model.entities.Phone;

/**
 * Servlet implementation class Inserimento
 */
@WebServlet("/Inserimento")
public class Inserimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inserimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Inserimento");
		ContactDao ok=new ContactDao();
		Contact i1=new Contact();
		i1.setName(request.getParameter("username"));
		i1.setSurname(request.getParameter("surname"));	
		Set<Email> emails=new HashSet<>();
		Set<Phone> phones=new HashSet<>();
		Email oggetto_email=new Email();
		Phone oggetto_phone=new Phone();

		
	    // Email
		oggetto_email.setEmail(request.getParameter("email")); //inserisco l'email 
	    oggetto_email.setContact(i1);
		emails.add(oggetto_email);
		
		//Phone
		oggetto_phone.setPhone(request.getParameter("phone"));
		oggetto_phone.setContact(i1);
		phones.add(oggetto_phone);
		
		//Persist
		i1.setEmails(emails);
		i1.setPhones(phones);
//		Set<Email> emails_1= i1.getEmails();
//		Iterator it_emails =emails_1.iterator();
//		while(it_emails.hasNext()){
//	        System.out.print(it_emails.next().toString()+",");
//	    }
//		
//		System.out.println("sto qua");
		ok.insert(i1);
		response.sendRedirect(request.getContextPath()+("/"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
