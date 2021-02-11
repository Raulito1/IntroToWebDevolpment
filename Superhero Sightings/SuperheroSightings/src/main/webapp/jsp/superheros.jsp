<%-- 
    Document   : superheros
    Created on : Feb 10, 2021, 12:06:16 AM
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/superheros">Superhero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Location</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organization</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sighting</a></li>
                </ul>    
            </div>
            <div class="container">
                <ul class="list-group" id="errorMessages"></ul>
                <div class="row">
                    <div class="col-md-6">
                        <h2>List Of Superheros</h2>
                        <table id="superheroTable" class="table table-hover">
                            <!-- GET SUPERHEROES FROM DB AND DISPLAY HERE -->
                            <c:forEach var="currentPerson" items="${personList}">
                                <tr>
                                    <td>
                                        <a href="heroDetails?personId=${currentPerson.personId}">
                                            <c:out value="${currentPerson.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a class="editPerson-btn" onclick="editSuperhero(${currentPerson.personId})">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteHero?personId=${currentPerson.personId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>                            
                        </table>
                    </div>
                    <div id="addSuperheroDiv" class="col-md-6">
                        <h2>Add A Superhero</h2>
                        <form class="form-horizontal" role="form" method="POST" action="addPerson">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" name="description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-superpowers" class="col-md-4 control-label">Superpowers:</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="powers">
                                        <option selected disabled>Select A Superpower</option>
                                        <!-- GET SUPERPOWERS FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentPower" items="${superpowerList}">
                                            <option value="${currentPower.superpowerId}"><c:out value="${currentPower.name}"/></option>
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
                    <div id="editSuperheroDiv" class="col-md-6" style="display: none">
                        <h2>Edit Superhero</h2>
                        <form class="form-horizontal" role="form" method="POST" action="editPerson">
                            <input type="int" id="personId" name="personId" hidden/>
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
                                <label class="col-md-4 control-label">Current Power:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" id="currentPowers" disabled></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-superpowers" class="col-md-4 control-label">Superpower:</label>
                                <div class="col-md-8">
                                    <select multiple class="form-control" name="powers">
                                        <option selected disabled>Select A Superpower</option>
                                        <!-- GET SUPERPOWERS FROM DB AND DISPLAY HERE -->
                                        <c:forEach var="currentPower" items="${superpowerList}">
                                            <option value="${currentPower.superpowerId}"><c:out value="${currentPower.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" type="submit">Submit</button>
                                    <button class="btn btn-default" type="button" id="cancelEditPerson-btn">Cancel</button>
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