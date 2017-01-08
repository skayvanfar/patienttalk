<h2>Hello World!</h2>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>
<sf:form modelAttribute="test" action="/manage/production/edit">
    <div class="row">

        <div class="col-md-9">
            <form:text path="name" iconLeft="icon-circle_ok" placeholder="name" label="name"/>
                <%--<form:text path="model" placeholder="مدل" label="مدل"/>--%>
                <%--<form:tag path="parent" maxCount="1" placeholder="محصول اصلی"--%>
                <%--label="محصول اصلی"--%>
                <%--filterUrl="/manage/filter/production"/>--%>
                <%--<form:textarea path="description" placeholder="توضیحات" label="توضیحات"/>--%>
                <%--<form:tag path="artists" placeholder="هنرمند" filterUrl="/manage/filter/artist"--%>
                <%--label="هنرمند"/>--%>
                <%--</div>--%>
                <%--<div class="columns medium-6 small-6">--%>
                <%--<form:text path="mainPrice" placeholder="قیمت" label="قیمت"/>--%>
                <%--<form:text path="discountedPrice" placeholder="قیمت با تخفیف" label="قیمت با تخفیف"/>--%>
                <%--<form:tag path="tags" placeholder="تگ‌ها" filterUrl="/manage/filter/tag" createUrl="/manage/create/tag"--%>
                <%--label="تگ ها"/>--%>
                <%--<form:tag path="categories" placeholder="دسته‌ها" filterUrl="/manage/filter/category"--%>
                <%--label="دسته ها"/>--%>
                <%--<form:text path="availableCount" placeholder="موجودی" label="موجودی"/>--%>
        </div>
    </div>
</sf:form>