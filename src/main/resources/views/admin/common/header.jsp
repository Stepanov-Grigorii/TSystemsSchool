<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><i class="fas fa-truck me-2 text-primary"></i> Logiweb</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <c:url value="/admin/drivers/list" var="driverslink"/>
                        <a class="nav-link active" aria-current="page" href="${driverslink}">Водители</a>
                    </li>
                    <li class="nav-item">
                        <c:url value="/admin/wagons/list" var="wagonslink"/>
                        <a class="nav-link active" aria-current="page" href="${wagonslink}">Фуры</a>
                    </li>
                    <li class="nav-item">
                        <c:url value="/admin/cargoes/list" var="cargoeslink"/>
                        <a class="nav-link active" aria-current="page" href="${cargoeslink}">Грузы</a>
                    </li>
                    <li class="nav-item">
                        <c:url value="/admin/orders/list" var="orderslink"/>
                        <a class="nav-link active" aria-current="page" href="${orderslink}">Заказы</a>
                    </li>
                </ul>
                <c:url value="/logout" var="logoutlink"/>
                <form class="d-flex" action="${logoutlink}" method="post">
                    <button class="btn btn-outline-success" type="submit">Выйти</button>
                </form>
            </div>
        </div>
    </nav>
</header>