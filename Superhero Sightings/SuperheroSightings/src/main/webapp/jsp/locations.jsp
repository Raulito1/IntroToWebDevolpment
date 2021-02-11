<%-- 
    Document   : locations
    Created on : Feb 10, 2021, 12:05:35 AM
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locations">Location</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organization</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sighting</a></li>
                </ul>    
            </div>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2>List Of Locations:</h2>
                        <table id="locationTable" class="table table-hover">
                            <!-- GET LOCATIONS FROM DB AND DISPLAY HERE -->
                            <c:forEach var="currentLocation" items="${locationList}">
                                <tr>
                                    <td>
                                        <a href="locationDetails?locationId=${currentLocation.locationId}">
                                            <c:out value="${currentLocation.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="editLocation-btn" onclick="editLocation(${currentLocation.locationId})">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>                                           
                        </table>
                    </div>
                    <div id="addLocationDiv" class="col-md-6">
                        <h2>Add A Location</h2>
                        <form class="form-horizontal" role="form" method="POST" action="addLocation">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name Of Location:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description Of Location:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" name="description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-street" class="col-md-4 control-label">Street:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="street"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="add-country" class="col-md-4 control-label">Country:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="country"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                                <div class="col-md-8">
                                    <input type="number" step="any" class="form-control" name="longitude"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                    <input type="number" step="any" class="form-control" name="latitude"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="editLocationDiv" class="col-md-6" style="display: none">
                        <h2>Edit Location</h2>
                        <form class="form-horizontal" role="form" method="POST" action="editLocation">
                            <input type="int" id="locationId" name="locationId" hidden/>
                            <div class="form-group">
                                <label for="edit-name" class="col-md-4 control-label">Name Of Location:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" id="name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description Of Location:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" name="description" id="description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-street" class="col-md-4 control-label">Street:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="street" id="street"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="edit-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city" id="city"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="edit-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state" id="state"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="edit-country" class="col-md-4 control-label">Country:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="country" id="country"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="edit-longitude" class="col-md-4 control-label">Longitude:</label>
                                <div class="col-md-8">
                                    <input type="number" step="any" class="form-control" name="longitude" id="longitude"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="edit-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                    <input type="number" step="any" class="form-control" name="latitude" id="latitude"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                    <button class="btn btn-default" type="button" id="cancelEditLocation-btn">Cancel</button>
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

