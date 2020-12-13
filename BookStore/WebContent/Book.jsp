<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book</title>
 <style>
        <%@include file="/css/homeStyle.css"%>
        <%@include file="/css/Book.css"%>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>
<body>
 <header>
        <% String accountID = null;
					Cookie[] cookies = request.getCookies();
					
					if(cookies!=null){
						for(Cookie cookie:cookies){
							if(cookie.getName().equals("accountID"))
								accountID = cookie.getValue();
						}
						
					}
					Cookie pathCookie = new Cookie("prePath", "Book");
					response.addCookie(pathCookie);
					
		%>
        <!-- for logo page -->
        <div class="logo">
            <h1><span style="color:white">Book</span> <span style="color:rgb(226, 215, 63)">Store</span></h1>
        </div>
        <!-- for search form -->
        <div class="search-container">
            <form action="Search" method="post">
                <div class="enter-for-search">
                    <input type="text" placeholder=" Tìm kiếm sách mong muốn.." name="keyword" value="${keyword}">
                </div>
                <div class="button-search">
                    <button type="submit"><i class="fa fa-search"></i><span>Tìm</span></button>
                </div>
            </form>
        </div>
        <!-- for nav bar -->
        <div class="header-link">
            <div class="book-order">
                <form class="myform" action="OrderHistory" method="post">
                    <button class="control-btn">
                        <i class="fa fa-shopping-bag fa-lg"></i>
                        <span>Đơn hàng </span>
                    </button>
                </form>
                <form class="myform" action="Cart" method="post">
                    <button class="control-btn">
                        <i class="fa fa-shopping-cart fa-lg"></i>
                        <span>Giỏ hàng</span>
                    </button>
                </form>
            </div>
            <!-- dropdown tai khoan -->
            <div class="dropdown">
                <a href="" class="tk">
                    <i class="fa fa-user-circle fa-lg"></i><span>Tài khoản</span>
                </a>
                <div class="dropdown-content">
                    <a href=""><i class="fa fa-cog"></i><span>Cài đặt</span></a>
                    <a href=""><i class="fa fa-id-badge"></i><span>Liên hệ</span></a>
                    <a href=""><i class="fa fa-power-off"></i><span>Đăng xuất</span></a>
                </div>
            </div>
            <!-- end  dropdown tai khoan -->
        </div>
        <!-- end for nav bar -->
    </header>
    <main>
    	<a id="home" href="Home">
             <span><-Quay về trang chủ</span>
        </a>
        
        <c:if test="${fn:length(message) > 0}">
			<p style="color:red; margin-left:80px"> <c:out value = "${message}"/></p>
			</c:if>
			
			
		<div class="detail-book">
			<img  class ="book-img" src="${book.url }">
			<div class="detail">
				<p><c:out value = "${book.name}"/></p>
				<p>Tác giả:  <c:out value = "${book.author}"/></p>
				<p>Số lượng: <c:out value = "${book.quantity}"/></p>
				<p>Giá :  <c:out value = "${book.price}"/> đ</p>
				<button type="button" class="change add-button" onclick="changeQuantity('1')">Thêm</button>
				<input id="quantity-input" class="quantity-input" type="text" name="quantity" value="1"></input>
				<button type="button" class="change sub-button" onclick="changeQuantity('-1')">Bớt</button>
				<form id="form" action="AddToCart" method="post">
					<input type="hidden" name="quantity" id="quantity-parameter" value="">
					<input type="hidden" name="bookID" value="${book.id }">
					<input type="hidden" name="accountID" value="<%=accountID%>" />
					<button class="submit" type="button" onclick="onSubmit(${book.quantity})">Đặt hàng</button>
				</form>
			</div>
			
			
		</div>
		<div class="comment">
			<h2>Nhận xét</h2>
			<form action="comment" method="post" id="comment-form">
			<input type="hidden" name="bookID" value="${book.id}">
			<input type="hidden" name="accountID" value="<%=accountID%>" >
			<textarea rows="4" cols="40" name="content" required="required" placeholder="Nhận xét về sản phẩm ..."></textarea>
			<button id="button-comment" type="submit" >Comment</button>
			</form>
			<c:forEach var="comment" items="${listComment}" varStatus="i">
				<div class="comment-item">
					<p>${listName.get(i.index)}</p>
					<p>${comment.content }</p>
				</div>
			</c:forEach>
		</div>
    </main>
</body>
</html>

<script type="text/javascript">
	function changeQuantity(type){
		console.log(type ==1)
		if(type==1){
			var x = document.getElementById("quantity-input").value
			document.getElementById("quantity-input").value = eval(x + "+ 1")
		}
		else{
			var x = document.getElementById("quantity-input").value
			if(x>1){
				document.getElementById("quantity-input").value= eval(x + "- 1")
			}
			
		}
	}
	function onSubmit(quantity){
		var x = document.getElementById("quantity-input").value
		if(eval(x) > eval(quantity)){
			alert("Số lượng sách không hợp lệ!")
			return;
		}
		else{
			document.getElementById("quantity-parameter").value = x
			document.getElementById("form").submit()
		}
		
	}
</script>