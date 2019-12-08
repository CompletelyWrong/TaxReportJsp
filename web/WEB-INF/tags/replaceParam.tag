<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="true" type="java.lang.String" %>
<c:url value="">
    <c:forEach items="${paramValues}" var="p">
        <c:choose>
            <c:when test="${p.key == name}">
                <c:param name="${name}" value="${value}"/>
            </c:when>
            <c:otherwise>
                <c:forEach items="${p.value}" var="val">
                    <c:param name="${p.key}" value="${val}"/>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${empty param[name] }">
        <c:param name="${name}" value="${value}"/>
    </c:if>

</c:url>