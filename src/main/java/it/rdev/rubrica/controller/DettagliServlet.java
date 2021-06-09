package it.rdev.rubrica.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.rdev.rubrica.model.ContactDao;
import it.rdev.rubrica.model.entities.Contact;
import it.rdev.rubrica.model.entities.Email;
import it.rdev.rubrica.model.entities.Phone;

/**
 * Servlet implementation class DettagliServlet
 */
@WebServlet("/DettagliServlet")
public class DettagliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactDao ok=new ContactDao();
		Integer id=Integer.parseInt(request.getParameter("id"));
		List <Email> emails=ok.dettagliEmail(id);
		List<Phone>cell =ok.dettagliPhone(id);
		//System.out.println(emails.get(0).getEmail());
		//System.out.println(cell.get(0).getPhone());
//		for (int i=0;i<emails.size();i++) {
//			System.out.println(emails.get(i).getEmail());
//		}
		
		


		request.setAttribute("emails", emails);
		request.setAttribute("cell", cell);		
		request.getRequestDispatcher("dettagli.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
