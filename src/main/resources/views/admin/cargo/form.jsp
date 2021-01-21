<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Редактирование груза</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div class="container">
    <div class="row mt-3">
        <div class="col">
            <c:url value="/admin/cargoes/save" var="savelink"/>
            <form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="cargoDTO">
                <legend>
                    <c:if test="${cargoDTO.id eq null}">
                        Создание груза
                    </c:if>
                    <c:if test="${cargoDTO.id ne null}">
                        Редактирование груза
                    </c:if>
                </legend>
                <div class="mb-3">
                    <form:label path="name" class="form-label">Содержимое</form:label>
                    <form:input path="name" class="form-control" id="cargo-name" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Пункт "Содержимое" не должен быть пустым.</div>
                </div>
                <div class="mb-3">
                    <form:label path="number" class="form-label">Номер</form:label>
                    <form:input path="number" class="form-control" id="cargo-number" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Номер груза не должен быть пустым.</div>
                </div>
                <div class="mb-3">
                    <form:label path="weight" class="form-label">Вес</form:label>
                    <form:input path="weight" class="form-control" id="cargo-weight" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Вес груза указан в тоннах.</div>
                </div>
                <div class="mb-3">
                    <form:label path="waypointDtoList" class="form-label">Пункт отправки:</form:label>
                    <form:select path="waypointId" items="${cargoDTO.waypointDtoList}" itemLabel="name" itemValue="id" multiple="false" class="form-select"/>

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