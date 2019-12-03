<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<!-- saved from url=(0054)https://getbootstrap.com/docs/4.0/examples/dashboard/# -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
    <style type="text/css">/* Chart.js */
    </style></head>

<body>
<jsp:include page="/service-header.jsp"/>

<jsp:include page="/user/userSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <h4 style="position: relative; width: 500px; left: 40%" class="mb-3"><fmt:message key="user.inspectorMain"/></h4>
        <div style="position: relative; width: 60%; left: 20%" class="col-md-8 order-md-1" >
            <form class="needs-validation" novalidate="">
                <div class="mb-3">
                    <label for="fname"><fmt:message key="uprofile.surname"/><span class="text-muted"></span></label>
                    <input disabled type="email" class="form-control" id="fname" value="${inspector.surname}">
                    <div class="invalid-feedback">

                    </div>
                </div>

                <div class="mb-3">
                    <label for="name"><fmt:message key="uprofile.name"/><span class="text-muted"></span></label>
                    <input disabled type="email" class="form-control" id="name" value="${inspector.name}">
                    <div class="invalid-feedback">

                    </div>
                </div>

                <div class="mb-3">
                    <label for="pat"><fmt:message key="uprofile.patron"/><span class="text-muted"></span></label>
                    <input disabled type="email" class="form-control" id="pat" value="${inspector.patronymic}">
                    <div class="invalid-feedback">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email"><fmt:message key="uprofile.email"/><span class="text-muted"></span></label>
                    <input disabled type="email" class="form-control" id="email" value="${inspector.email}">
                    <div class="invalid-feedback">
                    </div>
                </div>
            </form>
        </div>
</main>
</body></html>