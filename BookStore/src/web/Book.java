package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import temp.DAO;

/**
 * Servlet implementation class Book
 */
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DAO dao;
    public Book() {
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
			case "/Book":
				ShowBook(request, response);
				
				break;
			default:
				ShowBook(request, response);
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
	private void ShowBook(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String bookidTemp = request.getParameter("bookID");
		int bookid =0;
		Cookie[] cookies = request.getCookies();
		String message = "";
		
		if(bookidTemp==null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("bookID"))
					bookid = Integer.parseInt(c.getValue());
				if(c.getName().equals("message")) {
					message = c.getValue();
				}
			}
		}
		else {
			bookid = Integer.parseInt(bookidTemp);
		}
		
		System.out.println("Bookid ="+ bookid);
		model.Book b = dao.getBookByID(bookid);
		
		if(message.equals("Success")) {
			request.setAttribute("message", "Thêm thành công");
		}
		else if(message.equals("")) {
			request.setAttribute("message", "");
		}
		else {
			String bookName = b.getName();
			request.setAttribute("message", "Thêm sách "+ bookName +" thất bại!");
		}
		
		response.addCookie(new Cookie("message", ""));
		
		List<Comment> lc = dao.getCommentByBook(bookid);
		List<String> ln = new ArrayList<String>();
		for(Comment c : lc) {
			ln.add(dao.getCustomerByID(c.getCustomerID()).getName());
		}
		
		request.setAttribute("listComment", lc);
		request.setAttribute("listName", ln);
		request.setAttribute("book", b);
		request.getRequestDispatcher("Book.jsp").forward(request, response);
		
		
	}

}
