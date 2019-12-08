<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10.11.2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
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
                        <a class="nav-link" href="?command=show">

                            <fmt:message key="userSide.main"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=profile">


                            <fmt:message key="userSide.pfofile"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=add-report">

                            <fmt:message key="userSide.report"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=show">


                            <fmt:message key="userSide.allreports"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=inspector">

                            <fmt:message key="userSide.admin"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?command=add-request">


                            <fmt:message key="userSide.ask"/>
                        </a>
                    </li>
                </ul>

            </div>
        </nav>