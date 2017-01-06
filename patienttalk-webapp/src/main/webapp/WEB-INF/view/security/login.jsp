<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/6/2017
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--
    <title><tiles:insertAttribute name="title"/></title>
--%>

    <script src="<c:url value="/resources/js/jquery-2.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/core.js" />"></script>

    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>
<div class="container" id="content">
    <div class="col-md-4 col-md-offset-4" style="margin-top:200px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Please sign in</h3>
            </div>
            <div class="panel-body">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br/>
                    </div>
                </c:if>
                <c:url value="/login" var="loginUrl"/>
                <form action="<c:url value="${loginUrl}"></c:url>" method="post">
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="User Name" name='username' type="text">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Password" name='password' type="password" value="">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
