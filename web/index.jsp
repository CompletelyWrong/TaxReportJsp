<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<!doctype html>
<html lang="param.locale">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Marvel HTML Bootstrap 4 Template</title>

  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/tooplate-style.css">
</head>
<body>
<!-- MENU -->
<jsp:include page="simpleHeader.jsp" />
<!-- ABOUT -->
<section class="about full-screen d-lg-flex justify-content-center align-items-center" id="about">
  <div class="container">
    <div class="row">

      <div class="col-lg-7 col-md-12 col-12 d-flex align-items-center">
        <div class="about-text">
          <small class="small-text"><fmt:message key="string"/><span class="mobile-block"></span></small>
          <h1 class="animated animated-text">
            <span class="mr-2"><fmt:message key="index.online"/></span>
            <div class="animated-info">
              <span class="animated-item"><fmt:message key="index.fast"/></span>
              <span class="animated-item"><fmt:message key="index.comf"/></span>
              <span class="animated-item"><fmt:message key="index.safe"/></span>
            </div>
          </h1>
          <p><fmt:message key="index.message"/></p>

          <div class="custom-btn-group mt-4">
            <a href="${pageContext.request.contextPath}/login" class="btn mr-lg-2 custom-btn"><i class='uil uil-file-alt'></i><fmt:message key="index.log"/></a>
            <a href="${pageContext.request.contextPath}/register" class="btn custom-btn custom-btn-bg custom-btn-link"><fmt:message key="index.reg"/></a>
          </div>
        </div>
      </div>
      <div class="col-lg-5 col-md-12 col-12">
        <div class="about-image svg">
          <img src="images/undraw/undraw_software_engineer_lvl5.svg" class="img-fluid" alt="svg image">
        </div>
      </div>
    </div>
  </div>
</section>
<!-- PROJECTS -->

<section class="project py-5" id="project">
  <div class="container">
    <h1 style="position: relative; top: -100px; left: 10%">
      <span ><fmt:message key="index.why"/></span>
    </h1>

    <style>
      .layer1 {
        width:30%;
        height: 300px;
        float: left; /* Абсолютное позиционирование */
        /* Положение от левого края */

        margin: 7px; /* Отступы вокруг элемента */
      }

      .layer5 {
        /* Абсолютное позиционирование */
        /* Положение от левого края */
        position: relative;
        left: 27%;

        margin: 7px; /* Отступы вокруг элемента */
      }

      .layer4 {
        left: 550px;
        position: relative; /* Абсолютное позиционирование */
        /* Положение от левого края */
        margin: 7px; /* Отступы вокруг элемента */
      }
      .layer2 {
        width:30%;
        height: 300px;
        float: left; /* Абсолютное позиционирование */
        left: 10px; /* Положение от левого края */

        margin: 7px; /* Отступы вокруг элемента */
      }
      .layer3 {
        width:30%;
        height: 300px;
        float: left; /* Абсолютное позиционирование */
        /* Положение от левого края */

        margin: 7px; /* Отступы вокруг элемента */
      }
    </style>

    <!-- Card -->
    <div class="layer1">
      <div style="display: flex; justify-content: center;">
        <img  class="card-image" width="200px" height="150px" src="images/services-icon-1.svg" alt="alternative">
      </div>
      <p style=" justify-content: center;"></p>
      <h4 style="display: flex; justify-content: center;"><fmt:message key="index.fast"/></h4>
      <p style=" justify-content: center;"><fmt:message key="index.fast_full"/></p>
    </div>
    <!-- end of card -->

    <!-- Card -->
    <div class="layer2">
      <div style="display: flex; justify-content: center;">
        <img  class="card-image" width="200px" height="150px" src="images/services-icon-2.svg" alt="alternative">
      </div>
      <p style=" justify-content: center;"></p>
      <h4 style="display: flex; justify-content: center;"><fmt:message key="index.comf"/></h4>
      <p style=" justify-content: center;"><fmt:message key="index.comf_full"/></p>
    </div>
    <!-- end of card -->

    <!-- Card -->
    <div class="layer3">
      <div style="display: flex; justify-content: center;">
        <img  class="card-image" width="200px" height="150px" src="images/services-icon-3.svg" alt="alternative">
      </div>
      <p style=" justify-content: center;"></p>
      <h4 style="display: flex; justify-content: center;"><fmt:message key="index.safe"/></h4>
      <p style=" justify-content: center;"><fmt:message key="index.safe_full"/></p>
    </div>


    <!-- end of card -->

  </div> <!-- end of col -->

</section>
</body>
</html>