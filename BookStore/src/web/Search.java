package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import temp.DAO;

/**
 * Servlet implementation class Search
 */

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
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
				
				showSearchPage(request, response);
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
	private void showSearchPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		RequestDispatcher dispatcher = null;
		String keyword = request.getParameter("keyword");
		Cookie[] cookies = request.getCookies();
		if(keyword == null) {
			for(Cookie c : cookies)
				if(c.getName().equals("keyword"))
					keyword = c.getValue();
		}
		System.out.println("keyword :  "+keyword);
		List<Book> lb =  dao.SearchBookByKeyword(keyword);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listBook",lb);
		dispatcher = request.getRequestDispatcher("SearchPage.jsp");
		
		dispatcher.forward(request, response);
	}
}
