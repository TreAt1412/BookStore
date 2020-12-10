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

import model.OderDetail;
import model.Order;
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
			case "/OrderHistory":
				showOrderHistory(request, response);
				
				break;
			default:
				showOrderHistory(request, response);
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
	protected void showOrderHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int accountID = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("accountID"))
					accountID = Integer.parseInt(cookie.getValue());
			}
			
		}
		
		List<Order> lo = dao.getOrder(accountID);
		List<OderDetail> lod = dao.getOderDetail(accountID);
		List<String> lbookName = new ArrayList<String>();
		for(OderDetail od : lod) {
			lbookName.add(dao.getBookByID(od.getBookID()).getName());
		}
		
		request.setAttribute("listOrder", lo);
		request.setAttribute("listOrderDetail", lod);
		request.setAttribute("listName", lbookName);
		request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
	}

}
