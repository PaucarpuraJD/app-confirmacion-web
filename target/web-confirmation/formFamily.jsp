<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">REGISTER OF FAMILIES</h2>
<form action="${pageContext.request.contextPath}/families/form" method="post">
    <div class="row mb-2">
        <label for="motherName" class="col-form-label col-sm-2">Mother Name: </label>
        <div class="col-sm-4">
            <input type="text" name="motherName" id="motherName" value="${family.motherName}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.motherName}">
            <div style="color:red;">${errors.motherName}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="motherPhone" class="col-form-label col-sm-2">Mother Phone: </label>
        <div class="col-sm-4">
            <input type="text" name="motherPhone" id="motherPhone" value="${family.motherPhone}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.motherPhone}">
            <div style="color:red;">${errors.motherPhone}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="fatherName" class="col-form-label col-sm-2">Father Name: </label>
        <div class="col-sm-4">
            <input type="text" name="fatherName" id="fatherName" value="${family.fatherName}" class="form-control">
        </div>
    </div>
    <div class="row mb-2">
        <label for="fatherPhone" class="col-form-label col-sm-2">Father Phone: </label>
        <div class="col-sm-4">
            <input type="text" name="fatherPhone" id="fatherPhone" value="${family.fatherPhone}" class="form-control">
        </div>
    </div>
<div class="row mb-2">
    <div>
        <input class="btn btn-primary my-2" type="submit" value="${family.id != null && family.id > 0 ? "Edit" : "Create"}">
        <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/families">Cancel</a>
    </div>
</div>
<input type="hidden" name="id" value="${family.id}">
</form>
<jsp:include page="layout/footer.jsp" />