<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Редактирование водителя</title>
</head>
<body>
<h1>
    <c:if test="${driver.id eq null}">
        Создание водителя
    </c:if>
    <c:if test="${driver.id ne null}">
        Редактирование водителя
    </c:if>
</h1>
<c:url value="/drivers/save" var="savelink"/>
<form:form method="post" action="${savelink}" autocomplete="false" modelAttribute="driver">
    <p>
        <form:label path="name">Имя:</form:label>
        <form:input path="name" autocomplete="false"/>
    </p>
    <p>
        <form:label path="surname">Фамилия:</form:label>
        <form:input path="surname" autocomplete="false"/>
    </p>
    <form:hidden path="id"/>
    <input type="submit" value="Сохранить"/>
</form:form>
</body>
</html>