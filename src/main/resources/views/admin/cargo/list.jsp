<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Список грузов</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach var="cargo" items="${cargoes}">
            <c:url value="/admin/cargoes/form/${cargo.id}" var="editCargo"/>
            <div class="col-2 mt-5">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${cargo.number}</h5>
                        <p class="card-text">Содержимое: ${cargo.name}</p>
                        <p class="card-text">Вес: ${cargo.weight}</p>
                        <p class="card-text">Статус: ${cargo.status}</p>
                        <a href="${editCargo}"><i class="fas fa-user-edit"></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="col-2 mt-5">
            <c:url value="/admin/cargoes/form" var="newCargo"/>
            <a href="${newCargo}">
            <span style="font-size: 7rem">
                <i class="fas fa-plus-circle text-success"></i>
            </span>
            </a>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>