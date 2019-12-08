<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="?command=users">

                            <fmt:message key="inspectorSide.main"/>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=profile">


                            <fmt:message key="inspectorSide.pfofile"/>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=users">


                            <fmt:message key="inspectorSide.allreports"/>

                        </a>
                    </li>
                </ul>

            </div>
        </nav>
    </div>
</div>