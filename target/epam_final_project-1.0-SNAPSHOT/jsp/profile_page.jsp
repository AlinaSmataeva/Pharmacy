<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="doctor" value="${sessionScope.user}"/>

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
<hr>
<div class="container">
<h2>Profile</h2>
    <hr>
<h4>First name: ${doctor.firstName}</h4>
<h4>Surname: ${doctor.lastName}</h4>
<h4>Username: ${doctor.username}</h4>
<h4>Email: ${doctor.email}</h4>
    <hr>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="../medi-html/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../medi-html/js/bootstrap.js"></script>
</body>
</html>
