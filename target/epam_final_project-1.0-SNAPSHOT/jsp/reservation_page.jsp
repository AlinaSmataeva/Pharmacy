<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="doctor" value="${sessionScope.user}"/>
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
<div class="container">
    <hr>
    <h3>Your order:</h3>
    <hr>
    <c:forEach var="medications" items="${requestScope.medicineList}">
            <div class="col-lg-4">
                <h4>Medication name: ${medications.medicine.medicine_name}</h4>
                <h4>Dose: ${medications.dose}</h4>
                <h4>Price: ${medications.cost}</h4>
                <hr>
            </div>
    </c:forEach>
<form class="item" method="post" action="<c:url value='/Controller?command=reservation_command'/>">
    <input type="hidden" name="id_user" value="${doctor.id}">
    <input type="hidden" name="medicineList" value="${requestScope.medicineList}">
    <h4>Enter your address:
        <label for="address">
            <input id="address" name="address" type="text"/>
        </label>
    </h4>
    <input class="btn btn-secondary" type="submit" value="Оформить заказ"/>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>