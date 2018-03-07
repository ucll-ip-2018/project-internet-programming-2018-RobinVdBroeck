<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/parts/header.jsp" %>
<div class="container">
    <p>
        <strong>Id:</strong> <c:out value="${runescapeUser.id}"/>
    </p>
    <p>
        <strong>Email:</strong> <c:out value="${runescapeUser.email}"/>
    </p>
    <p>
        <strong>Display name:</strong> <c:out value="${runescapeUser.displayName}"/>
    </p>
    <p>
        <a href="<c:url value="/user/" />">
            Go back
        </a>
    </p>
</div>
<%@include file="/WEB-INF/parts/footer.jsp" %>
