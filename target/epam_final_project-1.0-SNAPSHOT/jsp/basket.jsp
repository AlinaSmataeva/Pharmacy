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
    <div class="row mt-5 mb-2">
        <c:forEach var="medications" items="${requestScope.basketList}">
            <div class="col-lg-4">
                <img class='img-profile-fluid'  src="${pageContext.request.contextPath}/img/${medications.impMedicine.imageName}" width="300" height="300">
                <h2>${medications.impMedicine.medicine.medicine_name}</h2>
                <h4>Importer: ${medications.impMedicine.importer.imp_name}</h4>
                <h2>${medications.impMedicine.dose} g</h2>
                <h2>Total price for ${medications.amount}: ${medications.sumCost} $</h2>
                <c:if test="${medications.impMedicine.medicine.recipe == true}">
                    <h2>Your have a recipe!</h2>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<div class="container">
<c:if test="${requestScope.basketList.size() > 0}">
<form method="post" action="<c:url value='/Controller?command=delete_all_from_basket_page_command'/>">
    <input type="hidden" name="id_user" value="${doctor.id}">
    <input class="btn btn-secondary" type="submit" value="ORDER"/>
</form>
</c:if>
<c:if test="${requestScope.basketList.size() == 0}">
    <h3>Your basket is empty!..</h3>
</c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
