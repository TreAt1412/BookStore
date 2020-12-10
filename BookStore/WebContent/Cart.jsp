<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="homeStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        <%@include file="/css/cart.css"%>
    </style>
    <style>
        <%@include file="/css/homeStyle.css"%>
    </style>
</head>

<body>
    <header>
     <% String address = request.getAttribute("address").toString();
     String accountID = null;
					Cookie[] cookies = request.getCookies();
					
					if(cookies!=null){
						for(Cookie cookie:cookies){
							if(cookie.getName().equals("accountID"))
								accountID = cookie.getValue();
						}
						
					}
					Cookie pathCookie = new Cookie("prePath", "Home");
					response.addCookie(pathCookie);
					
		%>
        <!-- for logo page -->
        <div class="logo">
            <h1><i class="fa fa-book" aria-hidden="true"></i><span style="color:white">Book</span><span
                    style="color:rgb(226, 215, 63)">Store</span></h1>
        </div>
        <!-- for search form -->
        <div class="search-container">
            <form action="Search" method="post">
                <div class="enter-for-search">
                    <input type="text" placeholder=" Tìm kiếm sách mong muốn.." name="keyword">
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
    <!-- end header -->
    <main>
        <div class="nav-left">
            <div class="bar-1">
                <a href=""><i style="background-color: rgb(6, 104, 104);" class="fa fa-bookmark main-span"></i><span
                        class="main-span-1">Yêu thích</span></a>
            </div>
            <div class="bar-1">
                <a href=""><i style="background-color: rgb(233, 63, 51);" class="fa fa-bell main-span"></i><span
                        class="main-span-1">Thông báo</span></a>
            </div>
            <div class="bar-1">
                <a href=""><i style="background-color: rgb(162, 211, 26);" class="fa fa-gift main-span"
                        aria-hidden="true"></i></i><span class="main-span-1">Quà tặng</span></a>
            </div>
            <div class="bar-1">
                <a href=""><i style="color: black;" class="fa fa-bars main-span"></i><span class="main-span-1">Danh mục
                    </span></a>
            </div>
            <div class="list">
                <a href=""><span class="span-1">Khoa học và Công nghệ</span></a>
                <br>
                <a href=""><span class="span-1">Khoa học vũ trũ</span></a>
                <br>
                <a href=""><span class="span-1">Văn học</span></a>
                <br>
                <a href=""><span class="span-1">Tiểu thuyết</span></a>
                <br>
                <a href=""><span class="span-1">Lịch sử</span></a>
                <br>
                <a href=""><span class="span-1">Toán học</span></a>
                <br>
                <a href=""><span class="span-1">Truyện tranh</span></a>
                <br>
                <a href=""><span class="span-1">Kinh doanh và đầu tư</span></a>
            </div>
        </div>
        <div class="menu-bar">
            <div class="text-bar">
                <a href="Home">
                    <span>Trang chủ</span>
                </a>
            </div>
            <div class="text-bar">
                <a href="">
                    <span>Mới nhất</span>
                </a>
            </div>
            <div class="text-bar">
                <a href="">
                    <span>Bán chạy nhất nhất</span>
                </a>
            </div>
            <div class="text-bar">
                <a href="">
                    <span>Tác phẩm nổi tiếng</span>
                </a>
            </div>
        </div>
       
        <div id="display-cart">
            <div id="display-cart-1"><span>Giỏ hàng</span></div>
        </div>
       <c:if test="${fn:length(message) > 0}">
			<p style="color:red; margin-left:80px"> <c:out value = "${message}"/></p>
			</c:if>
        <div id="cart-products-inner">
            <ul class="cart-products">
                <c:forEach var="book" items="${listBook}" varStatus="myIndex">
                    <li class="cart-product" id="book-1">
                        <div class="product-inner">
                            <div class="img-product">
                                <img src="<c:out value=" ${book.url}" />" alt="">
                            </div>
                            <div class="product-content">
                                <div class="name-product">
                                    <p>
                                        <c:out value="${book.name}" />
                                    </p>
                                    <p>
                                        <c:out value="${book.price}" />đ</p>
                                </div>
                                <div class="click-buy">
                                    <div class="quantity">
                                        <label style="color: black; font-size: 14px;" for="">Số lượng</label>
                                        <span style="color: black; font-size: 16px;">
                                            <c:out value="${book.quantity}" /></span>
                                    </div>
                                    <div class="click-del">
                                        <button onclick="click_del_product()">Xóa</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ul>
            <div class="order-div">
            	<div class="order">
                <span style="padding-right: 100px;">Tổng tiền</span>
                <span style="color: #fe3834;">
                    <%out.println(request.getAttribute("totalAmount"));%>đ
                </span>
	            </div>
	            <div class="click-order">
	                <button id="open-form" onclick="document.getElementById('enter-inf-order').style.display='block';">Tiến
	                    hành đặt hàng</button>
	            </div>
	            </div>
        </div>
        <div id="enter-inf-order">
        	<div class="head-content">
        		<span>Thông tin đặt hàng</span>
        	</div>
        	<div class="turn-off">
        	<button onclick=" document.getElementById('enter-inf-order').style.display = 'none';">X</button>
        	</div>
        	<div class="enter-inf">
        		<div class="inf">
        			<form id="form-main" action="CreateOrder" method="post">
		                <div class="form-control">
		                    <div class="label">Tên người nhận</div>
		                    <div class="display">
		                        <input type="text" name="full-name">
		                    </div>
		                </div>
		                 <div class="click-for-buy">
			        		<div>Tạm tính: <%out.println(request.getAttribute("totalAmount"));%>đ</div>
			        		<br>
					        <div>Phí giao hàng: <span id="feeShip"></span></div>
					        <br>
					        <input type="hidden" name="price" value="<%=request.getAttribute("totalAmount") %>">
					        <div>Tổng giá: <span name = "totalPrice" id="totalPrice"><%out.println(request.getAttribute("totalAmount"));%>đ
					                    </span>
					        </div>
					        <br>
			                <div class="form-control">
			                    <div class="display click-add">
			                    	<button type="submit" style="float: left;">Đặt hàng</button>
		                    	</div>
					    	</div>
        				</div>
		                <div class="form-control">
		                    <div class="label">Số điện thoại</div>
		                    <div class="display">
		                        <input type="text" name="phone">
		                    </div>
		                </div>
		                <div class="form-control">
		                    <div class="label">Địa chỉ nhận</div>
		                    <div class="display">
		                        <input type="text" name="address"  value="<%=request.getAttribute("address") %>">
		                    </div>
		                </div>
		                <div class="form-control form-checkbox">
		                    <div class="label">Hình thức vận chuyển</div>
		                    <div class="display1">
		                        <input type="radio" name="type" onclick="document.getElementById('feeShip').innerHTML='20000đ';
		                        	document.getElementById('totalPrice').innerHTML = eval('20000+' + ${totalAmount}) +'đ';" value="1">
		                        <label for="">Giao hàng nhanh</label>
		                        <br>
		                        <input type="radio" name="type" onclick="document.getElementById('feeShip').innerHTML='10000đ';
		                        	document.getElementById('totalPrice').innerHTML = eval('10000+' + ${totalAmount});" value="2">
		                        <label for="">Giao trong ngày</label>
		                        <br>
		                        <input type="radio" name="type" onclick="document.getElementById('feeShip').innerHTML='5000đ';
		                        	document.getElementById('totalPrice').innerHTML = eval('5000+' + ${totalAmount}) + 'đ';" value="3">
		                        <label for="">Giao tiêu chuẩn</label>
		                    </div>
		                </div>
            		</form>
        		</div>    		
        	</div>
        </div>
    </main>

    <footer>
        <div class="main-content">
            <div class="left box">
                <h2>
                    Giới thiệu</h2>
                <div class="content">
                    <p>
                        Học viện Công nghệ Bưu chính Viễn thông là một tổ chức Nghiên cứu
                        - Giáo dục Đào tạo có thương hiệu, uy tín đứng top 5 đại học Việt Nam.
                    </p>
                    <div class="social">
                        <a href="https://www.facebook.com/duong.ricksanchez/"><span
                                class="fab fa-facebook-f"></span></a>
                        <a href="https://twitter.com/login"><span class="fab fa-twitter"></span></a>
                        <a href="instagram.com/r1ck_xh/"><span class="fab fa-instagram"></span></a>
                        <a href="https://www.youtube.com"><span class="fab fa-youtube"></span></a>
                    </div>
                </div>
            </div>
            <div class="center box">
                <h2>
                    Địa chỉ</h2>
                <div class="content">
                    <div class="place">
                        <span class="fas fa-map-marker-alt"></span>
                        <span class="text">PTIT, Hà Đông-Hà Nội</span>
                    </div>
                    <div class="phone">
                        <span class="fas fa-phone-alt"></span>
                        <span class="text">+848-76543210</span>
                    </div>
                    <div class="email">
                        <span class="fas fa-envelope"></span>
                        <span class="text">xuanhieu1999@gmail.com</span>
                    </div>
                </div>
            </div>
            <div class="right box">
                <h2>
                    Liên hệ</h2>
                <div class="content">
                    <form action="#">
                        <div class="email">
                            <div class="text">
                                Email *
                            </div>
                            <input type="email" required>
                        </div>
                        <div class="msg">
                            <div class="text">
                                Nội dung *
                            </div>
                            <input type="text" required>
                            <br />
                            <div class="btn">
                                <button type="submit">Gửi</button>
                            </div>
                            <div class="bottom">
                                <center>
                                    <span class="credit">Created By <a
                                            href="https://www.facebook.com/duong.ricksanchez/">RickSanchez</a> | </span>
                                    <span class="far fa-copyright"></span> 2020 All reserved.
                                </center>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </footer>

    <script>
        function click_del_product() {
            document.getElementById("book-1").style.display = "none";
        }
        function setShipFee(name, amount) {
        	debugger;
            if (name == "1") {
                document.getElementById("feeShip").text = "20000";
                document.getElementById("totalPrice").innerHTML = eval("20000+"+ amount);
            }
            else if (name == "2") {
            	document.getElementById("feeShip").text = "10000";
                document.getElementById("totalPrice").innerHTML = eval("10000+"+ amount);
            }
            else {
            	document.getElementById("feeShip").text = "5000";
                document.getElementById("totalPrice").innerHTML = eval("5000+"+ amount);
            }
        }
        function display-form() {
            document.getElementById("enter-inf-order").style.display = "block";
        }
    </script>
</body>

</html>