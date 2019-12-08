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

                            <fmt:message key="adminSide.main"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=add-inspector">


                            <fmt:message key="adminSide.pfofile"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=users">


                            <fmt:message key="adminSide.report"/>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=inspectors">


                            <fmt:message key="adminSide.allreports"/>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=requests">

                            <fmt:message key="adminSide.ask"/>

                        </a>
                    </li>
                </ul>

            </div>
        </nav>