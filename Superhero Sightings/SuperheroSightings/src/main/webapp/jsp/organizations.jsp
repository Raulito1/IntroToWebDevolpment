<%-- 
    Document   : organizations
    Created on : Feb 10, 2021, 12:07:03 AM
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizations">Organization</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sighting</a></li>
                </ul>    
            </div>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2>List Of Organizations:</h2>
                        <table id="organizationTable" class="table table-hover">
                            <!-- GET ORGANIZATIONS FROM DB AND DISPLAY HERE -->
                            <c:forEach var="currentOrg" items="${organizationList}">
                                <tr>
                                    <td>
                                        <a href="organizationDetails?organizationId=${currentOrg.organizationId}">
                                            <c:out value="${currentOrg.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="editOrganization-btn" onclick="editOrganization(${currentOrg.organizationId})">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteOrganization?organizationId=${currentOrg.organizationId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>                                  
                        </table>
                    </div>
                    <div id="addOrganizationDiv" class="col-md-6">
                        <h2>Add An Organization</h2>
                        <form class="form-horizontal" role="form" method="POST" action="addOrganization">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="Organization Name" required=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" name="description" placeholder="Enter a description of Heros organization"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="phone" placeholder="(000)000-0000" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-email" class="col-md-4 control-label">Email:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="email" placeholder="(EmailName@Email.com)"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location" class="col-md-4 control-label">Location:</label>
                                <div class="col-md-8">
                                    <select class="form-control" name="location" required>
                                        <!-- GET LOCATIONS FROM DB AND DISPLAY HERE -->
                                        <c:forEach  var="currentLocation" items="${locationList}">
                                            <option value="${currentLocation.locationId}"><c:out value="${currentLocation.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-members" class="col-md-4 control-label">Select A Member:</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="persons">
                                        <option selected disabled>Select A Hero:</option>
                                        <!-- GET SUPERHEROES FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentHero" items="${personList}">
                                            <option value="${currentHero.personId}"><c:out value="${currentHero.name}"/></option>
                                        </c:forEach>            
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="editOrganizationDiv" class="col-md-6" style="display: none">
                        <h2>Edit Organization</h2>
                        <form class="form-horizontal" role="form" method="POST" action="editOrganization">
                            <input type="int" id="organizationId" name="organizationId" hidden/>
                            <div class="form-group">
                                <label for="edit-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" id="name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" name="description" id="description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-phone" class="col-md-4 control-label">Phone:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="phone" id="phone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-email" class="col-md-4 control-label">Email:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="email" id="email"/>
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
                                    <select class="form-control" name="location">
                                        <!-- GET LOCATIONS FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <option value="${currentLocation.locationId}"><c:out value="${currentLocation.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Current Members:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" id="currentMembers" disabled></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-members" class="col-md-4 control-label">Members:</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="persons">
                                        <option selected disabled>Select Multiple Heroes</option>
                                        <!-- GET SUPERHEROES FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentHero" items="${personList}">
                                            <option value="${currentHero.personId}"><c:out value="${currentHero.name}"/></option>
                                        </c:forEach>            
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                    <button class="btn btn-default" type="button" id="cancelEditOrganization-btn">Cancel</button>
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