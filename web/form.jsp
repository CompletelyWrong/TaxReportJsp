<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10.11.2019
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value='/show'/>" method="get">
    <input type="hidden" name="rowCount" value="${5}">
    <input type="hidden" name="startFrom" value="${0}">
    <input type="submit" value="Показать список">
</form>
</body>
</html>