<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../parts/header.jsp" %>
<div class="container">
    <h1>Data points</h1>

    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th>User</th>
            <th>When</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${datapoint}">
            <tr>
                <td><c:out value="${entry.user.email}"/></td>
                <td><c:out value="${entry.formatedDateTime}"/></td>
                <td>
                    <button type="button" class="btn btn-primary">View</button>
                    <button type="button" class="btn btn-primary">Edit</button>
                    <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="#">Add a datapoint</a>
</div>
<%@include file="../parts/footer.jsp" %>
