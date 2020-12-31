<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список фур</title>
</head>
<body>
<ul>
    <c:forEach var="wagon" items="${wagons}">
        <li>
            <c:url value="/wagons/form/${wagon.id}" var="editWagon"/>
                ${wagon.id} ${wagon.registryNumber} ${wagon.capacity} <a href="${editWagon}"><button>Редактировать</button></a>
        </li>
    </c:forEach>
</ul>
<c:url value="/wagons/form" var="newWagon"/>
<a href="${newWagon}"><button>Добавить фуру</button></a>
</body>
</html>