<%--
  Created by IntelliJ IDEA.
  User: sad.keyvanfar
  Date: 1/11/2017
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>
<%@ taglib prefix="f" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="data" type="ir.sk.patienttalk.model.domain.Forum" required="true" rtexprvalue="true" %>

<div class="my-panel">
    <div class="my-panel-header">
        <h3 class="my-panel-title">${data.name}</h3>
    </div>
    <div class="my-panel-body">
        <c:forEach var="subForum" items="${data.subForums}" varStatus="forIndex">
            <u:sub-forum data="${subForum}"/>
        </c:forEach>
    </div>
</div>