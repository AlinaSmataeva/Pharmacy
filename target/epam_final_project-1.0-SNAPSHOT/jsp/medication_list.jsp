<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="user0" value="${sessionScope.user}"/>
<!-- slider stylesheet -->
<link rel="stylesheet" type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css" />

<!-- fonts style -->
<link href="https://fonts.googleapis.com/css?family=Dosis:400,500|Poppins:400,700&display=swap" rel="stylesheet" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="medi-html/css/bootstrap.css" />

<!-- Custom styles for this template -->
<link href="medi-html/css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="medi-html/css/responsive.css" rel="stylesheet" />

<html lang = "en">
<body class="sub_page">
<div class="hero_area">
<jsp:include page="header.jsp"/>
</div>
<section class="product_section layout_padding">
    <div class="d-flex justify-content-center">
        <h2 class="custom_heading">
            Our Medications
        </h2>
    </div>
    <div class="container layout_padding2">
    <div class="row mt-5 mb-2">
        <c:forEach var="medications" items="${requestScope.medicineList}">
            <div class="col-lg-6">
                <img src="${pageContext.request.contextPath}/img/${medications.imageName}" width="300" height="300" alt="">
                <div class="detail-box">
                    <h2>${medications.medicine.medicine_name}</h2>
                    <p>Importer: ${medications.importer.imp_name}<br>
                    ${medications.dose} g<br>
                            ${medications.cost} $</p>
                    <form method="post" action="<c:url value='/Controller?command=set_medication_page_command'/>">
                        <input type="hidden" name="medicationId" value="${medications.id}">
                        <input class=".btn-outline-info" type="submit" value="CHOOSE"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="../medi-html/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../medi-html/js/bootstrap.js"></script>
</body>
</html>