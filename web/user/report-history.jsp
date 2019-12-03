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
    <link href="css/dashboard.css" rel="stylesheet">
    <style type="text/css">/* Chart.js */
    </style></head>

<body>
<jsp:include page="/service-header.jsp"/>

<jsp:include page="/user/userSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

    <h2 style="position: relative; width: 500px; left: 40%"><fmt:message key="report.historys"/></h2>
    <section id="tabs">
    <div style="position: relative; left: 5%; width: 30%"  class="container">
        <div class="row">
            <div class="col-xs-12 ">
                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link " id="nav-home-tab"  href="${pageContext.request.contextPath}/user?command=report&report_id=${report_id}"  aria-selected="false"><fmt:message key="report.form"/></a>
                        <a class="nav-item nav-link active" id="nav-profile-tab" href="${pageContext.request.contextPath}/user?command=report_history&report_id=${report_id}" aria-selected="true"><fmt:message key="report.file"/></a>
                    </div>
                </nav>


            </div>
        </div>
    </div>
    </section>
    <div>
        <div class="row col-md-6">
            <table style="position: relative; width: 80%; left: 60%" class="table">
                <tr>
                    <th><fmt:message key="report.insId"/></th>
                    <th><fmt:message key="report.date"/></th>
                    <th><fmt:message key="report.message"/></th>
                    <th><fmt:message key="report.ty"/></th>
                </tr>
                <c:forEach items="${actions_list}" var="action">
                    <tr>
                        <td>${action.inspector.id}</td>
                        <td>${action.dateTime}</td>
                        <td>${action.message}</td>
                        <td>${action.action}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <nav aria-label="Navigation for countries" style="position: relative; left: 40%; width: 60%;">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/user?command=show&currentPage=${currentPage-1}"><</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/user?command=show&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/user?command=show&currentPage=${currentPage+1}">></a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    </div>
</main>
</body></html>