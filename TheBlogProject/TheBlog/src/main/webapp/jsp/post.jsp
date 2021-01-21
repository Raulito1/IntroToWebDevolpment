<%-- 
    Document   : post
    Created on : Jan 13, 2021, 1:47:04 AM
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
        <title>The Blog: Content Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/myCss.css" rel="stylesheet">
        <script src="https://cdn.tiny.cloud/1/3mcr2ezgssioqqi3o00foz13922twjp48ughhe14gc4741nb/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
            tinymce.init({
                selector: '#textarea',
                plugins: 'save',
                toolbar: 'save'
            });
      
        </script>
    </head>
  </script>
    </head>
    <body>
        <div class="container">
            <h1>The Sports Blog</h1>
            <hr/>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Blog Site:</a>
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

            <h2>Create Blog Page</h2>
            <sec:authorize access="isAuthenticated()">
                <form class="form-inline" 
                      method="POST" 
                      action="${pageContext.request.contextPath}/logout">
                    <input type="hidden" 
                           name="${_csrf.parameterName}" 
                           value="${_csrf.token}"/>
                    <label for="submit" style="color: whitesmoke;">
                        Hello : ${pageContext.request.userPrincipal.name}&nbsp;&nbsp;&nbsp;|
                    </label>
                    <button class="btn btn-link" 
                            id="submit" 
                            type="submit">Logout</button>
                </form>
            </sec:authorize>

            <form  id="get-data-form" method="POST" action="createPost" role="form" class="form-horizontal">
                <input type="hidden" 
                       name="${_csrf.parameterName}" 
                       value="${_csrf.token}"/>
                <div class="form-group">
                    <label for="title" id="blogTitle">Blog Title</label>
                    <input id="blog-title" type="text" class="form-control" name="title" placeholder="Title Your Blog HERE!!" required>
                </div>
                <div class="form-group">
                    <label for="category" >Category</label>
                    <select id="category-name" style="width: 100%" class="form-control" name="category">
                        <option selected value="">Select A Category</option>
                        <c:forEach var="currentCat" items="${categoryList}">
                            <option name="cat" value="${currentCat.categoryId}">${currentCat.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">                
                    <textarea  id="textarea" rows="15" name="textarea" placeholder="Insert Your Blog HERE!!" required></textarea>
                   
                </div>
                <div class="form-group"><br/>
                    <label for="tags" id="blogTag">Tags</label>
                    <input type="text" class="form-control" name="tags" id="blog-tag" placeholder="Add Your (#)TAGS!!">
                    <button type="button" id="tag-btn" name="tag-btn">Tag</button>
                    <div id="tagDiv"></div>
                </div><br/>
                <button type="submit" class="btn btn-success" name="submitBlog" id="saveBtn">Submit Blog</button>  
            </form>
        </div>
                

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tags.js"></script>
        <script src="${pageContext.request.contextPath}/js/post.js"></script>
    </body>
</html>
