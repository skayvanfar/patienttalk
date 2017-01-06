<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/6/2017
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:insertAttribute name="title"/></title>

    <script src="<c:url value="/resources/js/jquery-2.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/core.js" />"></script>

    <link href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>

<tiles:insertAttribute name="navigation"/>
<div tabindex="-1" id="content" class="bs-docs-header">
    <div class="container">
        <h1><tiles:insertAttribute name="heading"/></h1>

        <p><tiles:insertAttribute name="tagline"/></p>
    </div>
</div>
<div class="container bs-docs-container">
    <tiles:insertAttribute name="content"/>
</div>
<footer role="contentinfo" class="bs-docs-footer">
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>
