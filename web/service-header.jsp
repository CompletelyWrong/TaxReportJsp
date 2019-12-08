<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href=""><fmt:message key="head.hello"/>${user.name}</a>
    <ul class="navbar-nav px-3" style="position: relative; left: 51%">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<my:replaceParam name='locale' value='en'  />">Eng</a>
        </li>
    </ul>
    <ul class="navbar-nav px-3" style="position: relative; left: 34%">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<my:replaceParam name='locale' value='ru'  />">Рус</a>
        </li>
    </ul>
    <ul class="navbar-nav px-3" style="position: relative; left: 17%">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<my:replaceParam name='locale' value='ua'  />">Укр</a>
        </li>
    </ul>
    <ul class="navbar-nav px-3" style="position: relative;">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="?command=logout"><fmt:message key="head.out"/></a>
        </li>
    </ul>
</nav>