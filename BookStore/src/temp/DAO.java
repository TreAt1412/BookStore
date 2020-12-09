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

import javafx.beans.binding.ListBinding;
import model.Address;
import model.Book;
import model.Comment;
import model.Customer;
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
	
	public int checkPassword(String password, int accountID) throws SQLException{
		String Hpass = String.valueOf(password.hashCode());
		String sql = "select * from customer where password=? and id =?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, Hpass);
        ps.setInt(2, accountID);
        int acc= -1;
        
        while (re.next()) {
       	 acc = re.getInt(1);
        }
        return acc;
	}
	public int updateInfo(int accountID, String address, String password, String name, String phone, String email) throws SQLException{
		if(checkPassword(password, accountID) == -1)
			return 0;
		String sql = "update customer\r\n" + 
				"set password = ?, name=?, phone=?, email=?" + 
				"where id = ?;";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(password.hashCode()));
        ps.setString(2, name);
        ps.setString(3, phone);
        ps.setString(4, email);
        ps.setInt(5, accountID);
        ps.executeQuery();
        
        sql="update address\r\n" + 
        		"set detail=?" + 
        		"where customerID = ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, address);
        ps.setInt(2, accountID);
        return 1;
	}
	
	public void comment(int accountID, int bookID, String content) throws SQLException{
		String sql ="insert into comment (content, customerID, bookID)\r\n" + 
				"values (?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, content);
        ps.setInt(2, accountID);
        ps.setInt(3, bookID);
        ps.executeQuery();
	}
	
	public int createAccount(String name, String username, String password, String phone, String email, String address) throws SQLException{
		if(checkUsernameExist(username) == -1)
			return -1;
		if(checkPhoneExist(phone) == -1)
			return -2;
		if(checkEmailExist(email) == -1)
			return -3;
		
		String sql="insert into customer (username, password, name, phone, email)\r\n" + 
				"values  (?,?, ?, ?,?)";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, username);
        ps.setString(2, String.valueOf(password.hashCode()));
        ps.setString(3, name);
        ps.setString(4, phone);
        ps.setString(5, email);
        ps.executeUpdate();
        re = ps.getGeneratedKeys();
        int accountID =0;
		if(re.next()) {
			accountID = re.getInt(1);
		}
		sql = "insert into address (detail, customerID) values(?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, address);
        ps.setInt(2, accountID);
        ps.executeUpdate();
        
        sql = "insert into cart (customerID)   values(?)";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, accountID);
        ps.executeUpdate();
        return 1;
	}
	
	public int checkUsernameExist(String username) throws SQLException{
		String sql = "select * from customer where username=?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        re=ps.executeQuery();
        while(re.next()) {
        	return -1;
        }
        return 1;
	}
	public int checkPhoneExist(String phone) throws SQLException{
		String sql = "select * from customer where phone=?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, phone);
        re=ps.executeQuery();
        while(re.next()) {
        	return -1;
        }
        return 1;
	}
	public int checkEmailExist(String email) throws SQLException{
		String sql = "select * from customer where email=?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);
        re=ps.executeQuery();
        while(re.next()) {
        	return -1;
        }
        return 1;
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
	
	public Customer getCustomerByID(int customerid) throws SQLException{
		
		String sql ="select * from customer where id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
       	ps.setInt(1,customerid);
       	re = ps.executeQuery();
       	while(re.next()) {
       		String name = re.getString(4);
       		String phone = re.getString(5);
       		String  email = re.getString(6);
       		return new Customer(customerid, null, null, name, phone, email);
       	}
		return null;
	}
	
	
	
	//------------------------------------------------------------------------------------------
	public void deleteBookByCustomer(int accountID, int bookID) throws SQLException{
		String sql = "delete cartdetail.* from cartdetail , cart\r\n" + 
				"where \r\n" + 
				"cartdetail.cartID = cart.id\r\n" + 
				"and cart.customerID = ?\r\n" + 
				"and cartdetail.bookID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,accountID);
		ps.setInt(2, bookID);
		ps.executeUpdate();
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
	
	
	
	public int getQuantityBook(int id) throws Exception{
		Book b = getBookByID(id);
		return b.getQuantity();
	}
	public int getQuantityByCus(int customerID, int bookID) throws SQLException{
		String sql = "select cartdetail.quantity\r\n" + 
				"from cart, cartdetail\r\n" + 
				"where cart.id = cartdetail.cartID\r\n" + 
				"and cart.customerID = ?\r\n" + 
				"and cartdetail.bookID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.setInt(2, bookID);
        re = ps.executeQuery();
        int quantity = 0;
        while(re.next()) {
        	quantity = re.getInt(1);
        }
        return quantity;
	}

	
	public List<Book> getAllBooks() throws SQLException{
		
		String sql = "SELECT * FROM book";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Book> listBook = null;
		

		try {
			statement = connection.prepareStatement(sql);

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
	
	//-----------------------------------------------------------------------------
	
	public List<Comment> getCommentByBook(int bookid) throws SQLException{
		List<Comment> lc = new ArrayList<Comment>();
		String sql = "SELECT * FROM bookstore.comment where bookID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, bookid);
        re = ps.executeQuery();
        while(re.next()) {
        	int id = re.getInt(1);
        	String content = re.getString(2);
        	int customerID = re.getInt(3);
        	int bookID = re.getInt(4);
        	lc.add(new Comment(id, content, customerID, bookID));
        	
        }
        return lc;
	}
	
	//-----------------------------------------------------------------------------
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
	

	
	public int[] addToCart(int accountID, int bookID, int quantity)throws  Exception{
		int cartID =0;
		
		String sql = "SELECT id FROM cart WHERE";
        sql+=" customerID = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountID);
        re = ps.executeQuery();
        while (re.next()) {
          	 cartID= re.getInt(1);
           }
        int slk = getQuantityBook(bookID);
        int slcd = getQuantityByCus(accountID, bookID);
        if(slk < slcd + quantity)
        	return new int[] {-1, bookID};
       
        if(cartID != 0) {
        	sql = "insert into cartdetail (cartID, bookID, quantity)";
            sql+="values (?,?,?)";
            sql += "on duplicate key update quantity = quantity + ?";
             ps = connection.prepareStatement(sql);
             ps.setInt(1, cartID);
             ps.setInt(2, bookID);
             ps.setInt(3, quantity);
             ps.setInt(4, quantity);
             int result  = ps.executeUpdate();
            
             if(result >= 0)
            	 return new int[] {1};
             return new int[] {-1, bookID};
        }
        ps.close();
        return new int[] {-1, bookID};
	}
	
	public void subQuantity(int id, int quantity) throws SQLException {
		String sql = "update book\r\n" + 
				"set quantity = quantity - ?\r\n" + 
				"where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, quantity);
		ps.setInt(2, id);
		ps.executeUpdate();
		
	}
	
	public int[] createOrder(int price, int type, String address, int customerID ) throws Exception {
		
		List<Book> lb = getCartByCustomerID(customerID);
		for(Book b : lb) {
			if(b.getQuantity() > getQuantityBook(b.getId())) {
				return new int[] {0, b.getId()};
			}
		}
		
		java.util.Date date=new java.util.Date();
		Date d = new Date(date.getTime());
		String sql = "insert into order1 (date, price, type, address, customerID,status) values ";
		sql +="(?, ?, ?, ?, ?,? )";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setDate(1, d);
		ps.setInt(2, price);
		ps.setInt(3, type);
		ps.setString(4, address);
		ps.setInt(5, customerID);
		ps.setString(6, "Chờ xác nhận");
	
		int x = ps.executeUpdate();
		int orderID =0;
		re = ps.getGeneratedKeys();
		if(re.next()) {
			orderID = re.getInt(1);
		}
		ps.close();
		for(Book b : lb) {
			
			sql = "insert into oderdetail (bookID, quantity, orderID) values ";
			sql +="( ?, ?, ? )";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, b.getId());
			ps.setInt(2, b.getQuantity());
			ps.setInt(3, orderID);
			ps.executeUpdate();
			
			subQuantity(customerID, b.getQuantity());
					
		}
		
		//delete cart
		sql = "delete cartdetail from cartdetail,cart where";
		sql += " cartdetail.cartID = cart.id ";
		sql += "and cart.customerID = ?";
		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, customerID);
		ps.executeUpdate();
		
		return new int[] {1,0};
		
	}
}
