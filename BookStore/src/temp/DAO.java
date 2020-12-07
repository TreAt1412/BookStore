package temp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import model.Address;
import model.Book;
import model.OderDetail;

public class DAO {
	public static Connection connection;
	public static ResultSet re;

	public DAO() {
		try {
			String connectString = "jdbc:mysql://localhost:3306/bookstore?useSSL=false&AllowPublicKeyRetrieval=true";
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = (Connection) DriverManager.getConnection(connectString, "root", "123qweasd");
	        
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public int checkAccount(String username, String password) throws SQLException{
		 String passH = String.valueOf(password.hashCode());
		
		 String sql = "SELECT * FROM customer WHERE";
         sql+=" username = ? AND password = ?";
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setString(1, username);
         ps.setString(2, passH);
         re = ps.executeQuery();
         
      
         int accountID= -1;
         
         while (re.next()) {
        	 accountID= re.getInt(1);
         }
        
         ps.close();
         return accountID;
	}
	
	public Address getAddressByID(int customerid) throws Exception {
		Address a = null;
		String sql = "SELECT * FROM address WHERE";
        sql+=" customerID=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerid);
        
        re = ps.executeQuery();
        while (re.next()) {
       	 	String detail = re.getString(2);
       	 	a = new Address(0,detail, customerid);
        }
        ps.close();
        return a;
	}
	public Book getBookByID(int id) throws Exception{
		Book b = null;
		String sql = "SELECT * FROM book WHERE";
        sql+=" id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        re = ps.executeQuery();
        while (re.next()) {
       	 	String url = re.getString(2);
       	 	int quantity = re.getInt(3);
       	 	String name = re.getString(4);
       	 	String author = re.getString(5);
       	 	int price = re.getInt(6);
       	 	String detail = re.getString(7);
       	 	b = new Book(id, url, quantity, name, author, price, detail);
        }
        ps.close();
        return b;
	}
	
	public List<Book> getCartByCustomerID(int customerid) throws Exception{
		String sql = "SELECT bookID, quantity FROM cartdetail, cart WHERE";
        sql+=" cartdetail.cartID = cart.id and ";
        sql+= "cart.customerID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerid);
        int index = 0;
        int[] bookIdA = new int[1000];
        int[] quantityA = new int[1000];
        re = ps.executeQuery();
        while(re.next()) {
        	bookIdA[index] = re.getInt(1);
        	quantityA[index] = re.getInt(2);
        	index++;
        }
        
       
        List<Book> lb = new ArrayList<>();
        for(int i =0;i<index;i++) {
        	Book b = getBookByID(bookIdA[i]);
        	lb.add(new Book(b.getId(), b.getUrl(),quantityA[i], b.getName(), b.getAuthor(),b.getPrice()*quantityA[i] ,b.getDetail()));
        }
       
        ps.close();
        return lb;
		
	}
	public List<Book> getAllBooks() throws SQLException{
		
		String sql = "SELECT * FROM book";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Book> listBook = null;
		

		try {
			statement = connection.prepareStatement(sql);

			System.out.println(sql);
			rs = statement.executeQuery();

			listBook = new ArrayList<Book>();
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String url = rs.getString(2);
				int quantity = rs.getInt(3);
				String name = rs.getString(4);
				String author = rs.getString(5);
				int price = rs.getInt(6);
				String detail = rs.getString(7);
				listBook.add(new Book(bookId, url, quantity, name, author, price, detail));
			}

		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			statement.close();
		}
	 
		return listBook;
	}
	public List<Book> SearchBookByKeyword(String keyword) throws SQLException{
		List<Book> listBook = getAllBooks();
		for(int i =0; i< listBook.size();i++) {
			if(!listBook.get(i).getName().toLowerCase().contains(keyword.toLowerCase()))
				listBook.remove(i);
		}
        return listBook;
	}
	public int addToCart(int accountID, int bookID, int quantity)throws  Exception{
		int cartID =0;
		
		String sql = "SELECT id FROM cart WHERE";
        sql+=" customerID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(accountID));
        re = ps.executeQuery();
        System.out.println(ps);
        
        while (re.next()) {
       	 cartID= re.getInt(1);
        }
        
        if(cartID != 0) {
        	sql = "insert into cartdetail (cartID, bookID, quantity)";
            sql+="values (?,?,?)";
            sql += "on duplicate key update quantity = quantity + 1";
             ps = connection.prepareStatement(sql);
             ps.setInt(1, cartID);
             ps.setInt(2, bookID);
             ps.setInt(3, quantity);
             System.out.println(ps);
             
             int result  = ps.executeUpdate();
             System.out.println("result = "+ result);
             if(result >= 0)
            	 return 1;
             return -1;
        }
        ps.close();
		return -1;
	}
	public int createOrder(int price, int type, String address, int customerID ) throws Exception {
		
		java.util.Date date=new java.util.Date();
		Date d = new Date(date.getTime());
		String sql = "insert into order1 (date, price, type, address, customerID) values ";
		sql +="(?, ?, ?, ?, ? )";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setDate(1, d);
		ps.setInt(2, price);
		ps.setInt(3, type);
		ps.setString(4, address);
		ps.setInt(5, customerID);
	
		int x = ps.executeUpdate();
		int orderID =0;
		re = ps.getGeneratedKeys();
		if(re.next()) {
			orderID = re.getInt(1);
		}
		ps.close();
		List<Book> lb = getCartByCustomerID(customerID);
		for(Book b : lb) {
			
			sql = "insert into oderdetail (bookID, quantity, orderID) values ";
			sql +="( ?, ?, ? )";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, b.getId());
			ps.setInt(2, b.getQuantity());
			ps.setInt(3, orderID);
			ps.executeUpdate();
	
					
		}
		
		//delete cart
		sql = "delete cartdetail from cartdetail,cart where";
		sql += " cartdetail.cartID = cart.id ";
		sql += "and cart.customerID = ?";
		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, customerID);
		ps.executeUpdate();
		System.out.println("Update success....");
		
		return 0;
		
	}
}
