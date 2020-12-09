package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temp.DAO;

/**
 * Servlet implementation class OrderHistory
 */

public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistory() {
        super();
        dao = new DAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/Profile":
				showProfile(request, response);
				
				break;
			default:
				showProfile(request, response);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void showProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
