<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="user0" value="${sessionScope.user}"/>
<c:set var="recipes0" value="${sessionScope.recipesCount}"/>
<c:set var="medicationsList" value="${sessionScope.medicationDataList}"/>
        <!-- header section strats -->
        <header class="header_section">
            <div class="container-fluid">
                <nav class="navbar navbar-expand-lg custom_nav-container pt-3">
                    <a class="navbar-brand" href="main.jsp">
                        <span>
              Aibolit Pharmacy
            </span>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
                            <ul class="navbar-nav  ">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/Controller?command=go_to_main_page_command'/>">Main</a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${user0.role.name == 'PATIENT' or user0.role.name == 'DOCTOR'}">
                        <a href="<c:url value='/Controller?command=sign_out_command'/>" class="nav-link">Sign out</a>
                        </c:if>
                        <c:if test="${user0.role.name == 'GUEST'}">
                            <a href="<c:url value='/Controller?command=go_to_sign_in_page_command'/>" class="nav-link">Sign in</a>
                        </c:if>
                    </li>
                    <c:if test="${user0.role.name == 'PATIENT' or user0.role.name == 'DOCTOR'}">
                    <li class="nav-item">
                        <a href="<c:url value='/Controller?command=go_to_profile_page_command'/>" class="nav-link">Profile</a>
                    </li>
                    </c:if>
                    <c:if test="${user0.role.name == 'PATIENT'}">
                    <li class="nav-item">
                        <a href="<c:url value='/Controller?command=go_to_medication_list_page_command'/>" class="nav-link">Medications</a>
                    </li>
                    </c:if>
                    <c:if test="${user0.role.name == 'PATIENT'}">
                    <li class="nav-item">
                            <a class="nav-link">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</a>
                    </li>
                    </c:if>
                    <c:if test="${user0.role.name == 'PATIENT'}">
                        <li class="nav-item">
                            <form method="post" action="<c:url value='/Controller?command=go_to_basket_page_command' />">
                                <input type="hidden" name="id_user" value="${user0.id}">
                                <input class = "btn-success" type="submit" value="Basket: ${medicationsList.size()}"/>
                            </form>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">&nbsp;&nbsp;&nbsp;</a>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="<c:url value='/Controller?command=delete_all_from_basket_page_command' />">
                                <input type="hidden" name="id_user" value="${user0.id}">
                                <input class="btn-success" type="submit" value="Clean basket"/>
                            </form>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">&nbsp;&nbsp;&nbsp;</a>
                        </li>
                    </c:if>
                    <c:if test="${user0.role.name == 'DOCTOR'}">
                    <li class="nav-item">
                        <a href="<c:url value='/Controller?command=go_to_visits_page_command'/>" class="nav-link">Visits</a>
                    </li>
                    </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="collapse navbar-collapse">
                        <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
                            <ul class="navbar-nav  ">
                    <c:if test="${user0.role.name == 'PATIENT'}">
                        <c:if test="${recipes0 > 0}">
                            <li class="nav-item">
                                <a class="nav-link"> You have recipe for </a>
                            </li>
                            <c:forEach var="recipe" items="${sessionScope.recipes}">
                                <c:if test="${recipe.recipeStatus == true}">
                                    <li class="nav-item">
                                        <a href="<c:url value='/Controller?command=go_to_recipes_page_command_2'/>" class="nav-link">${recipe.medicine.medicine_name} </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:if>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>