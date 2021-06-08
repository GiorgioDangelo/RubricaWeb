package it.rdev.rubrica.controller;

import java.io.IOException;
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
		
		Set<Email> emails=new TreeSet<>();
		
		Email oggetto_email=new Email();
		oggetto_email.setEmail(request.getParameter("email")); //inserisco l'email 
	    oggetto_email.setContact(i1);  
		//emails.add(oggetto_email);
		i1.setEmails(emails);
//		Set<Email> emails_1= i1.getEmails();
//		Iterator it_emails =emails_1.iterator();
//		while(it_emails.hasNext()){
//	        System.out.print(it_emails.next().toString()+",");
//	    }
//		
//		System.out.println("sto qua");
		ok.insert(i1);
		
		List <Contact> show_users=ok.findAllCriteria();
		request.setAttribute("users", show_users);
		//Integer la=i1.getId();)
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
