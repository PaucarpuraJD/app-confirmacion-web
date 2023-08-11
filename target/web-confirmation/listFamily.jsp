<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">LIST OF FAMILIES</h2>
<form action="${pageContext.request.contextPath}/families" method="post">
    <div class="input-group mb-3 mt-4">
        <div class="col-sm-3">
            <input type="text" name="motherName" class="form-control" autofocus>
        </div>
        <div>
            <input type="submit" value="Search" class="btn btn-secondary">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/families/form"> Create [+] </a>
        </div>
    </div>
</form>
<table class="table table-hover table-striped" style="text-align: center">
    <thead>
        <tr>
            <th>ID</th>
            <th>MOTHER</th>
            <th>PHONE-MOTHER</th>
            <th>FATHER</th>
            <th>PHONE-DAD</th>
            <th>EDIT</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${families}" var="f">
            <tr>
                <td>${f.id}</td>
                <td>${f.motherName}</td>
                <td>${f.motherPhone}</td>
                <td>${f.fatherName}</td>
                <td>${f.fatherPhone}</td>
                <td>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/families/form?id=${f.id}">
                        edit
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="layout/footer.jsp" />