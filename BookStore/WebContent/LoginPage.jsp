<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        <%@include file="/css/style.css"%>
    </style>
</head>

<body>
    <div class="hero">
        <div class="form-box">
            <div class="button-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" onclick="login()">Log in</button>
                <button type="button" class="toggle-btn" onclick="register()">Register</button>
            </div>
            
           <c:if test="${fn:length(loginFail) > 0}">
			<p style="color:red; margin-left:80px"> <c:out value = "${loginFail}"/></p>
			</c:if>
			<c:if test="${fn:length(registerFail) > 0}">
			<p style="color:red; margin-left:80px"> <c:out value = "${registerFail}"/></p>
			</c:if>
            <form id="log-in" action="<%=request.getContextPath()%>/dologin" method="post" class="input-group">
                <input type="text" name="username" class="input-field" placeholder="User Name" required>
                <input type="password" name="password" class="input-field" placeholder="Enter Password" required>
                <input type="checkbox" class="check-box"> <span>Nhớ mật khẩu</span>
                <button type="submit" class="submit-btn">Đăng Nhập</button>
            </form>
          
            <form id="register" action="<%=request.getContextPath()%>/doregister" class="input-group">
                <input type="text" class="input-field" placeholder="Name" name ="name" required>
                <input type="text" class="input-field" placeholder="User Name" name="username" required>
                <input type="password" class="input-field" placeholder="Enter Password" name="password"
                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                    title="Mật khẩu cần ít nhất 8 ký tự gồm chữ hoa, chữ thường và số " required>
                <input type="text" class="input-field" placeholder="Address" name="address" required>
                <input type="email" class="input-field" placeholder="Email" name="email"
                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Hãy điền đúng email" required>
                <input type="text" class="input-field" placeholder="Phone Number" name="phone" pattern="[0-9]{10}"
                    title="Số điện thoại gồm 10 chứ số" required>
                <input type="checkbox" class="check-box" required="required"> <span>Tôi đồng ý với các điều khoản !</span>
                <button type="submit" class="submit-btn">Đăng Ký</button>
            </form>

        </div>
    </div>
    <script>
        var x = document.getElementById("log-in")
        var y = document.getElementById("register")
        var z = document.getElementById("btn")
        function register() {
            x.style.left = "-400px";
            y.style.left = "50px";
            z.style.left = "110px";
        }
        function login() {
            x.style.left = "50px";
            y.style.left = "450px";
            z.style.left = "0px";
        }

    </script>
</body>

</html>