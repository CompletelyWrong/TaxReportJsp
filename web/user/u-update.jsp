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
    @-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>

<body>
<jsp:include page="/service-header.jsp"/>
update_profile
<jsp:include page="/user/userSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        <h4 style="position: relative; width: 30%; left: 30%" class="mb-3"><fmt:message key="uprofile.prof"/></h4>
        <div id="form" style="position: relative; width: 83%; left: 12%; ">
            <div  class="col-md-8 order-md-1" >
                <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/user?command=update_profile">
                    <div class="mb-3">
                        <label for="fullName"><fmt:message key="uprofile.surname"/><span class="text-muted"></span></label>
                        <input required="required" name="fullName" id="fullName" class="form-control" pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.fname"/>" type="text" value="${user.surname}">
                    </div>

                    <div class="mb-3">
                        <label for="name1"><fmt:message key="uprofile.name"/><span class="text-muted"></span></label>
                        <input   name="name1" class="form-control" required pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.name"/>" type="text" id="name1" value="${user.name}">
                    </div>

                    <div class="mb-3">
                        <label for="pat"><fmt:message key="uprofile.patron"/><span class="text-muted"></span></label>
                        <input  name="patron"  class="form-control" required pattern="[A-Za-zА-Яа-яїіІЇ]{2,30}" title="<fmt:message key="reg.nameHelp"/>" placeholder="<fmt:message key="reg.patron"/>" type="text" id="pat" value="${user.patronymic}">
                    </div>

                    <div class="mb-3">
                        <label for="email"><fmt:message key="uprofile.email"/><span class="text-muted"></span></label>
                        <input  name="email" id="email" class="form-control"  required  placeholder="<fmt:message key="reg.email"/>" type="email" value="${user.email}">
                    </div>

                    <div class="mb-3">
                        <label for="number"><fmt:message key="uprofile.code"/><span class="text-muted"></span></label>
                        <input  id="number" name="number" class="form-control" required pattern="[\d]{8,10}" title="<fmt:message key="reg.codeHelp"/>" placeholder="<fmt:message key="reg.code"/>" type="text" value="${user.identificationCode}">
                    </div>
                    <div class="mb-3">
                        <label for="password1"><fmt:message key="uprofile.updatePass1"/><span class="text-muted"></span></label>
                        <input id="password1" name="password1" class="form-control" required="required" pattern="[\s\S]{6,15}" title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.spassword"/>" type="password">
                    </div>
                    <div class="mb-3">
                        <label for="password2"><fmt:message key="uprofile.updatePass2"/><span class="text-muted"></span></label>
                        <input id="password2" name="password2" class="form-control" required="required" pattern="[\s\S]{6,15}" title="<fmt:message key="reg.passHelp"/>" placeholder="<fmt:message key="reg.spassword"/>" type="password">
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" style="width: 35%; position: relative; left: 35%;" type="submit"><fmt:message key="uprofile.change"/></button>
                </form>
            </div>
        </div>
    </main>
</main>
</body></html>