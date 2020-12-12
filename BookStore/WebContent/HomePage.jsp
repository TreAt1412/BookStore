<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*" import="java.io.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        <%@include file="/css/homeStyle.css"%>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>
<body>
    <!-- header -->
    <header>
        <% String accountID = null;
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
        <c:if test="${fn:length(message) > 0}">
			<p style="color:red; margin-left:80px"> <c:out value = "${message}"/></p>
			</c:if>
        	
        
        <div class="display-book">
            <h2>Những tác phẩm hiện có</h2>
            <c:forEach var="book" items="${listBook}">
                <div class="one-book">
                    <div class="img-book">
                        <img src="<c:out value=" ${book.url}" />">
                    </div>
                    <div class="content-book">
                        <p>
                            <c:out value="${book.name}" />
                        </p>
                        <p style="font-size: 16px;">
                            <strong><c:out value="${book.price}" />đ</strong>
                        </p>
                    </div>
                    <div class="button-add">
                        <form action="AddToCart" method="post">
                            <input type="hidden" name="bookID" value="${book.id}" />
                            <input type="hidden" name="quantity" value="1" />
                            <input type="hidden" name="accountID" value="<%=accountID%>" />
                            <button type="submit">Mua</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
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
</body>
</html>