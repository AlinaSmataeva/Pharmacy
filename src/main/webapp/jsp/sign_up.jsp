<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- slider stylesheet -->
<link rel="stylesheet" type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css" />

<!-- fonts style -->
<link href="https://fonts.googleapis.com/css?family=Dosis:400,500|Poppins:400,700&display=swap" rel="stylesheet" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="../medi-html/css/bootstrap.css" />

<!-- Custom styles for this template -->
<link href="../medi-html/css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="../medi-html/css/responsive.css" rel="stylesheet" />

<html lang = "en">
<body class="sub_page">
<div class="hero_area">
<jsp:include page="header.jsp"/>
</div>
<section class="contact_section layout_padding">
    <div class="container">
        <div class="col-md-6">
            <div class="d-flex mb-4 ml-4 ml-md-0">
                <h2 class="custom_heading text-center">
                    SIGN UP
                </h2>
            </div>
            <form method="post" action="<c:url value='/Controller?command=sign_up_command'/>">
                <p>Please, fill in form for creation account.</p>
                    <hr>
                    <div>
                        <label><b>Username</b></label>
                    </div>
                    <input type="text" placeholder="Enter username" name="username" pattern="[a-z]{4,15}" title="4 to 15 lowercase letters" required>

                    <div>
                        <label><b>First name</b></label>
                    </div>
                    <input type="text" placeholder="Enter first name" name="first_name" required>

                    <div>
                        <label><b>Surname</b></label>
                    </div>
                    <input type="text" placeholder="Enter surname" name="last_name" required>

                    <div>
                        <label><b>Email</b></label>
                    </div>
                    <input type="email" placeholder="Enter email" name="email" pattern="(?=^.{5,25}$)^([a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+)$" title="incorrect email"required>

                    <div>
                        <label><b>Password</b></label>
                    </div>
                    <input type="password" placeholder="Enter password" name="password" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})" title = "This regular expression refers to a pattern with at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”)."required>

                    <div>
                        <label><b>Phone</b></label>
                    </div>
                    <input type="text" placeholder="Enter phone" name="phone" pattern="^\+375 \((17|29|33|44)\) [0-9]{3}-[0-9]{2}-[0-9]{2})$" title="enter phone in format +375(xx) xxx-xx-xx" required>
                    <div class="d-flex justify-content-center mt-4 ">
                        <input type="submit" value="SIGN UP"/>
                    </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="../medi-html/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../medi-html/js/bootstrap.js"></script>
</body>
</html>
