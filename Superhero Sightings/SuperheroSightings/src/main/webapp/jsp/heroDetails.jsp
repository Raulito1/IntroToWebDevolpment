<%-- 
    Document   : heroDetails
    Created on : Feb 10, 2021, 12:04:59 AM
    Author     : raulalvarado
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">SUPERHERO SIGHTINGS</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/superheros">Superhero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superpowers">Superpower</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organization</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Location</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sighting</a></li>
                </ul>    
            </div>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2><c:out value="${person.name}"/></h2>
                        <p><c:out value="${person.description}"/></p>
                        <!-- PICTURE STRETCH GOAL -->
                    </div>
                    <div class="col-md-6">
                        <h2>Superpowers</h2>
                        <ul>
                            <c:forEach var="currentPower" items="${person.superpowers}">
                                <li><c:out value="${currentPower.name}"/></li>
                                </c:forEach>
                        </ul>
                        <h2>Organizations</h2>
                        <ul>
                            <c:forEach var="currentOrg" items="${organizationList}">
                                <li><c:out value="${currentOrg.name}"/></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

        
    </body>
</html>