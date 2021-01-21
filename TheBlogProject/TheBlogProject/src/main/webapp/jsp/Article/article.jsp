<%-- 
    Document   : article
    Created on : Jan 18, 2021, 9:41:31 PM
    Author     : raulalvarado
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Squatch Watch</title>

        <!--CDN for Bootstrap 4-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--custom CSS-->
        <link rel="stylesheet" href="css/customStyles.css">
        <style>
            .form-control, .list-group-item, .btn {
                color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;
            }
        </style>
    </head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>

    <div class="container">

        <nav class="navbar navbar-expand-lg navbar-light mb-2">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white active" href="${pageContext.request.contextPath}/Article">Blogs</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/Item">Tags</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/Category">Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/User">Users</a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.userPrincipal.name == null}">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/Login">Log In</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a class="nav-link text-white" href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </c:if>
                    </li>

                </ul>
            </div>
        </nav>

                    <h1 class="text-center" style="">Blog Page <br>
            <c:if test="${not empty error}">
                <p> ${error}</p>
            </c:if>
        </h1>

        <sec:authorize access="hasRole('ROLE_COLLECTORS')">
            <div class="text-right" style="color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;">
                <a class="" href="${pageContext.request.contextPath}/displayAddArticleForm">
                    <button class="btn">Add Article</button>
                </a>
            </div>
        </sec:authorize>

                    <div class="main-content container" style="color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;">

            <div class="scrolling-container">

                <!--this represents the most recent article that is displayed at the 
                top of the page-->
                <!--${pageContext.request.userPrincipal.name}-->
                <!--Author Name: ${pageContext.request.userPrincipal.name} |--> 
                
                <div class="mb-3" >
                    <c:forEach var="currentArticle"  items="${articleList}" >
                        <p class="text-center">Date: <c:out value="${currentArticle.articleDate}"/></p>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <p class="text-center">
                                <a href="${pageContext.request.contextPath}/displayEditArticleForm?articleId=${currentArticle.articleId}">
                                    <button class="btn">Edit</button>
                                </a>

                                <a href="${pageContext.request.contextPath}/deleteArticle?articleId=${currentArticle.articleId}">
                                    <button class="btn">Delete</button>
                                </a>
                            </p>
                        </sec:authorize>
                        <div class="">
                            <div class="">
                                ${currentArticle.content}
                            </div>
                        </div>
                            <br>
                        <hr>
                        <br>
                    </c:forEach>
                </div>

            </div>

        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
