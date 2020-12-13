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
         <%@include file="/css/OrderHistory.css"%>
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
     <div class="display-order">
     	<c:forEach  var="order" items="${listOrder}">
     		<div class="order">
	     		<div class="order1">
	     			<p>Mã số: <c:out value=" ${order.id}"/></p>
	     			<p>Ngày đặt hàng:<c:out value=" ${order.date}"/> </p>
	     			<p>Giá: <c:out value=" ${order.price}"/> đ</p>
	     			
	     			
	     		</div>
	     		<div class="order2">
	     			
	     			<p>Địa chỉ: <c:out value=" ${order.address}"/></p>
	     			<p>Tình trạng: <c:out value=" ${order.status}"/></p>
	     			
	     		</div>
	     		<div class="view-detail">
	     			<button class="btn-view-detail btn" onclick="document.getElementById('${order.id}').style.display = 'block';">Xem chi tiết</button>
	     		</div>
	     		<div class="cancel">
	     			<form action="cancelOrder" method="post" id="form">
	     			<input type="hidden" name="orderID" value="${order.id }">
	     			<button type="buton" class="btn" onclick="onSubmit(${order.status})">Huỷ đơn hàng</button>
	     			</form>
	     		</div>
	     		</div>
     		<div id="${order.id }" class="order-detail">
     			<c:forEach items="${listOrderDetail }" var="orderdetail" varStatus="i">
     				<c:if test="${orderdetail.orderid == order.id }">
     					<p class="book-name">Tên sách : ${listName.get(i.index) } </p>
     					<p class="book-quantity"> Số lượng:    ${orderdetail.quantity }</p>
     				</c:if>
     			</c:forEach>
     			<button class="close btn" onclick="document.getElementById('${order.id}').style.display = 'none';">Đóng</button>
     		</div>
     	</c:forEach>
     </div>
    </main>						
</body>
</html>

<script type="text/javascript">

function open(id){
	
	document.getElementById(id).style.display="block";
}

function onSubmit(status){
	
	if(status.localeCompare("Chờ xác nhận") != 0){
		alert("Không thể hủy đơn này!")
		return;
	}
	else{
		
		document.getElementById("form").submit()
	}
	
}


</script>