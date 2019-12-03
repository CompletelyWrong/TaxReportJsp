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
    <h4 style="position: relative; width: 500px; left: 30%" class="mb-3"><fmt:message key="request.main"/></h4>

    <div id="form1" style="position: relative; width: 83%; left: 17%; ">

    <div id="form" style="position: relative; left: 17%; width: 1000px ">
        <div  class="col-md-8 order-md-1" >
            <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/user?command=add_request">
                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label for="country"><fmt:message key="request.type"/></label>
                        <select class="custom-select d-block w-100" id="country" required="">
                            <option disabled selected value=""><fmt:message key="request.stype"/></option>
                            <option><fmt:message key="request.change"/></option>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="reason"><fmt:message key="request.mess"/><span class="text-muted"></span></label>
                    <input required type="text" name="reason" class="form-control" id="reason" placeholder="<fmt:message key="request.messbody"/>">
                </div>
                <button class="btn btn-primary btn-lg btn-block" style="width: 30%; position: relative; left: 40%;" type="submit"><fmt:message key="request.submit"/></button>
            </form>
        </div>
    </div>
    </div>
</main>
</body></html>