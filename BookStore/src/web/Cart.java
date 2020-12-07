package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.Book;
import temp.DAO;

/**
 * Servlet implementation class Cart
 */

public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
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
			case "/Cart":
				showCart(request, response);
				
				break;
			default:
				showCart(request, response);
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
	
	private void showCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		
		List<Book> cart = dao.getCartByCustomerID(accountID);
		int totalAmount= 0;
		for(Book b : cart) {
			totalAmount += b.getPrice();
		}
		Address address = dao.getAddressByID(accountID);
		request.setAttribute("address", address.getDetail());
		request.setAttribute("totalAmount", totalAmount);
		request.setAttribute("listBook", cart);
		System.out.println("11");
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}
}
