<%-- 
    Document   : categoryPost
    Created on : Jan 13, 2021, 5:15:49 PM
    Author     : raulalvarado
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>CATEGORY</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        
    </head>
    <body>
        <div class="container">
            <h1>${cat.name}</h1>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Blog Site:</a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="dropdown active">
                            <a href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li class="dropdown">
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
                        <li>
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

            <div class="col-md-8">
                <div class="card" style="width: 100%;">
                    <h2>All Posts</h2>
                    <hr/>
                    <c:forEach var="currentPost" items="${postList}">
                        <div class="card-body">
                            <h3><c:out value="${currentPost.title}"/></h3>
                            <b><p>Date: <c:out value="${currentPost.postDate}"/></p></b>
                            <b><p>Category: <c:out value="${currentPost.category.name}"/></p></b>
                            <p readonly class="card-text" style="width: 50%; height: 50%">
                                ${currentPost.content}
                            </p>
                            <c:forEach var="currentTag" items="${currentPost.tags}">
                                <a href="displayTagPosts?id=${currentTag.tagId}" class="badge badge-pill badge-light">${currentTag.name} </a>
                            </c:forEach>
                        </div>
                        <hr/>
                    </c:forEach>
                </div>
            </div>

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>