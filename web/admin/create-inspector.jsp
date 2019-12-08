<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<!-- saved from url=(0054)https://getbootstrap.com/docs/4.0/examples/dashboard/# -->
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title><fmt:message key="admin.addInspectorMain"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <style type="text/css">/* Chart.js */
    </style>
</head>

<body>
<jsp:include page="/service-header.jsp"/>

<jsp:include page="/admin/adminSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <h2 style="position: relative; left: 35%; width: 60%"><fmt:message key="admin.addInspectorMain"/></h2>
    <article class="card-body mx-auto" style="max-width: 400px;">
        <form name="register" method="post" action="${pageContext.request.contextPath}/admin?command=add-inspector">
            <div class="form-group input-group">
                <input name="surname" class="form-control" required
                       pattern="([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))"
                       title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.fname"/>"
                       type="text">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <input name="name" class="form-control" required
                       pattern="([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))"
                       title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.name"/>"
                       type="text">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <input name="patronymic" class="form-control" required
                       pattern="([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))"
                       title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.patron"/>"
                       type="text">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <input name="email" id="email" class="form-control" required
                       placeholder="<fmt:message key="reg.email"/>" type="email">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <input id="password" name="password" class="form-control" required pattern="([^\s][A-Za-z\d]{5,15})"
                       title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.password"/>"
                       type="password">
            </div> <!-- form-group// -->
            <div class="form-group input-group">
                <input id="repeatedPassword" name="repeatedPassword" class="form-control" required
                       pattern="([^\s][A-Za-z\d]{5,15})"
                       title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.spassword"/>"
                       type="password">
            </div> <!-- form-group// -->
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="admin.addInspector"/></button>
            </div> <!-- form-group// -->
        </form>
    </article>
</main>
</body>
</html>