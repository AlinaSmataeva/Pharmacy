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
<c:set var= "medication" value="${requestScope.medication}"/>
<c:set var= "isRecipe" value="${requestScope.isRecipe}"/>
<div class="container">
<img class='img-profile-fluid'  src="${pageContext.request.contextPath}/img/${medication.imageName}" width="300" height="300">
<h3>${medication.medicine.medicine_name}</h3>
    <h4>Importer: ${medication.importer.imp_name}</h4>
<c:if test="${medication.medicine.recipe == false}">
<form class="item" method="post" action="<c:url value='/Controller?command=add_medication_command'/>">
    <input type="hidden" name="medicationId" value="${medication.id}">
    <input type="hidden" name="id_user" value="${doctor.id}">
    <h3>Amount
        <label for="amount">
            <input id="amount" name="amount" type="number"/>
        </label>
    </h3>
    <input class="btn btn-secondary" name="button" type="submit" value="Заказать"/>
</form>
</c:if>
<c:if test="${medication.medicine.recipe == true}">
    <form class="item" method="post" action="<c:url value='/Controller?command=add_recipes_command'/>">
        <input type="hidden" name="medicationId" value="${medication.medicine.id}">
        <input type="hidden" name="id_user" value="${doctor.id}">
        <input class="btn btn-secondary" name="button" type="submit" value="ПОЛУЧИТЬ РЕЦЕПТ"/>
    </form>
    <c:forEach var="recipe" items="${sessionScope.recipes}">
        <c:if test="${medication.medicine.medicine_name == recipe.medicine.medicine_name}">
        <c:if test="${medication.dose <= recipe.max_dose}">
            <form class="item" method="post" action="<c:url value='/Controller?command=add_medication_with_recipe_command'/>">
                <input type="hidden" name="medicationId" value="${medication.id}">
                <input type="hidden" name="medicineId" value="${medication.medicine.id}">
                <input type="hidden" name="id_user" value="${doctor.id}">
                <h3>Количество
                    <label for="amount2">
                        <input id="amount2" name="amount" type="number"/>
                    </label>
                </h3>
                <input class="btn btn-secondary" name="button" type="submit" value="ЗАКАЗАТЬ"/>
            </form>
        </c:if>
        <c:if test="${medication.dose > recipe.max_dose}">
            <h3>Maximum dose exceeded! (${medication.dose} > ${recipe.max_dose})</h3>
        </c:if>
        </c:if>
    </c:forEach>
</c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
