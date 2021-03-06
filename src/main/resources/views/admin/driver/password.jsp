<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Change Password</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <p class="text-center">Use the form below to change your password. Your password cannot be the same as your
                username.</p>
            <c:url value="/admin/drivers/change-password" var="changePassword"/>
            <form:form method="post" id="passwordForm" action="${changePassword}" autocomplete="false"
                       modelAttribute="driver">
                <form:input path="password" type="password" class="input-lg form-control" name="password1"
                            id="password1" placeholder="New Password" autocomplete="off"/>
                <div class="row">
                    <div class="col-sm-6">
                        <span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> 8 Characters
                        Long<br>
                        <span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase
                        Letter
                    </div>
                    <div class="col-sm-6">
                        <span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase
                        Letter<br>
                        <span id="num" class="glyphicon glyphicon-remove" style="color:#ff0004;"></span> One Number
                    </div>
                </div>

                <input path="password" type="password" class="input-lg form-control" name="password2" id="password2"
                       placeholder="Repeat Password" autocomplete="off"/>
                <div class="row">
                    <div class="col-sm-12">
                        <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords
                        Match
                    </div>
                </div>
                <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg"
                       data-loading-text="Changing Password..." value="Change Password">
            </form:form>
        </div><!--/col-sm-6-->
    </div><!--/row-->
</div>