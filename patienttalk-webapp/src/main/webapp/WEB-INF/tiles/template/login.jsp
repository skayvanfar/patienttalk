<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/10/2017
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="columns medium-4 medium-offset-4">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <spring:message code="addProduct.form.productId.label"/><br/>
        </div>
    </c:if>
    <c:url value="/login/p" var="loginUrl"/> <%-- todo --%>
    <form:form modelAttribute="loginData" action="/login/p">
        <form:text path="username" placeholder="نام کاربری" iconRight="icon-user" label="نام کاربری"/>
        <form:password path="password" placeholder="کلمه عبور" iconRight="icon-lock" label="کلمه عبور"/>
        <sf:hidden path="redirect"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <form:button label="ورود" cssClass="gray" />
    </form:form>
</div>
