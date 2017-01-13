<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/13/2017
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="column small-12 medium-12">
        <div class="my-panel">
            <div class="my-panel-header">
                <form:paging cssClass="paging" name="page" method="get" list="${threadPagingDataList}"/>
            </div>
            <div class="my-panel-body">
                <div class="my-panel">
                    <div class="my-panel-header row ">
                        <dl class="section-headers">
                            <dd class="column small-1 medium-1">
                                نخ ها
                            </dd>
                            <dd class="main column small-6 medium-6>
                                <a href="forums/research-news.4/?order=title" class="title "><span>عنوان</span></a>
                                <a href="forums/research-news.4/?order=post_date" class="postDate " style="float: left"><span>تاریخ شروع</span></a>
                            </dd>
                            <dd class="stats column small-3 medium-3">
                                <a href="forums/research-news.4/?order=reply_count" class="major"><span>تعداد پاسخ</span></a>
                                <a href="forums/research-news.4/?order=view_count" class="minor"><span>تعداد مشهاهده</span></a>
                            </dd>
                            <dd class="lastPost column small-2 medium-2"><a href="forums/research-news.4/?direction=asc"><span>آخرین پیام  ↓</span></a></dd>
                        </dl>
                    </div>
                    <div class="my-panel-body">
                        <c:forEach var="thread" items="${threadPagingDataList.data}" varStatus="forIndex">
                            <u:thread data="${thread}"/>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="my-panel-footer">
                <form:paging cssClass="paging" name="page" method="get" list="${threadPagingDataList}"/>
            </div>
        </div>
</div>