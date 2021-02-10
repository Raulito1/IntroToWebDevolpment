<%-- 
    Document   : search
    Created on : Nov 9, 2020, 10:28:05 PM
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
        <h1>Company Contacts</h1>
        <hr/>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/displayContactsPage">Contacts</a>
                </li>
                <li role="presentation" class="active">
                    <a href="${pageContext.request.contextPath}/displaySearchPage">Search</a>
                </li>
            </ul>
                
        </div>
         <!-- Main Page Content Start -->
         <!-- 
            Add a row to our container - this will hold the search summary table and
            the search form
         -->
         <ul class="list-group" id="errorMessages"></ul>
         <div class="row">
             <!-- 
                Add a col to hold the summary table- have it take up half the row 
             -->
             <div class="col-md-6">
                 <h2>Search Results</h2>
                 <table id="contactTable" class="table table-hover">
                     <tr>
                         <th width="40%">Contact Name</th>
                         <th width="30%">Company</th>
                         <th width="15%">Phone</th>
                         <th width="15%">Email</th>
                     </tr>
                     <tbody id="contentRows"/>
                 </table>
             </div> <!-- End col div -->
             <!--
                Add col to hold the search form - have it take up the other ha;f of the row
             -->
             <div class="col-md-6">
                 <h2>Search</h2>
                 <form class="form-horizontal" role="form" id="search-form">
                     <div class="form-group">
                         <label for="search-first-name" class="col-md-4 control-label">First Name:</label>
                         <div class="col-md-8">
                             <input type="text" class="form-control" id="search-first-name" placeholder="First Name"/>
                             
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="search-last-name" class="col-md-4 control-label">Last Name:</label>
                         <div class="col-md-8">
                             <input type="text" class="form-control" id="serch-last-name" placeholder="Last Name"/>
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="search-company" class="col-md-4 control-label">Company:</label>
                         <div class="col-md-8">
                             <input type="text" class="form-control" id="search-company" placeholder="Company"/>
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="search-email" class="col-md-4 control-label">Email:</label>
                         <div class="col-md-8">
                             <input type="email" class="form-control" id="search-email" placeholder="Phone"/>
                         </div>
                     </div>
                     <div class="form-group">
                         <div class="col-md-offset-4 col-md-8">
                             <input type="button" class="btn btn-default" id="search-button" value="Search"/>
                         </div>
                     </div>
                 </form>
             </div> <!-- End col div -->
             
         </div> <!-- End row div -->
         
         <!-- Main Page Content Stop -->
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/contactList.js"></script>
    </body>
</html>
