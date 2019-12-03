<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 08.11.2019
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="param.locale">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Marvel HTML Bootstrap 4 Template</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/tooplate-style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="css/floating-labels.css" rel="stylesheet">
</head>
<body>
<!-- MENU -->
<jsp:include page="simpleHeader.jsp" />
<!-- ABOUT -->

<div class="container">
    <form class="form-signin" name = "login" method="post" action="${pageContext.request.contextPath}/admin">
        <input type="hidden" name="command" value="login" />
        <div class="text-center mb-4">
            <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="log.form"/></h1>
            <p><fmt:message key="log.enter"/></p>
        </div>

        <div class="form-label-group">
            <input type="email" name="email" id="email" class="form-control" placeholder="Email address" required autofocus>
            <label for="email"><fmt:message key="log.email"/></label>
        </div>

        <div class="form-label-group">
            <input type="password" name="password" id="password"  class="form-control" placeholder="Password" required>
            <label for="password"><fmt:message key="log.pass"/></label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="log.submit"/></button>

        <p class="mt-5 mb-3 text-muted text-center">&copy; 2019</p>
    </form>

</div>

</body>
</html>
