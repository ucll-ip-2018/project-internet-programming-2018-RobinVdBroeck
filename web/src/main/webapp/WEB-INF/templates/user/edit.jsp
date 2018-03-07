<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/parts/header.jsp" %>
<div class="container">
    <h1>Create User</h1>
    <form:form modelAttribute="runescapeUser" method="post"
               action="${pageContext.request.contextPath}/user/${runescapeUser.id}/edit">
        <div class="form-group row">
            <label for="id" class="col-sm-4">Email:</label>
            <form:input path="id" class="form-group col-sm-8" autocomplete="false" disabled="true"/>
            <form:errors path="id" cssClass="error"/>
        </div>

        <div class="form-group row">
            <label for="Email" class="col-sm-4">Email:</label>
            <form:input path="email" class="form-group col-sm-8"/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group row">
            <label for="displayName" class="col-sm-4">Display name:</label>
            <form:input path="displayName" class="form-group col-sm-8"/>
            <form:errors path="displayName" cssClass="error"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Create</button>
        </div>
    </form:form>
</div>
<%@include file="/WEB-INF/parts/footer.jsp" %>
