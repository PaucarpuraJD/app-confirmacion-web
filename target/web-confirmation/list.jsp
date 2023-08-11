<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">LIST OF SACRAMENTS</h2>
<form action="${pageContext.request.contextPath}/sacraments" method="post">
    <div class="input-group mb-3 mt-4">
        <div class="col-sm-3">
            <input type="text" name="name" class="form-control" autofocus>
        </div>
        <div>
            <input type="submit" value="Search" class="btn btn-secondary">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/sacraments/form"> Create [+] </a>
        </div>
    </div>
</form>
<table class="table table-hover table-striped" style="text-align: center">
    <thead>
        <tr>
            <th>ID</th>
            <th>SACRAMENT</th>
            <th>EDIT</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${sacraments}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/sacraments/form?id=${s.id}">
                        edit
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="layout/footer.jsp" />