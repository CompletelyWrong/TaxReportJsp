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

<jsp:include page="/inspector/inspectorSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <h4 style="position: relative; width: 500px; left: 40%" class="mb-3"><fmt:message key="inspector.work"/></h4>
    <div id="form" style="position: relative; width: 83%; left: 17%; ">
        <div  class="col-md-8 order-md-1" >
            <h4 style="position: relative; width: 500px; left: 40%"><fmt:message key="inspector.current"/></h4>
            <form class="rounded"  style="padding: 10px; border:1px solid #999ea0">
                <div class="mb-3">
                    <label for="pib"><fmt:message key="report.pib"/></label>
                    <input value="${reportFullName}" disabled type="text" class="form-control" id="pib" name="pib" required="">
                </div>
                <div class="mb-3">
                    <label for="type"><fmt:message key="report.type"/></label>
                    <input value="${reportType}" disabled type="text" class="form-control" id="type" name="type"  required="">
                </div>
                <div class="mb-3">
                    <label for="code"><fmt:message key="reg.code"/></label>
                    <input value="${reportInnCode}" disabled style="width: 48%;" type="text" class="form-control" id="code"  name="code" required="">
                </div>
                <p style="position: relative;"><fmt:message key="report.period"/></p>
                <div class="row" style="position: relative">
                    <div class="col-md-6 mb-3">
                        <label for="date1"><fmt:message key="report.periodStart"/></label>
                        <input value="${reportStartPeriod}" disabled style="width: 100%;" name="date" required id="date1" type="date" class="form-control">

                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="date2"><fmt:message key="report.periodEnd"/></label>
                        <input value="${reportEndPeriod}" disabled style="width: 100%;" name="date" required id="date2" type="date" class="form-control">
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.income"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-label-group">
                            <input value="${reportClearCode}" disabled style="width: 100%;" type="text" name="incomeCode" id="incomeCode" class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required autofocus>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${reportClearValue}" disabled style="width: 100%;" type="text" name="incomeValue" id="incomeValue" class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.outcome"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${reportIncomeCode}" disabled style="width: 100%;"  type="text" name="outcomeCode" id="outcomeCode" class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${reportIncomeValue}" disabled style="width: 100%;"  type="text" name="outcomeValue" id="outcomeValue" class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="report.procent"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${reportOutcomeCode}" disabled style="width: 100%;"  type="text" name="procentCode" id="procentCode" class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${reportOutcomeValue}" disabled style="width: 100%;"  type="text" name="procentValue" id="procentValue" class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required autofocus>
                    </div>
                </div>
                <p style="position: relative;"><fmt:message key="request.clear"/></p>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input value="${reportPercentCode}" disabled style="width: 100%;"  type="text" name="clearCode" id="clearCode" class="form-control" placeholder="<fmt:message key="report.incomeCode"/>" required autofocus>
                    </div>
                    <div class="col-md-6 mb-3">
                        <input value="${reportPercentValue}" disabled style="width: 100%;"  type="text" name="clearValue" id="clearValue" class="form-control" placeholder="<fmt:message key="report.incomeValue"/>" required autofocus>
                    </div>
                </div>
            </form>
        </div>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <p></p>

        <form style="position: relative; width: 500px; left: 15%" method="post" action="${pageContext.request.contextPath}/inspector?command=report&report_id=${report_id}">
            <h5 style="position: relative; width: 500px; left: 40%"><fmt:message key="report.chose"/></h5>
            <input hidden name="report_id" value="${report_id}">
            <div class="form-group">
                <select name="verdict" class="custom-select d-block w-100" required="">
                    <option disabled selected value=""><fmt:message key="report.chose"/></option>
                    <option value="ACCEPT"><fmt:message key="report.acc"/></option>
                    <option value="REJECT"><fmt:message key="report.rej"/></option>
                    <option value="REQUEST_CHANGES"><fmt:message key="report.cna"/></option>
                    <option value="VIEW"><fmt:message key="report.view"/></option>
                </select>
            </div>
            <h5 style="position: relative; width: 500px; left: 20%"><fmt:message key="report.add"/></h5>
            <div class="form-group">
                <label for="exampleFormControlTextarea1"></label>
                <textarea name="message" required placeholder="<fmt:message key="report.add"/>" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <button class="btn btn-primary btn-lg btn-block" style="width: 30%; position: relative; left: 38% " type="submit"><fmt:message key="request.submit"/></button>
        </form>
    </div>
</main>


</body></html>