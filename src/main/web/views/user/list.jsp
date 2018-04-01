<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="content">
    <div class="container">
        <h2>Users</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last Name</th>
                        <th>Position</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.position}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td><a href="<c:url value='/user/edit/${user.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/user/delete/${user.id}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</rapid:override>

<%@ include file="../base.jsp" %>