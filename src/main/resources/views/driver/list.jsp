<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список водителей</title>
</head>
<body>
<ul>
    <c:forEach var="driver" items="${drivers}">
        <li>
            <c:url value="/drivers/form/${driver.id}" var="editDriver"/>
                ${driver.identityNumber} ${driver.name} ${driver.surname} <a href="${editDriver}"><button>Редактировать</button></a>
        </li>
    </c:forEach>
</ul>
<c:url value="/drivers/form" var="newDriver"/>
<a href="${newDriver}"><button>Добавить водителя</button></a>
</body>
</html>