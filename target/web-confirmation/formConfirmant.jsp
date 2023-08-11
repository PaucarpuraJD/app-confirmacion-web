<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h2 style="text-align: center">REGISTER OF CONFIRMANTS</h2>
<form action="${pageContext.request.contextPath}/confirmants/form" method="post">
    <div class="row mb-2">
        <label for="name" class="col-form-label col-sm-2">Confirmant: </label>
        <div class="col-sm-4">
            <input type="text" name="name" id="name" value="${confirmant.name}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.name}">
            <div style="color:red;">${errors.name}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="sacrament" class="col-form-label col-sm-2">Sacrament</label>
        <div class="col-sm-4">
            <select name="sacrament" id="sacrament" class="form-select" class="col-form-label col-sm-2">
                <option value="">--- seleccionar --- </option>
                <c:forEach items="${sacraments}" var="s">
                    <option value="${s.id}" ${s.id.equals(confirmant.sacrament.id) ? "selected" : ""}>${s.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row mb-2">
         <label for="family" class="col-form-label col-sm-2">Family</label>
         <div class="col-sm-4">
            <select name="family" id="family" class="form-select" class="col-form-label col-sm-2">
                <option value="">--- seleccionar ---</option>
                <c:forEach items="${families}" var="f">
                    <option value="${f.id}" ${f.id.equals(confirmant.family.id) ? "selected" : ""}>${f.motherName}</option>
                </c:forEach>
            </select>
         </div>
        <c:if test="${errors != null && not empty errors.family}">
            <div style="color:red;">${errors.family}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="address" class="col-form-label col-sm-2">Address: </label>
        <div class="col-sm-4">
            <input type="text" name="address" id="address" value="${confirmant.address}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.address}">
            <div style="color:red;">${errors.address}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="phone" class="col-form-label col-sm-2">Phone: </label>
        <div class="col-sm-4">
            <input type="text" name="phone" id="phone" value="${confirmant.phone}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.phone}">
            <div style="color:red;">${errors.phone}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="birthDate" class="col-form-label col-sm-2">BirthDate: </label>
        <div class="col-sm-4">
           <input class="form-control" type="date" name="birthDate" id="birthDate" value="${confirmant.birthDate !=null ? confirmant.birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errors != null && not empty errors.birthDate}">
            <div style="color:red;">${errors.birthDate}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="${confirmant.id != null && confirmant.id > 0 ? "Editar" : "Crear"}">
            <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/confirmants">Cancelar</a>
        </div>
    </div>
<input type="hidden" name="id" value="${confirmant.id}">
</form>
<jsp:include page="layout/footer.jsp" />