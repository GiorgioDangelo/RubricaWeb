package it.rdev.rubrica.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.rdev.rubrica.model.ContactDao;
import it.rdev.rubrica.model.entities.Contact;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/secure/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Cancellazione");
		ContactDao ok=new ContactDao();	
		//Mi prendo l'id dell'utente e mi faccio restituire in una lista l'oggetto Contact
		//con quell'id 
		Integer id=Integer.parseInt(request.getParameter("id"));
		List <Contact> delete_users=ok.findByID_(id);
		ok.delete(delete_users.get(0));
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
