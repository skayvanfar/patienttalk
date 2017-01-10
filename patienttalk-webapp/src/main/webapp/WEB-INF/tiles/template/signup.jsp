<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/10/2017
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>

<%--@elvariable id="signupData" type="ir.arusha.artmart.web.logic.data.SignupData"--%>
<c:if test="${!signupData.succeed}">
    <form:form modelAttribute="signupData" action="/signup">
        <div class="columns medium-4">
            <form:text path="profile.name" placeholder="نام" label="نام"/>
            <form:text path="profile.family" placeholder="نام خانوادگی" label="نام خانوادگی"/>
            <form:text path="mail" placeholder="ایمیل" label="ایمیل"/>
        </div>
        <div class="columns medium-4">
            <form:text path="username" placeholder="نام کاربری" label="نام کاربری"/>
            <form:password path="password1" placeholder="کلمه عبور" label="کلمه عبور"/>
            <form:password path="password2" placeholder="تکرار کلمه عبور" label="تکرار کلمه عبور"/>
        </div>
        <div class="columns medium-4">
            <form:text path="profile.nickname" placeholder="نام مستعار" label="نام مستعار"/>
            <form:file path="profile.picture" label="تصویر پروفایل کاربر" async="true"/>
            <form:button label="ثبت نام" cssClass="gray"/>
        </div>
        <sf:hidden path="redirect"/>
    </form:form>
</c:if>
<c:if test="${signupData.succeed}">
    <div class="columns medium-6 medium-offset-3" id="">
        <p class="s-signup-succeed-icon">
            <i class="icon-circle_ok" ></i>
        </p>
        <p class="s-signup-succeed-text">
            متشکریم :) ثبت نام شما با موفقیت انجام شد.
        </p>
        <p class="s-signup-succeed-text">
            برای تایید حساب کاربری خود ایمیلتان را بررسی نمایید
        </p>
        <c:if test="${not empty signupData.redirect}">
            شما به طور خودکار به صفحه مورد نظر منتقل می‌شوید.
            <script>
                setTimeout(redirectMe, 5000);
                function redirectMe() {
                    window.location = '<s:url value="redirectLogin?redirect=${signupData.redirect}"/>';
                }
            </script>
            شما می‌توانید روی
            <a href="<s:url value="${signupData.redirect}"/>">
                این لینک
            </a>
            نیز کلیک نمایید. توجه کنید که برای ادامه کار باید در سیستم وارد شوید.
        </c:if>
    </div>
</c:if>
