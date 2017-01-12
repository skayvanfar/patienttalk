<%--
  Created by IntelliJ IDEA.
  User: sad.keyvanfar
  Date: 1/11/2017
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="filter" uri="http://sk.ir/common/tags/security-filter" %>

<%@ attribute name="data" type="ir.sk.patienttalk.model.domain.SubForum" required="true" rtexprvalue="true" %>

<article class="row node node-info forum-node-info">
    <span class="column small-1 medium-1">
        <i class="icon-pill node-icon"></i>
    </span>
    <div class="column small-5 medium-5 primary-content">
        <div class="node-text">
            <h3 class="node-title">
                <a href="forums/research-news.4/" data-description="#nodeDescription-4">${data.name}</a>
            </h3>
            <div class="node-stats pairs-inline">
                <dl><dt>نخ ها:</dt> <dd>${data.threads.size()}</dd></dl>
                <dl><dt>پیام ها:</dt> <dd>100</dd></dl>
            </div>
        </div>
    </div>

    <div class="column small-6 medium-6 node-last-post secondary-content">
        <a href="members/flimflam.18873/" class="avatar Av18873s" data-avatarhtml="true">
            <img src="/resources/img/cropper/Cancel.png" width="48" height="48" alt="flimflam">
        </a>
        <span class="last-thread-title">
            <a href="posts/226273/" title="Back to Normal">بازگشت به حالت عادی</a>
        </span>
        <span class="last-thread-meta">
            <span class="last-thread-user">
                <a href="members/flimflam.18873/" class="username" dir="auto">سعید</a>,
            </span>
            <abbr class="date-time muted last-thread-date" data-latest="Latest: " data-time="1484191040" data-diff="48959" data-datestring="Jan 12, 2017" data-timestring="6:47 AM" title="Jan 12, 2017 at 6:47 AM">Today at 6:47 AM</abbr>
        </span>
    </div>
</article>
