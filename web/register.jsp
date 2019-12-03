<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<%request.setCharacterEncoding("UTF-8");%>
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 08.11.2019
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>8
<html lang="${sessionScope.locale}">
<jsp:include page="simpleHeader.jsp" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Floating labels example for Bootstrap</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/tooplate-style.css">

    <!-- Bootstrap core CSS -->

    <!-- Custom styles for this template -->
    <link href="css/floating-labels.css" rel="stylesheet">
</head>

<body>



<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="index.reg"/></h4>
            <p class="text-center"><fmt:message key="reg.all"/></p>
            <form name = "register" method="post" action="${pageContext.request.contextPath}/register">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="fullName"  class="form-control" required pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.fname"/>" type="text">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name1" class="form-control" required pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.name"/>" type="text">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="patron"  class="form-control" required pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.patron"/>" type="text">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" id="email" class="form-control"  required  placeholder="<fmt:message key="reg.email"/>" type="email">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input id="password1" name="password1" class="form-control" required pattern="[\s\S]{6,15}" title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.password"/>" type="password">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input id="password2" name="password2" class="form-control" required pattern="[\s\S]{6,15}" title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.spassword"/>" type="password">
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <select id="role_type" required name="role_type" class="form-control">
                        <option disabled selected=""><fmt:message key="reg.type"/></option>
                        <option id="4" value="4"><fmt:message key="reg.type1"/></option>
                        <option id="3" value="3"><fmt:message key="reg.type2"/></option>
                    </select>
                </div> <!-- form-group end.// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-archive"></i> </span>
                    </div>
                    <input id="number" name="number" class="form-control" required pattern="[\d]{8,10}" title="<fmt:message key="reg.codeHelp"/>" placeholder="<fmt:message key="reg.code"/>" type="text">
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"><fmt:message key="index.reg"/></button>
                </div> <!-- form-group// -->
                <p class="text-center"><fmt:message key="reg.have"/>? <a href="${pageContext.request.contextPath}/login"><fmt:message key="log.form"/></a> </p>
            </form>
        </article>
    </div> <!-- card.// -->
</div>
<!--container end.//-->
</body>
</html>