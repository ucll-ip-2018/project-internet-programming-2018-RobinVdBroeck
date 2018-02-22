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
                    <c:out value="${user.currentDisplayName}"/>
                </td>
                <td><
                    <button type="button" class="btn btn-primary">View</button>
                    <button type="button" class="btn btn-primary">Edit</button>
                    <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="/WEB-INF/parts/footer.jsp" %>
