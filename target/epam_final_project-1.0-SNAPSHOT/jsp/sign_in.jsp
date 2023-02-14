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

<html lang = "ru">
<body class="sub_page">
<div class="hero_area">
<jsp:include page="header.jsp"/>
</div>
    <section class="contact_section layout_padding">
        <div class="container">
            <div class="col-md-6">
                <div class="d-flex mb-4 ml-4 ml-md-0">
                    <h2 class="custom_heading text-center">
                        SIGN IN
                    </h2>
                </div>
                <form method="post"  action="<c:url value='/Controller?command=sign_in_command'/>">
                    <div>
                        <label for="exampleInputUsername1">Username</label>
                        <div>
                            <input type="text" id="exampleInputUsername1" name="username" aria-describedby="usernameHelp" placeholder="Enter username">
                        </div>
                    </div>
                    <div>
                        <label for="exampleInputPassword1">Password</label>
                        <div>
                            <input type="password"  id="exampleInputPassword1" name = "password" placeholder="Enter password">
                        </div>
                        <br>
                        <div class="d-flex justify-content-center mt-4 ">
                            <input type="submit" value="SIGN IN"/>
                        </div>
                    </div>
                </form>
                <p>If you don't have an account,<a href="<c:url value='/Controller?command=go_to_sign_up_page_command'/>"> register</a></p>
            </div>
        </div>
    </section>
    <jsp:include page="footer.jsp"/>
<script type="text/javascript" src="../medi-html/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../medi-html/js/bootstrap.js"></script>
</body>
</html>