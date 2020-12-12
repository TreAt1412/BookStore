package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
				break;
			case "/register":
				showRegisterPage(request, response);
				break;
			case "/doregister":
				showRegisterPage(request, response);
				break;
			case "/login":
				//showLoginPage(request, response);
				break;
			case "/dologin":
				loginAccount(request, response);
				break;
			case "/changeprofile":
				updateInfor(request,response);
				break;
			case "/cancelorder":
				deleteOrder(request,response);
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
			request.setAttribute("loginFail", "Sai tên đăng nhập hoặc mật khẩu!");
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
		
		if(type == 1)
			price += 20000;
		else if(type ==2)
			price += 10000;
		else
			price += 5000;
		
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		
		int[] check = dao.createOrder(price, type, address, accountID);
		if(check[0] == 1) {
			response.addCookie(new Cookie("message", "Success"));
			System.out.println("Thanh cong");
		}
		else {
			response.addCookie(new Cookie("message",String.valueOf(check[1])));
		}
		response.sendRedirect("Cart");
	}




	public void showRegisterPage(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String name= request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address= request.getParameter("address");
		String email= request.getParameter("email");
		String phone= request.getParameter("phone");
		
		int check = dao.createAccount(name, username, password, phone, email, address);
		
		System.out.println("Check = " + check);
		RequestDispatcher dispatcher = null;
		if(check == -1) {
			dispatcher = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("registerFail", "Tài khoản đã được đăng kí!");
			dispatcher.forward(request, response);
		}
		else if(check == 1){
			dispatcher = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("registerFail", "Tạo tài khoản thành công");
			dispatcher.forward(request, response);
		}
		else if(check == -2) {
			dispatcher = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("registerFail", "Số điện thoại đã được đăng kí!");
			dispatcher.forward(request, response);
		}
		else if(check == -3) {
			dispatcher = request.getRequestDispatcher("LoginPage.jsp");
			request.setAttribute("registerFail", "Email đã được đăng kí!");
			dispatcher.forward(request, response);
		}
	}
	public void updateInfor(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String name= request.getParameter("name");
		String npassword = request.getParameter("newpassword");
		String opassword = request.getParameter("oldpassword");
		String address= request.getParameter("address");
		String email= request.getParameter("email");
		String phone= request.getParameter("phone");
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		int check = dao.updateInfo(accountID, address, opassword , npassword, name, phone, email);
		
		if(check == 1) {
			response.addCookie(new Cookie("message", "Success"));
			System.out.println("Thanh cong");
		}
		else if(check == 0){
			response.addCookie(new Cookie("message","password"));
		}
		else if(check == -1) {
			response.addCookie(new Cookie("message", "phone"));
		}
		else {
			response.addCookie(new Cookie("message", "email"));
		}
		response.sendRedirect("Profile");
	}
	
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		dao.cancelOrder(orderID);
		
		response.sendRedirect("/OrderHistory");
		
	}

}
