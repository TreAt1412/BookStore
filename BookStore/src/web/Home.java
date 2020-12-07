package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temp.DAO;

/**
 * Servlet implementation class Home
 */

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
    	dao = new DAO();
      
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String action = request.getServletPath();
		
		try {
			switch (action) {
			
			default:
				showHomePage(request, response);
				break;
			}
		} catch (SQLException e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		RequestDispatcher dispatcher = null;
		System.out.println(111);
		request.setAttribute("listBook", dao.getAllBooks());
		dispatcher = request.getRequestDispatcher("HomePage.jsp");
		
		dispatcher.forward(request, response);
	}
	
}
