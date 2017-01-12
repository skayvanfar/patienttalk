<%--
  Created by IntelliJ IDEA.
  User: sad.keyvanfar
  Date: 1/11/2017
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>
<%@ taglib prefix="f" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="production-box" data-equalizer-watch>
    <c:forEach var="subFroum" items="${forum.subforum.data}" varStatus="forIndex">
        <div class="row">

        </div>
    </c:forEach>
</div>