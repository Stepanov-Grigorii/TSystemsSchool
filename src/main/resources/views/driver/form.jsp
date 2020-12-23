<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Редактирование водителя</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row mt-3">
        <div class="col">
            <c:url value="/drivers/save" var="savelink"/>
            <form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="driver">
                <legend>
                    <c:if test="${driver.id eq null}">
                        Создание водителя
                    </c:if>
                    <c:if test="${driver.id ne null}">
                        Редактирование водителя
                    </c:if>
                </legend>
                <div class="mb-3">
                    <form:label path="name" class="form-label">Имя</form:label>
                    <form:input path="name" class="form-control" id="driver-name" aria-describedby="nameHelp"/>
                    <div id="nameHelp" class="form-text">Имя водителя не должно быть пустым.</div>
                </div>

                <div class="mb-3">
                    <form:label path="surname" class="form-label">Фамилия</form:label>
                    <form:input path="surname" class="form-control" id="driver-surname" aria-describedby="surnameHelp"/>
                    <div id="surnameHelp" class="form-text">Фамилия водителя не должна быть пустой.</div>
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