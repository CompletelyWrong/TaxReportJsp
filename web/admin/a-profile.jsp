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

<jsp:include page="/admin/adminSidebar.jsp"/>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>

    <h2>Profile admin</h2>

    <div id="myTabContent" class="tab-content">
    <hr>
    <div class="tab-pane fade active in" id="detail">
        <h4>История профиля</h4>
        <table class="table table-th-block">
            <tbody>
            <tr><td class="active">Зарегистрирован:</td><td>12-06-2016</td></tr>
            <tr><td class="active">Последняя активность:</td><td>12-06-2016 / 09:11</td></tr>
            <tr><td class="active">Страна:</td><td>Россия</td></tr>
            <tr><td class="active">Город:</td><td>Волгоград</td></tr>
            <tr><td class="active">Пол:</td><td>Мужской</td></tr>
            <tr><td class="active">Полных лет:</td><td>43</td></tr>
            <tr><td class="active">Семейное положение:</td><td>Женат</td></tr>
            <tr><td class="active">Рейтинг пользователя:</td><td><i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> 4/5</td></tr>
            <tr><td class="active">Плагин рейтинга:</td><td><a href="http://bootstraptema.ru/stuff/plugins_bootstrap/improvement/bootstrap_star_rating/12-1-0-73" target="_blank">http://goo.gl/bGGXuw</a></td></tr>
            </tbody>
        </table>
    </div>
   </div>
</main>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./Dashboard Template for Bootstrap_files/jquery-3.2.1.slim.min.js.Без названия" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="./Dashboard Template for Bootstrap_files/popper.min.js.Без названия"></script>
<script src="./Dashboard Template for Bootstrap_files/bootstrap.min.js.Без названия"></script>

<!-- Icons -->
<script src="./Dashboard Template for Bootstrap_files/feather.min.js.Без названия"></script>
<script>
    feather.replace()
</script>

<!-- Graphs -->
<script src="./Dashboard Template for Bootstrap_files/Chart.min.js.Без названия"></script>
<script>
    var ctx = document.getElementById("myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            datasets: [{
                data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false,
            }
        }
    });
</script>


</body></html>