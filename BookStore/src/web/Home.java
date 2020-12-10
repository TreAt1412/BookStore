package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	
	private void showHomePage(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = null;
		String message = "";
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("message")) {
				message = c.getValue();
				break;
			}
		}
		
		if(message.equals("Success")) {
			request.setAttribute("message", "Thêm thành công");
		}
		else if(message.equals("")) {
			request.setAttribute("message", "");
		}
		else {
			int bookid = Integer.parseInt(message);
			String bookName = dao.getBookByID(bookid).getName();
			request.setAttribute("message", "Thêm sách "+ bookName +" thất bại!");
		}
		response.addCookie(new Cookie("message", ""));
		
		request.setAttribute("listBook", dao.getAllBooks());
		dispatcher = request.getRequestDispatcher("HomePage.jsp");
		
		dispatcher.forward(request, response);
	}
	
}
