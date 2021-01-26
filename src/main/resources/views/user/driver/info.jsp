<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Список водителей</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <table class="table table-striped">
        <tr>
            <th style="width: 20%">Имя Фамилия</th>
            <td>${driver.name} ${driver.surname}</td>
        </tr>
        <tr>
            <th>Идентификационный номер</th>
            <td>${driver.identityNumber}</td>
        </tr>
        <tr>
            <th>Часы в текущем месяце</th>
            <td>${driver.hoursInCurrentMonth}</td>
        </tr>
        <c:if test="${driver.wagon ne null}">
            <tr>
                <th>Фура</th>
                <td>${driver.wagon}</td>
            </tr>
            <tr>
                <th>Город</th>
                <td>${driver.city}</td>
            </tr>
            <tr>
                <th>Номера со-водителей</th>
                <td>
                    <table>
                        <c:forEach var="driverNumber" items="${driver.driverIdentityNumbers}">
                            <tr>
                                <td>${driverNumber}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <c:if test="${driver.orderNumbers ne null}">
                <th>Номера заказов</th>
                <td>
                    <table>
                        <c:forEach var="order" items="${driver.orders}">
                            <%--                        <c:forEach var="orderNumber" items="${driver.orderNumbers}">--%>
                            <tr>
                                <td>${order.number} - </td>
                                <td>${order.status.name} </td>
                                <td> Груз </td>
                                <td> ${order.actionDeparture.cargo.number} - </td>
                                <td> ${order.actionDeparture.cargo.status.name}  </td>
                                    <%--                                <td>${orderNumber}</td>--%>
                                    <%--                                <td>${}</td>--%>
                                <c:if test="${order.actionDeparture.cargo.status.name ne 'Доставлен'}">
                                    <td>
                                        <c:url value="/user/drivers/order-status?id=${driver.id}&number=${order.number}"
                                               var="editOrderStatus"/>
                                        <form method="post" action="${editOrderStatus}">
                                            <button class="btn btn-primary">Изменить</button>
                                        </form>
<%--                                        <a href="${editOrderStatus}" type="button" class="btn btn-primary">Изменить</a>--%>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </c:if>
        </c:if>
    </table>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>