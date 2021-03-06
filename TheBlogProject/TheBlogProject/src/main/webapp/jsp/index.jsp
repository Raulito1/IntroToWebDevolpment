<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>The Sports Blog</title>
        <!-- Bootstrap core CSS -->
<!--        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        -->

        <!--CDN for Bootstrap 4-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Google Fonts-->
        <link href="https://fonts.googleapis.com/css?family=Abhaya+Libre|Arbutus+Slab" rel="stylesheet">
        
        
        <!--custom CSS-->
        <link rel="stylesheet" href="css/customStyles.css">
        <style>
            .form-group, .form-control, .list-group-item, .btn,h4 {
                color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;
               
            }
        </style>
    </head>
    <body>
        <div class="container">

            <nav class="navbar navbar-expand-lg navbar-light mb-2">

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-white active" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/Article">Blogs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/Item">Tags</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/Category">Category</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/User">Users</a>
                        </li>
                        <li class="nav-item">
                            <c:if test="${pageContext.request.userPrincipal.name == null}">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/Login">Log In</a>
                            </c:if>
                        </li>
                        <li class="nav-item">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <a class="nav-link text-white" href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </nav>

            <h1 class="text-center">The Sports Blog</h1>

            <!--This is mostly a test, it provides a logout link as well as an if tag to test login privileges-->
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h4>Hello : ${pageContext.request.userPrincipal.name}</h4>
            </c:if>


                <div style="color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Varius duis at consectetur lorem donec massa. Ut tortor pretium viverra suspendisse potenti. Ac tortor vitae purus faucibus ornare suspendisse sed. Risus viverra adipiscing at in tellus integer. Dignissim enim sit amet venenatis urna cursus eget. Elit ut aliquam purus sit amet luctus venenatis lectus magna. At varius vel pharetra vel turpis nunc. Facilisi cras fermentum odio eu. Tristique sollicitudin nibh sit amet commodo nulla facilisi nullam. Tortor dignissim convallis aenean et tortor at. Ullamcorper velit sed ullamcorper morbi. Egestas erat imperdiet sed euismod nisi porta lorem mollis aliquam.</p>
                </div>

                <div class="featured-items-container" style="color: whitesmoke;
                font-family: chalkduster, fantasy;
                text-shadow: 0 0 3px black, 0 0 3px black, 0 0 3px black, 0 0 3px black;">

                <h4 class="text-center">Featured items:</h4>

                <div class="row justify-content-center">
                    <c:forEach items="${featuredItems}" var="item">
                        <a href="${pageContext.request.contextPath}/displayItemDetail?itemId=${item.itemId}">
                            <div class="featured-item-div border btn m-2">
                                <div class="feature-text">${item.itemName}</div>
                                <div class="feature-text text-truncate">${item.description}</div>
                               
                            </div>
                        </a>
                    </c:forEach>

                </div>

            </div>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
<!--        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>-->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
