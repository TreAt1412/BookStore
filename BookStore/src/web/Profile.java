package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.Customer;
import temp.DAO;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
			case "/Profile":
				ShowProfile(request, response);
				
				break;
			default:
				ShowProfile(request, response);
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
	private void ShowProfile(HttpServletRequest request, HttpServletResponse response)throws Exception{
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		
		Customer cus = dao.getCustomerByID(accountID);
		Address address = dao.getAddressByID(accountID);
		
		request.setAttribute("customer", cus);
		request.setAttribute("address", address);
		
		request.getRequestDispatcher("Profile.jsp").forward(request, response);
		
	}

}
