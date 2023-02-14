<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="curRecipeId" value="${requestScope.recipeId}"/>
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
<div class="container">
    <div class="row mt-5 mb-2">
        <c:forEach var="recipe" items="${sessionScope.recipes}">
            <c:if test="${user0.role.name == 'DOCTOR'}">
            <c:if test="${recipe.id == curRecipeId}">
            <hr>
            <h2>Patient: ${recipe.patient.lastName} ${recipe.patient.firstName}</h2>
            <h2>Medication: ${recipe.medicine.medicine_name}</h2>
            <hr>
            <div class="col-lg-4">
                <form class="item" method="post" action="<c:url value='/Controller?command=update_recipe_command'/>">
                    <input type="hidden" name="recipeId" value="${recipe.id}">

                    <h3>Allowable dose in grams:
                        <label for="dose">
                            <input id="dose" name="dose" type="number"/>
                        </label>
                    </h3>
                    <h3>Valid until:
                        <label for="notes">
                            <input id="notes" name="notes" type="text"/>
                        </label>
                    </h3>
                    <input class="btn btn-secondary" name="button" type="submit" value="APPROVE"/>
                </form>
            </c:if>
                </c:if>
            <c:if test="${user0.role.name == 'PATIENT'}">
                <c:if test="${recipe.patient.username == user0.username}">
                <hr>
                <h2>Medication: ${recipe.medicine.medicine_name}</h2>
                <hr>
                <h3>Allowable dose in grams: ${recipe.max_dose} </h3>
                <h3>Valid until: ${recipe.notes} </h3>
                </c:if>
            </c:if>
        </c:forEach>
            </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
