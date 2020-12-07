package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temp.DAO;

/**
 * Servlet implementation class Login
 */

public class Common extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Common() {
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
			case "/CreateOrder":
				createOrder(request, response);
				System.out.println("action: " + action);
				break;
			case "/register":
				//showRegisterPage(request, response);
				break;
			case "/doregister":
				//insertAccount(request, response);
				break;
			case "/login":
				//showLoginPage(request, response);
				break;
			case "/dologin":
				loginAccount(request, response);
				break;
			default:
				showLoginPage(request, response);
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.sendRedirect("LoginPage.jsp");
	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, SQLException, ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int accountID = dao.checkAccount(username, password);
		RequestDispatcher dispatcher = null;
		if(accountID == -1) {
			dispatcher = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("loginFail", "Sai ten dang nhap hoac mat khau!");
			dispatcher.forward(request, response);
		}
		else {
			Cookie loginCookie = new Cookie("accountID",String.valueOf(accountID));
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			
			response.sendRedirect("Home");
		}
	}

	private void createOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
		int price = Integer.parseInt(request.getParameter("price"));
		
		int type = Integer.parseInt(request.getParameter("type"));
	
		String address = request.getParameter("address");
		
		
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		
		dao.createOrder(price, type, address, accountID);
		response.sendRedirect("Cart");
	}























}
