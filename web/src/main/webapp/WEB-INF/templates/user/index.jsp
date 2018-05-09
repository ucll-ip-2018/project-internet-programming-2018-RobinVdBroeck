<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/parts/header.jsp" %>
<div class="container">
    <h1>Users</h1>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Email</th>
            <th>Display Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.displayName}"/>
                </td>
                <td>
                    <a href="<c:url value="/user/${user.id}"/>" class="btn btn-primary">
                        View
                    </a>
                    <a href="<c:url value="/user/${user.id}/edit" />" class="btn btn-primary">
                        Edit
                    </a>
                    <a href="<c:url value="/user/${user.id}/delete"/>" class="btn btn-danger">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/user/create">Add a user</a>
</div>

<%@include file="/WEB-INF/parts/footer.jsp" %>
