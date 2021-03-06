<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Список фур</title>
    <style>
        .icon-button{
            background: none;
            padding: 0;
            border: none;
        }
        .little-form{
            display: inline;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <c:url value="/admin/wagons/form" var="newWagon"/>
    <div class="d-grid gap-2">
        <a href="${newWagon}" class="btn btn-success" role="button">Новая фура</a>
    </div>
    <table class="table table-light table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Марка</th>
            <th scope="col">Регистрационный номер</th>
            <th scope="col">Вместимость (тонны)</th>
            <th scope="col">Размер смены водителей</th>
            <th scope="col">Состояние</th>
            <th scope="col">Город</th>
            <th scope="col">Заказ</th>
            <th scope="col">Водители</th>
            <th scope="col">Изменить</th>
            <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="wagon" items="${wagons}" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${wagon.brand}</td>
                <td>${wagon.registryNumber}</td>
                <td>${wagon.capacity}</td>
                <td>${wagon.driverNumber}</td>
                <td>${wagon.status}</td>
                <td>${wagon.city}</td>
                <td>${wagon.order}</td>
                <td>
                    <select name="drivers">
                        <c:forEach items="${wagon.drivers}" var="driver">
                            <option value="${driver}">
                                    ${driver}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <c:url value="/admin/wagons/form/${wagon.id}" var="editWagon"/>
                <td><a href="${editWagon}" type="button" class="btn btn-primary">Изменить</a></td>
                <td>
                    <c:url value="/admin/wagons/delete/${wagon.id}" var="deleteWagon"/>
                    <form method="post" class="little-form" action="${deleteWagon}">
                        <button class="icon-button"><i class="fas fa-trash-alt text-primary"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--<ul>--%>
<%--    <c:forEach var="wagon" items="${wagons}">--%>
<%--        <li>--%>
<%--            <c:url value="/wagons/form/${wagon.id}" var="editWagon"/>--%>
<%--                ${wagon.id} ${wagon.registryNumber} ${wagon.capacity} <a href="${editWagon}"><button>Редактировать</button></a>--%>
<%--        </li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>
<%--<c:url value="/wagons/form" var="newWagon"/>--%>
<%--<a href="${newWagon}"><button>Добавить фуру</button></a>--%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>