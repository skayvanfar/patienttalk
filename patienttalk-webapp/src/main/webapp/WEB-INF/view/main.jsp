<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<jsp:include page="/WEB-INF/tiles/template/home/guest-info.jsp"/>--%> <!-- todo -->

<div class="column small-8 medium-8">
    <div class="my-panel">
        <div class="my-panel-header">
            <h3 class="my-panel-title">انجمن ها</h3>
        </div>
        <div class="my-panel-body">
            <c:forEach var="forum" items="${channel.forums}" varStatus="forIndex">
                <u:forum data="${forum}"/>
            </c:forEach>
        </div>
        <div class="my-panel-footer">
            <h3 class="my-panel-title">انجمن ها</h3>
        </div>
    </div>
</div>
<aside class="column small-4 medium-4">
    <div class="my-panel">
        <div class="my-panel-header">
            <h3 class="my-panel-title">وزوز گوش</h3>
        </div>
        <div class="my-panel-body">
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
        </div>
        <div class="my-panel-footer">
            <h3 class="my-panel-title">وزوز گوش</h3>
        </div>
    </div>
    <div class="my-panel">
        <div class="my-panel-header">
            <h3 class="my-panel-title">وزوز گوش</h3>
        </div>
        <div class="my-panel-body">
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
            <p>فعلا</p>
        </div>
        <div class="my-panel-footer">
            <h3 class="my-panel-title">وزوز گوش</h3>
        </div>
    </div>
</aside>

