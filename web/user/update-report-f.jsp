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

    <title><fmt:message key="report.update"/></title>

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

<jsp:include page="/user/userSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <h2 style="position: relative; width: 30%; left: 35%"><fmt:message key="report.update"/></h2>
    <section id="tabs">
        <div style="position: relative; left: 5%; width: 30%" class="container">
            <div class="row">
                <div class="col-xs-12 ">
                    <nav>
                        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            <a class="nav-item nav-link " id="nav-home-tab"
                               href="${pageContext.request.contextPath}/user?command=update-report&reportId=${reportId}"
                               aria-selected="false"><fmt:message key="report.form"/></a>
                            <a class="nav-item nav-link active" id="nav-profile-tab"
                               href="${pageContext.request.contextPath}/user?command=update-report-file&reportId=${reportId}"
                               aria-selected="true"><fmt:message key="report.file"/></a>
                        </div>
                    </nav>


                </div>
            </div>
        </div>
    </section>
    <div style="position: relative; left: 24%; width: 30%" class="col-md-8 order-md-1">
        <form class="needs-validation" enctype="multipart/form-data" method="post"
              action="${pageContext.request.contextPath}/user?command=update-report-file&reportId=${reportId}">
            <div class="file-field">
                <div class="btn btn-primary btn-sm" style="position: relative; left: 36%;">
                    <input required type="file" placeholder="<fmt:message key="report.filetype"/>" accept=".json,.xml"
                           name="file" id="customFileLang" lang="">
                    <input hidden type="text" name="reportId" value="${reportId}" lang="">
                    <label for="customFileLang"></label>
                </div>
            </div>
            <p></p>
            <p></p>
            <button class="btn btn-primary btn-lg btn-block" style="width: 30%; position: relative; left: 50% "
                    type="submit"><fmt:message key="request.submit"/></button>
        </form>
    </div>
</main>


</body>
</html>