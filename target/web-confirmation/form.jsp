<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">REGISTER OF SACRAMENTS</h2>
<form action="${pageContext.request.contextPath}/sacraments/form" method="post">
    <div class="row mb-2">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4">
            <input type="text" name="name" id="name" value="${sacrament.name}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.name}">
            <div style="color:red;">${errors.name}</div>
        </c:if>
    </div>
<div class="row mb-2">
    <div>
        <input class="btn btn-primary my-2" type="submit" value="${sacrament.id != null && sacrament.id > 0 ? "Edit" : "Create"}">
        <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/sacraments">Cancel</a>
    </div>
</div>
<input type="hidden" name="id" value="${sacrament.id}">
</form>
<jsp:include page="layout/footer.jsp" />