<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/86bfa24c64.js" crossorigin="anonymous"></script>
    <title>Аутентификация</title>
</head>
<body class="text-center">
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
        <div class="position-absolute top-50 start-50 translate-middle">
            <form method="post" action="login">
                <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
                <p>
                    <label for="inputLogin" class="visually-hidden">Логин</label>
                    <input name="username" id="inputLogin" class="form-control" placeholder="Логин" required="" autofocus="">
<%--                    Логин:--%>
<%--                    <input name="username">--%>
                </p>
                <p>
                    <label for="inputPassword" class="visually-hidden">Password</label>
                    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
<%--                    Пароль:--%>
<%--                    <input name="password" type="password">--%>
                </p>
                <c:if test="${param.error ne null}">
                    test
                </c:if>
                <button class="w-50 btn btn-lg btn-primary" type="submit">Войти</button>
                <%--            <button>Войти</button>--%>
            </form>
        </div>
    </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>