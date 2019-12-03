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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <title>Dashboard Template for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
    <style type="text/css">/* Chart.js */
</style>
</head>

<body>
<jsp:include page="/service-header.jsp"/>
<div>
    <p></p>
</div>
<jsp:include page="/user/userSidebar.jsp"/>


<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <h4 style="position: relative; width: 30%; left: 45%" class="mb-3"><fmt:message key="uprofile.prof"/></h4>
    <div id="form" style="position: relative; width: 83%; left: 17%; ">
    <div  class="col-md-8 order-md-1" >
        <form class="needs-validation" novalidate="">



            <div class="mb-3">
                <label for="fname"><fmt:message key="uprofile.surname"/><span class="text-muted"></span></label>
                <input disabled type="email" class="form-control" id="fname" value="${user.surname}">
                <div class="invalid-feedback">

                </div>
            </div>

            <div class="mb-3">
                <label for="name"><fmt:message key="uprofile.name"/><span class="text-muted"></span></label>
                <input disabled type="email" class="form-control" id="name" value="${user.name}">
                <div class="invalid-feedback">

                </div>
            </div>

            <div class="mb-3">
                <label for="pat"><fmt:message key="uprofile.patron"/><span class="text-muted"></span></label>
                <input disabled type="email" class="form-control" id="pat" value="${user.patronymic}">
                <div class="invalid-feedback">
                </div>
            </div>

            <div class="mb-3">
                <label for="email"><fmt:message key="uprofile.email"/><span class="text-muted"></span></label>
                <input disabled type="email" class="form-control" id="email" value="${user.email}">
                <div class="invalid-feedback">
                </div>
            </div>

            <div class="mb-3">
                <label for="code"><fmt:message key="uprofile.code"/><span class="text-muted"></span></label>
                <input disabled type="email" class="form-control" id="code" value="${user.identificationCode}">
                <div class="invalid-feedback">
                </div>
            </div>
        </form>
            <a href="${pageContext.request.contextPath}/user?command=update_profile" class="btn btn-primary btn-lg btn-block" type="submit" style="width: 30%; position: relative; left: 70%;"><fmt:message key="uprofile.change"/></a>


    </div>
    </div>
</main>
</body></html>