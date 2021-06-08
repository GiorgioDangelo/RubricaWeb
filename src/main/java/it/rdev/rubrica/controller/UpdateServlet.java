package it.rdev.rubrica.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.rdev.rubrica.model.ContactDao;
import it.rdev.rubrica.model.entities.Contact;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean valore;
		Contact i1=new Contact();
		Integer id=Integer.parseInt(request.getParameter("ID"));
		i1.setId(id);
		i1.setName(request.getParameter("name"));
		i1.setSurname(request.getParameter("surname"));
		ContactDao ok=new ContactDao();
		valore=ok.findByID(id);
		if (valore==true) {
			ok.update(i1);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("errore.jsp").forward(request, response);
		}
		System.out.println(valore);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
