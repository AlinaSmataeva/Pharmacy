<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="../medi-html/css/bootstrap.css" />

<!-- fonts style -->
<link href="https://fonts.googleapis.com/css?family=Dosis:400,500|Poppins:400,700&display=swap" rel="stylesheet" />
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
    <h2>Визиты</h2>
    <hr>
        <c:forEach var="visit" items="${sessionScope.visits}">
            <h4>Visit number ${visit.id}</h4>
            <h4>Visit date: ${visit.visitDate}</h4>
            <h4>Specialist: ${visit.doctor.firstName} ${visit.doctor.lastName}</h4>
            <h4>Patient: ${visit.patient.firstName} ${visit.patient.lastName}</h4>
            <c:forEach var="recipe" items="${sessionScope.recipes}">
                <c:if test="${recipe.patient.username == visit.patient.username}">
                <c:if test="${recipe.recipeStatus == false}">
                    <form class="nav-item" method="post" action="<c:url value='/Controller?command=go_to_recipes_page_command'/>">
                        <input type="hidden" name="recipeId" value="${recipe.id}">
                        <input class="btn btn-secondary" name="button" type="submit" value="Запрошен рецепт"/>
                    </form>
                </c:if>
                </c:if>
            </c:forEach>
        </c:forEach>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
