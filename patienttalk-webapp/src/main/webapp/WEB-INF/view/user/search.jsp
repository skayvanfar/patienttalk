<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 9/8/2017
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<%@ taglib prefix="u" uri="http://sk.ir/patienttalk/utils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="filter" uri="http://sk.ir/common/tags/security-filter" %>


<div class="my-panel">
    <div class="my-panel-header">
        <h3 class="my-panel-title">کاربران</h3>
    </div>
    <div class="my-panel-body">
        <div class="column small-12 medium-12 large-12">
            <div class="my-panel">
                <div class="my-panel-body">
                    <form:form modelAttribute="userSearchData">
                        <div class="column small-4 medium-4 large-4">
                            <form:text path="id" placeholder="id" label="id" maxlength="20"/>
                        </div>
                        <div class="column small-4 medium-4 large-4">
                            <form:text path="username" placeholder="نام کاربری" label="نام"/>
                        </div>
                        <div class="column small-4 medium-4 large-4">
                            <form:text path="password" placeholder="پسورد" label="پسورد"/>
                        </div>
                        <div class="column small-2 medium-2 large-2"><form:button label="جستجو"/></div>
                    </form:form>
                </div>
            </div>

            <div class="my-panel">
                <table style="width:100%">
                    <tr class="my-panel-header">
                        <th>ردیف</th>
                        <th>نام</th>
                        <th>پسورد</th>
                        <th>وضعیت آنلاین</th>
                        <%--
                                        <th>تغییر</th>
                        --%>
                        <th>ddd</th>
                    </tr>
                    <c:forEach var="user" items="${users.data}" varStatus="forIndex">
                        <tr>
                            <td>${users.page*users.pageSize + forIndex.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.onlineStatus}</td>
                            <td>
                                <a href="<filter:hashLongUrl url="/manage/user" name="id" value="${user.id}" />" class="radius button split">
                                    ویرایش
                                    <span data-dropdown="drop${forIndex.index}"></span>
                                </a>
                                <ul id="drop${forIndex.index}" class="f-dropdown" data-dropdown-content>
                                    <li>
                                        <a href="<filter:hashLongUrl url="/manage/artist" name="id" value="${user.id}" />">
                                            حذف
                                        </a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="my-panel-footer">
                    <form:paging name="page" method="get" modelAttribute="userSearchData" list="${users}" cssClass="paging">
                    </form:paging>
                </div>
            </div>
        </div>
    </div>
    <div class="my-panel-footer">
        <h3 class="my-panel-title"></h3>
    </div>
</div>


