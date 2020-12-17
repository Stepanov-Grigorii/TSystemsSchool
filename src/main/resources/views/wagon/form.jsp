<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Редактирование фуры</title>
</head>
<body>
<h1>
    <c:if test="${wagon.id eq null}">
        Создание фуры
    </c:if>
    <c:if test="${wagon.id ne null}">
        Редактирование фуры
    </c:if>
</h1>
<c:url value="/wagons/save" var="savelink"/>
<form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="wagon">
    <p>
        <form:label path="registryNumber">Регистрационный номер:</form:label>
        <form:input path="registryNumber" autocomplete="false"/>
    </p>
    <p>
        <form:label path="capacity">Вместимость:</form:label>
        <form:input path="capacity" autocomplete="false"/>
    </p>
    <form:hidden path="id"/>
    <input type="submit" value="Сохранить"/>
</form:form>
</body>
</html>