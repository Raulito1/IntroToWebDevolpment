<%-- 
    Document   : contactDetails
    Created on : Nov 10, 2020, 1:12:53 AM
    Author     : raulalvarado
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container"
        <h1>Contact Details</h1>
        <hr/>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/displaySearchPage">Search</a>
                </li>
            </ul>
        </div>
                <p>
                    Name: <c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/>
                </p>
                <p>
                    Company: <c:out value="${contact.company}"/>
                </p>
                <p>
                    Phone: <c:out value="${contact.phone}"/>
                </p>
                <p>
                    Email: <c:out value="${contact.email}"/>
                </p>
    </div>
                <!-- Placed at the end of the document so the pages load faster -->
                <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script src="${pageContext.request.comtextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
