<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" uri="userList" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/addTariff4.css">
    <title>User menu</title>
</head>
<body>
<c:choose>
    <c:when test="${accountRole.equals('mainAdmin')}">
        <jsp:include page="header/mainAdmin.jsp"/>
    </c:when>
    <c:when test="${accountRole.equals('admin')}">
        <jsp:include page="header/admin.jsp"/>
    </c:when>
</c:choose>
<div class="userInfoBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <jsp:include page="navigation.jsp"/>
        <div class="addTariffWindow">
            <div class="tariffLabel">
                <c:choose>
                    <c:when test="${successMessage == true}">
                        <label class="addTariffLabel"><fmt:message key="label.addTariffSuccess"/></label>
                    </c:when>
                    <c:otherwise>
                        <label class="addTariffLabel"><fmt:message key="label.addTariff"/></label>
                    </c:otherwise>
                </c:choose>
            </div>
            <form class="addTariffForm"action="controller" method="post">
                <div class="box">
                    <label class="infoLabel"><fmt:message key="label.tariff"/></label>
                    <c:choose>
                        <c:when test="${incorrectData.contains('tariff')}">
                            <input class="tariffText" type="text" name="tariff" required pattern="[A-Za-z0-9()?/$%#№^&*+'-]{3,45}" placeholder="<fmt:message key="label.incorrectTariff"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input class="tariffText" type="text" name="tariff" placeholder="<fmt:message key="label.enterTariff"/>"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="box">
                    <label class="infoLabel"><fmt:message key="label.tariffPrice"/></label>
                    <c:choose>
                        <c:when test="${incorrectData.contains('price')}">
                            <input class="tariffText" type="number" name="price" pattern="[0-9]{1,10}" step="0.01" required placeholder="<fmt:message key="label.incorrectTariffPrice"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input class="tariffText" type="number" name="price" pattern="[0-9]{1,10}" step="0.01" required placeholder="<fmt:message key="label.enterTariffPrice"/>"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="box">
                    <label class="infoLabel"><fmt:message key="label.tariffDiscount"/></label>
                    <c:choose>
                        <c:when test="${incorrectData.contains('discount')}">
                            <input class="tariffText" type="number" name="discount" pattern="[0-9]{1,3}" max="100" min="0" required placeholder="<fmt:message key="label.incorrectTariffDiscount"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input class="tariffText" type="number" name="discount" pattern="[0-9]{1,3}" max="100" min="0" required placeholder="<fmt:message key="label.enterTariffDiscount"/>"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="box">
                    <label class="infoLabel"><fmt:message key="label.tariffImage"/></label>
                    <c:choose>
                        <c:when test="${empty image}">
                            <input class="tariffText" type="text" name="image" pattern="[^*<>\\\\/{|}]+ {1,500}" required readonly placeholder="<fmt:message key="label.noImage"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input class="tariffText" type="text" name="image" pattern="[^*<>\\\\/{|}]+ {2,500}" required readonly value="${image}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.tariffInfo"/></label>
                    <c:choose>
                        <c:when test="${incorrectData.contains('tariffInfo')}">
                            <textarea rows="3" class="textArea" name="tariffInfo" placeholder="<fmt:message key="label.incorrectTariffInfo"/>"></textarea>
                        </c:when>
                        <c:otherwise>
                            <textarea rows="3" class="textArea" name="tariffInfo" placeholder="<fmt:message key="label.enterTariffInfo"/>"></textarea>
                        </c:otherwise>
                    </c:choose>
                    </p>
                </div>
                <input class="addTariffButton" type="submit" value="<fmt:message key="label.addTariff"/>">
                <input type="hidden" name="command" value="ADD_TARIFF"/>
                <input type="hidden" name="tariffInfo" value="${tariffInfo}">
                <input type="hidden" name="image" value="${image}">
            </form>
            <div class="uploadBox">
                <form class="uploadForm" action="${pageContext.request.contextPath}/upload_controller" enctype="multipart/form-data" method="POST">
                    <div class="upload">
                        <label class="file_add">
                            <input class="imageButton" type="file" name="image" accept="image/*" height="130">
                            <span class="input_file"><fmt:message key="label.addFile"/></span>
                        </label>
                        <input class="uploadButton" type="submit" value=<fmt:message key="button.uploadImage"/>>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>

