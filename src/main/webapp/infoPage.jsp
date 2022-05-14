<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>

    </div>
    <!-- /.container-fluid -->
</div>


<div class="container-fluid">

    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary"> Info Page</h6>
    </div>
    <!-- DataTales Example -->

    <div class="text-center">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">${info}</h1>

    </div>
    </div>

</div>

<form action="${backLink}?userId=${id}" method="get">
<%--<form action="${backLink}" method="get">--%>
    <input type="hidden" name="userId" value="${id}"/>

    <div class="text-center">
        <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit" name="back"
                value="yes">Back</button>
    </div>
</form>

</form>


<%--<div class="text-center">--%>
<%--    <a href="${backLink}?userId=${id}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">--%>
<%--        <i class="fas fa-download fa-sm text-white-50"></i>Back</a>--%>
<%--</div>--%>


</div>
<!-- End of Main Content -->

<%@ include file="/WEB-INF/jspf/footer.jspf" %>