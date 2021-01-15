<%-- 
    Document   : category
    Created on : Jan 13, 2021, 1:46:55 AM
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
        <title>The Blog</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/myCss.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <h1>The Sports Blog</h1>
            <hr/>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/home" style="color: whitesmoke;">Blog Site:</a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li class="dropdown active">
                            <a class="dropdown-toggle" data-toggle="dropdown"
                               href="${pageContext.request.contextPath}/home">
                                User <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/post">
                                        Create Post
                                    </a></li>
                                <li><a href="${pageContext.request.contextPath}/displayCategoryPage">
                                        Category
                                    </a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown"
                               href="${pageContext.request.contextPath}/admin">
                                Admin <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/admin">
                                        Admin
                                    </a></li>
                                <li><a href="${pageContext.request.contextPath}/post">
                                        Create Post
                                    </a></li>
                                <li><a href="${pageContext.request.contextPath}/displayCategoryPage">
                                        Category
                                    </a></li>
                                <li><a href="${pageContext.request.contextPath}/approvePost">
                                        Approve Post</a></li>
                            </ul>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
                    </ul>
                </div>
            </nav>

            <div class="col-md-6">
                <h2>Category</h2>
                <table id="categoryTable" class="table table-hover">
                    <tr>
                        <th width="40%" style="color: whitesmoke;">Name</th>

                    </tr>
                    <c:forEach var="currentCategory" items="${categoryList}">
                        <tr>
                            <td style="color: red;">
                                <c:out  value="${currentCategory.name}"/> 
                            </td>

                            <td>
                                <a href="displayEditCategoryForm?categoryId=${currentCategory.categoryId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteCategory?categoryId=${currentCategory.categoryId}">
                                    Delete 
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>                 
            </div> <!-- End col div -->
            <!-- 
                Add col to hold the new contact form - have it take up the other 
                half of the row
            -->
            <div class="col-md-6">
                <h2>Add New Category</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createCategory">
                    <div class="form-group">
                        <label for="add-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="name" placeholder="Name" required/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Category"/>
                        </div>
                    </div>
                    <input type="hidden"                        
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>

            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/tags.js"></script>
    </body>
</html>