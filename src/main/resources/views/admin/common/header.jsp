<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><i class="fas fa-truck me-2 text-primary"></i> Моя программа</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <c:url value="/drivers/list" var="driverslink"/>
                        <a class="nav-link active" aria-current="page" href="${driverslink}">Водители</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>