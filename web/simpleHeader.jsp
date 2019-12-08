<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-light" style="position:absolute; width: 400px; height: 100px; top: -1px; left: 40%">
    <div class="container" style="position:absolute; width: 100%; height: 100px; top: -1px">

        <div>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><span class="navbar-brand"><fmt:message key="language"/></span></a>
            <a class="navbar-brand" href="<my:replaceParam name='locale' value='en'/>"><span class="mr-2">Eng</span></a>
            <a class="navbar-brand" href="<my:replaceParam name='locale' value='ua'/>"><span class="mr-2">Укр</span></a>
            <a class="navbar-brand" href="<my:replaceParam name='locale' value='ru'/>"><span class="mr-2">Рус</span></a>
        </div>
    </div>
</nav>
