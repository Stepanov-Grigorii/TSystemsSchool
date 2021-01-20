<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Редактирование заказа</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row mt-3">
        <div class="col">
            <c:url value="/orders/save" var="savelink"/>
            <form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="orderDTO">
                <legend>
                    <c:if test="${orderDTO.id eq null}">
                        Создание заказа
                    </c:if>
                    <c:if test="${orderDTO.id ne null}">
                        Редактирование заказа
                    </c:if>
                </legend>
                <div class="mb-3">
                    <form:label path="number" class="form-label">Номер:</form:label>
                    <form:input path="number" class="form-control" id="order-number" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Номер заказа....</div>
                </div>
                <div class="mb-3">
                    <form:label path="cargoId" class="form-label">Груз:</form:label>
                    <form:select path="cargoId" items="${orderDTO.cargoDtoList}" itemLabel="name" itemValue="id" multiple="false" class="form-select"/>

                </div>
                <div class="mb-3">
                    <form:label path="wagonId" class="form-label">Регистрационный номер фуры:</form:label>
                    <form:select path="wagonId" items="${orderDTO.wagonDtoList}" itemLabel="registryNumber" itemValue="id" multiple="false" class="form-select"/>

                </div>
                <div class="mb-3">
                    <form:label path="waypointId" class="form-label">Пункт назначения:</form:label>
                    <form:select path="waypointId" items="${orderDTO.waypointDtoList}" itemLabel="name" itemValue="id" multiple="false" class="form-select"/>

                </div>


                <form:hidden path="id"/>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form:form>
        </div>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>