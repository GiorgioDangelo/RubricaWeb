package it.rdev.rubrica.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.rdev.rubrica.dto.User;
import it.rdev.rubrica.model.ContactDao;
import it.rdev.rubrica.model.entities.Contact;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User()
				.setPassword(request.getParameter("password"))
				.setUsername(request.getParameter("username"));
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if( user.getUsername() != null && user.getUsername().equals("root") ) {
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);
		}
		
		String action = request.getParameter("action");   //Prende la stringa che gli è stato passato da secure è controlla se è eguale a 
		//logout 
		if(action != null && action.equals("logout") ) { // se è vero invalida la sessione
			HttpSession session = request.getSession();
			System.out.println("logout");
			session.invalidate();
			
		}
		System.out.println("Home");
		List <Contact> users=ContactDao.findAllCriteria();
		request.setAttribute("users", users);
		request.getRequestDispatcher("home.jsp").forward(request, response); // E vai in home
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
