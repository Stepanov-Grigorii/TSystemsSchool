<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Список заказов</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <c:url value="/admin/orders/form" var="newOrder"/>
    <div class="d-grid gap-2">
        <a href="${newOrder}" class="btn btn-success" role="button">Добавить заказ</a>
    </div>
    <table class="table table-light table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Номер</th>
            <th scope="col">Фура</th>
            <th scope="col">Статус</th>
            <th scope="col">Пункт отправки</th>
            <th scope="col">Пункт доставки</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${order.getNumber()}</td>
                <td>${order.getWagonRegistryNumber()}</td>
                <td>${order.getStatus()}</td>
                <td>${order.getDeparture()}</td>
                <td>${order.getDestination()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>