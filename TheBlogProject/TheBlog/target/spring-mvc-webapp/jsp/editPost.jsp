<%-- 
    Document   : editPost
    Created on : Jan 13, 2021, 5:15:21 PM
    Author     : raulalvarado
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Security: Content</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <script src="https://cdn.tiny.cloud/1/3mcr2ezgssioqqi3o00foz13922twjp48ughhe14gc4741nb/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
    tinymce.init({
      selector: 'textarea',
      plugins: 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
      toolbar: 'a11ycheck addcomment showcomments casechange checklist code formatpainter pageembed permanentpen table',
      toolbar_mode: 'floating',
      tinycomments_mode: 'embedded',
      tinycomments_author: 'Author name',
   });
  </script>
    </head>
    <body>
        <div class="container">
            <h1>The Sports Blog: Edit Blog</h1>
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

            <h2>Edit Blog Page</h2>
            <sec:authorize access="isAuthenticated()">
                <form class="form-inline" 
                      method="POST" 
                      action="${pageContext.request.contextPath}/logout">
                    <input type="hidden" 
                           name="${_csrf.parameterName}" 
                           value="${_csrf.token}"/>
                    <label for="submit">
                        Hello : ${pageContext.request.userPrincipal.name}&nbsp;&nbsp;&nbsp;|
                    </label>
                    <button class="btn btn-link" 
                            id="submit" 
                            type="submit">Logout</button>
                </form>
            </sec:authorize>

            <form method="POST" action="updatePost">
                <input type="hidden" 
                       name="${_csrf.parameterName}" 
                       value="${_csrf.token}"/>
                <input type="hidden" 
                       name="postId" 
                       value="${post.postId}"/>
                <input type="hidden" 
                       name="userId" 
                       value="${post.user.userId}"/>
                <div class="form-group">
                    <label for="edit-title">Blog Title</label>
                    <input type="text" class="form-control" value="${post.title}" name="edit-title" required>
                </div>
                <div class="form-group">
                    <label for="edit-category">Category</label>
                    <select class="form-control" name="edit-category">
                        <option>Choose One</option>
                        <c:forEach items="${categoryList}" var="currentCat">
                            <option value="${currentCat.categoryId}" 
                                    <c:if test="${post.category.categoryId == currentCat.categoryId}"> selected="selected"</c:if>
                                    >${currentCat.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">                
                    <textarea rows="15" value="${post.content}">${post.content}</textarea>
                </div>
                <div class="form-group"><br/>
                    <label for="tags">Tags</label>
                    <input type="text" class="form-control" name="tags" id="tag"/>
                    <button type="button" id="tag-btn" name="tag-btn">Tag</button>
                    <div id="tagDiv">
                        <c:forEach var="currentTag" items="${post.tags}">
                            <a class="badge badge-pill badge-light">${currentTag.name} </a>
                        </c:forEach>
                    </div>
                </div><br/>
                <button type="submit" name="updatePost">Submit Blog</button>  
            </form>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p>
                    This is only visible to users who also have the ADMIN role.
                </p>

            </sec:authorize>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
     
    </body>
</html>

