<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 18.11.2019
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
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



    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <title><fmt:message key="report.show"/></title>

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
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4" style="width: 500px">
    <h4 style="position: relative; width: 30%; left: 35%" class="mb-3"><fmt:message key="report.show"/></h4>
    <section id="tabs">
        <div style="position: relative; left: 5%; width: 30%" class="container">
            <div class="row">
                <div class="col-xs-12 ">
                    <nav>
                        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            <a class="nav-item nav-link active" id="nav-home-tab"
                               href="${pageContext.request.contextPath}/user?command=report&reportId=${reportId}"
                               aria-selected="true"><fmt:message key="report.report"/></a>
                            <a class="nav-item nav-link" id="nav-profile-tab"
                               href="${pageContext.request.contextPath}/user?command=report-history&reportId=${reportId}"
                               aria-selected="false"><fmt:message key="report.history"/></a>
                        </div>
                    </nav>


                </div>
            </div>
        </div>
    </section>
    <div id="form" style="position: relative; width: 83%; left: 17%; ">
        <div class="col-md-8 order-md-1">
            <form class="needs-validation" method="post"
                  action="${pageContext.request.contextPath}/user?command=add-report">
                <div class="mb-3">
                    <label for="pib"><fmt:message key="report.pib"/></label>
                    <input value="${report.fullName}" disabled type="text" class="form-control" id="pib" name="pib"
                           required="">
                </div>
                <div class="mb-3">
                    <label for="type"><fmt:message key="report.type"/></label>
                    <input value="${report.type}" disabled type="text" class="form-control" id="type" name="type"
                           required="">
                </div>
                <div class="mb-3">
                    <label for="code"><fmt:message key="reg.code"/></label>
                    <input value="${report.innCode}" disabled style="width: 49%;" type="text" class="form-control"
                           id="code" name="code" required="">
                </div>
                <p style="position: relative;"><fmt:message key="report.period"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="date1"><fmt:message key="report.periodStart"/></label>
                        <input value="${report.periodStart}" disabled name="date" required id="date1" type="date"
                               class="form-control">

                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="date2"><fmt:message key="report.periodEnd"/></label>
                        <input value="${report.periodEnd}" disabled name="date" required id="date2" type="date"
                               class="form-control">
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.income"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-label-group">
                            <input value="${report.incomeCode}" disabled type="text" name="incomeCode" id="incomeCode"
                                   class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required
                                   autofocus>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${report.incomeValue}" disabled type="text" name="incomeValue" id="incomeValue"
                               class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required
                               autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.outcome"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${report.outcomeCode}" disabled type="text" name="outcomeCode" id="outcomeCode"
                               class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required
                               autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${report.outcomeValue}" disabled type="text" name="outcomeValue" id="outcomeValue"
                               class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required
                               autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.procent"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${report.percentCode}" disabled type="text" name="procentCode" id="procentCode"
                               class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required
                               autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${report.percentValue}" disabled type="text" name="procentValue" id="procentValue"
                               class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required
                               autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="request.clear"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${report.clearCode}" disabled type="text" name="clearCode" id="clearCode"
                               class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required
                               autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${report.clearValue}" disabled type="text" name="clearValue" id="clearValue"
                               class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required
                               autofocus>
                    </div>
                </div>

            </form>
            <a href="${pageContext.request.contextPath}/user?command=update-report&reportId=${reportId}"
               class="btn btn-primary btn-lg btn-block" type="submit"
               style="width: 30%; position: relative; left: 40%;"><fmt:message key="report.change"/></a>

            <p></p>
        </div>
    </div>
</main>
</body>
</html>