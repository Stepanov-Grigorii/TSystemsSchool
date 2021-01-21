<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <c:if test="${wagon.id eq null}"><title>Создание фуры</title></c:if>
    <c:if test="${wagon.id ne null}"><title>Редактирование фуры</title></c:if>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row mt-3">
        <div class="col">
            <c:url value="/admin/wagons/save" var="savelink"/>
            <form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="wagonDTO">
                <legend>
                    <c:if test="${wagonDTO.id eq null}">
                        Создание фуры
                    </c:if>
                    <c:if test="${wagonDTO.id ne null}">
                        Редактирование фуры
                    </c:if>
                </legend>
                <div class="mb-3">
                    <form:label path="brand" class="form-label">Марка:</form:label>
                    <form:input path="brand" class="form-control" id="wagon-brand" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Укажите марку фуры.</div>
                </div>
                <div class="mb-3">
                    <form:label path="registryNumber" class="form-label">Регистрационный номер:</form:label>
                    <form:input path="registryNumber" class="form-control" id="wagon-registryNumber" aria-describedby="nameHelp"/>

                </div>
                <div class="mb-3">
                    <form:label path="capacity" class="form-label">Вместимость:</form:label>
                    <form:input path="capacity" class="form-control" id="wagon-capacity" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Укажите вместимость в тоннах.</div>
                </div>
                <div class="mb-3">
                    <form:label path="cityId" class="form-label">Город прибывания:</form:label>
                    <form:select path="cityId" items="${wagonDTO.cityDtoList}" itemLabel="name" itemValue="id" multiple="false" class="form-select"/>
                    <div id="nameHelp" class="form-text">Укажите город.</div>
                </div>
                <div class="mb-3">
                    <form:label path="status" class="form-label">Статус:</form:label>
                    <form:select path="status" items="${wagonDTO.statusNames}" multiple="false" class="form-select"/>
                    <div id="nameHelp" class="form-text">Выберете статус.</div>
                </div>


                <form:hidden path="id"/>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form:form>
        </div>
    </div>
</div>
<%--<h1>--%>
<%--    <c:if test="${wagon.id eq null}">--%>
<%--        Создание фуры--%>
<%--    </c:if>--%>
<%--    <c:if test="${wagon.id ne null}">--%>
<%--        Редактирование фуры--%>
<%--    </c:if>--%>
<%--</h1>--%>
<%--<c:url value="/wagons/save" var="savelink"/>--%>
<%--<form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="wagon">--%>
<%--    <p>--%>
<%--        <form:label path="registryNumber">Регистрационный номер:</form:label>--%>
<%--        <form:input path="registryNumber" autocomplete="false"/>--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        <form:label path="capacity">Вместимость:</form:label>--%>
<%--        <form:input path="capacity" autocomplete="false"/>--%>
<%--    </p>--%>
<%--    <form:hidden path="id"/>--%>
<%--    <input type="submit" value="Сохранить"/>--%>
<%--</form:form>--%>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>