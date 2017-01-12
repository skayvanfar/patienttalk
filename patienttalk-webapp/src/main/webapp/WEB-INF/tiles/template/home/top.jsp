<%--
  Created by IntelliJ IDEA.
  User: sad.keyvanfar
  Date: 1/11/2017
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>

<u:top-menu arrival="s-content">
    <img src="<s:url value="/resources/img/patienttalk.png"/>"/>
</u:top-menu>
<u:top-menu arrival="newly">
    <img src="<s:url value="/resources/img/newly_logo.png"/>"/>
</u:top-menu>
<u:top-menu arrival="bought">
    <img src="<s:url value="/resources/img/bought_logo.png"/>"/>
</u:top-menu>
<dd class="s-top-section" data-magellan-arrival="s-footer">
</dd>
