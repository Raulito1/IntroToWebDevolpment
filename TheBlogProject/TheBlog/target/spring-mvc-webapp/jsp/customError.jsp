<%-- 
    Document   : customError
    Created on : Jan 14, 2021, 11:35:03 PM
    Author     : raulalvarado
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Blog</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>The Blog</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li>
                        <a href="${pageContext.request.contextPath}/">Home</a>
                    </li>
                </ul>
            </div>
                    <div>
                        <h1>An Error has occurred ......</h1>
                        <h3>${errorMessage}</h3>
                    </div>
        </div>
               <!-- jQuery (necessary for Bootstrap's Javascript plugins) --> 
               <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
               <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </body>
</html>
