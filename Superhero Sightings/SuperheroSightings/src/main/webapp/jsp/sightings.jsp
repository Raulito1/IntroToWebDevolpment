<%-- 
    Document   : sightings
    Created on : Feb 10, 2021, 12:06:04 AM
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superpowers">Superpower</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superheros">Superhero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Location</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organization</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightings">Sighting</a></li>
                </ul>    
            </div>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2>List Of Sightings</h2>
                        <table id="sightingTable" class="table table-hover">                            
                            <th width="40%">Location</th>
                            <th width="30%">Date</th>
                            <th width="15%"><!-- EDIT --></th>
                            <th width="15%"><!-- DELETE --></th>                            
                            <!-- GET SIGHTINGS FROM DB AND DISPLAY HERE -->
                            <c:forEach var="currentSighting" items="${sightingList}">
                                <tr>
                                    <td>
                                        <a href="sightingDetails?sightingId=${currentSighting.sightingId}">
                                            <c:out value="${currentSighting.location.name}"/>
                                        </a>
                                    </td>
                                    <td>                                        
                                        <c:out value="${currentSighting.date}"/>
                                    </td>

                                    <td>
                                        <a class="editSighting-btn" onclick="editSighting(${currentSighting.sightingId})">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>                                           
                        </table>
                    </div>
                    <div id="addSightingDiv" class="col-md-6">
                        <h2>Add A Sighting</h2>
                        <form class="form-horizontal" role="form" method="POST" action="addSighting">
                            <div class="form-group">
                                <label for="add-superheroes" class="col-md-4 control-label">Superheroes</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="persons" required>
                                        <option selected disabled>Select A Hero</option>
                                        <!-- GET SUPERHEROES FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentHero" items="${personList}">
                                            <option value="${currentHero.personId}"><c:out value="${currentHero.name}"/></option>
                                        </c:forEach>                                        
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location" class="col-md-4 control-label">Location:</label>
                                <div class="col-md-8">
                                    <select class="form-control" name="location" required>
                                        <!-- GET LOCATIONS FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <option value="${currentLocation.locationId}"><c:out value="${currentLocation.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-date" class="col-md-4 control-label">Date:</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" name="date" placeholder="Enter in yyyy-MM-dd format" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="editSightingDiv" class="col-md-6" style="display: none">
                        <h2>Edit Sighting</h2>
                        <form class="form-horizontal" role="form" method="POST" action="editSighting">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Current Hero Sighted:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" id="currentPersons" disabled></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-superheroes" class="col-md-4 control-label">Superheroes</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="persons" id="persons" required>
                                        <option selected disabled>Select Multiple Heroes</option>
                                        <!-- GET SUPERHEROES FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentHero" items="${personList}">
                                            <option value="${currentHero.personId}"><c:out value="${currentHero.name}"/></option>
                                        </c:forEach>                                        
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Current Location:</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="currentLocation" disabled></input>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-location" class="col-md-4 control-label">Location:</label>
                                <div class="col-md-8">
                                    <select class="form-control" name="location" id="location" required>
                                        <!-- GET LOCATIONS FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <option value="${currentLocation.locationId}"><c:out value="${currentLocation.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-date" class="col-md-4 control-label">Date:</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" name="date" id="date" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                    <button class="btn btn-default" type="button" id="cancelEditSighting-btn">Cancel</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superherosightings.js"></script>
    </body>
</html>