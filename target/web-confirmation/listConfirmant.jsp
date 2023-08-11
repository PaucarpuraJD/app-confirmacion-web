<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">LIST OF FAMILIES</h2>
<form action="${pageContext.request.contextPath}/confirmants" method="post">
    <div class="input-group mb-3 mt-4">
        <div class="col-sm-3">
            <input type="text" name="name" class="form-control" autofocus>
        </div>
        <div>
            <input type="submit" value="Search" class="btn btn-secondary">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/confirmants/form"> Create [+] </a>
        </div>
    </div>
</form>
<table class="table table-hover table-striped" style="text-align: center">
    <thead>
        <tr>
            <th>CONFIRMANT</th>
            <th>SACRAMENT</th>
            <th>ADDRESS</th>
            <th>PHONE</th>
            <th>BIRTHDATE</th>
            <th>MOTHER</th>
            <th>MOTHER-PHONE</th>
            <th>EDIT</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${confirmants}" var="c">
            <tr>
                <td>${c.name}</td>
                <td>${c.sacrament.name}</td>
                <td>${c.address}</td>
                <td>${c.phone}</td>
                <td>${c.birthDate}</td>
                <td>${c.family.motherName}</td>
                <td>${c.family.motherPhone}</td>
                <td>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/confirmants/form?id=${c.id}">
                        edit
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="layout/footer.jsp" />