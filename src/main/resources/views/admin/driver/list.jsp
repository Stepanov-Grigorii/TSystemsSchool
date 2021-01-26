<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Список водителей</title>
    <style>
        .icon-button{
            background: none;
            padding: 0;
            border: none;
        }
        .little-form{
            display: inline;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach var="driver" items="${drivers}">
            <c:url value="/admin/drivers/form/${driver.id}" var="editDriver"/>
            <c:url value="/admin/drivers/delete/${driver.id}" var="deleteDriver"/>
            <div class="col-2 mt-5">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${driver.name} ${driver.surname}</h5>
                        <p class="card-text">Идентификационный номер: ${driver.identityNumber}</p>
                        <a href="${editDriver}"><i class="fas fa-user-edit"></i></a>
                        <form method="post" class="little-form" action="${deleteDriver}">
                            <button class="icon-button"><i class="fas fa-trash-alt text-primary"></i></button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="col-2 mt-5">
            <c:url value="/admin/drivers/form" var="newDriver"/>
            <a href="${newDriver}">
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