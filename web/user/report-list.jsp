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

    <title><fmt:message key="user.showReports"/></title>

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
    <h4 style="position: relative; width: 500px; left: 40%" class="mb-3"><fmt:message key="user.showReports"/></h4>
    <div style="position: relative; width: 75%; left: 25%">
        <div class="row col-md-6">
            <table class="table">
                <tr>
                    <th><fmt:message key="user.reportId"/></th>
                    <th><fmt:message key="user.reportDate"/></th>
                    <th><fmt:message key="user.reportStatus"/></th>
                    <th><fmt:message key="user.reportAction"/></th>
                </tr>
                <c:forEach items="${reports}" var="report">
                    <tr>
                        <td>${report.id}</td>
                        <td>${report.creationDate}</td>
                        <td>${report.status}</td>
                        <td class=""><a class="page-link"
                                        href="${pageContext.request.contextPath}/user?command=report&reportId=${report.id}"><fmt:message
                                key="user.reportShow"/></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <nav aria-label="Navigation for countries" style="position: relative;  width: 60%;">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/user?command=show&currentPage=${currentPage-1}"><</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${numberOfPages}" var="i">
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

                <c:if test="${currentPage lt numberOfPages}">
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
</body>
</html>