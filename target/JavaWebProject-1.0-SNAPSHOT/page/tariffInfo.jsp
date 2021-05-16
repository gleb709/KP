<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/tariffInfo.css">
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
        <div class="infoWindow">
            <div class="infoPart"><fmt:message key="label.tariff"/><p/></div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariff"/></label>
                <label class="infoLabel">${tariffData.getName()}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffPrice"/></label>
                <label class="infoLabel">${tariffData.getPrice()}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffDiscount"/></label>
                <label class="infoLabel">${tariffData.getDiscount()}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffInfo"/></label>
                <textarea rows="3" class="textArea" readonly>${tariffData.getInfo()}</textarea></p>
            </div>
            <p/>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffImage"/></label>
                <img src="${pageContext.request.contextPath}/image/${tariffData.getImage()}" width="100" height="100"/>
            </div>
            <c:choose>
                <c:when test="${incorrectData.contains('userCount')}">
                    <label class="infoLabel"><fmt:message key="label.deleteTariffError"/></label>
                </c:when>
                <c:when test="${incorrectData.contains('tariff')}">
                    <label class="infoLabel"><fmt:message key="label.tariffDeleted"/></label>
                </c:when>
                <c:when test="${successMessage.equals('success')}">
                    <label class="infoLabel"><fmt:message key="label.deleteSuccess"/></label>
                </c:when>
                <c:otherwise>
                    <p/>
                    <label class="infoLabel"></label>
                </c:otherwise>
            </c:choose>
            <div class="blockBox_1">
                <form class="blockForm" action="controller" method="post">
                    <input class="blockButton" type="submit" value="<fmt:message key="button.deleteTariff"/>"/>
                    <input type="hidden" name="tariff" value="${tariffData.getName()}"/>
                    <input type="hidden" name="command" value="DELETE_TARIFF"/>
                </form>
                <form class="blockForm" action="controller" method="post">
                    <input class="blockButton" type="submit" name="blockAccount" value="<fmt:message key="button.updateTariff"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="CHANGE_TARIFF_INFO">
                    <input type="hidden" name="tariff" value="${tariffData.getName()}"/>
                </form>
            </div>
            <div class="blockBox_2">
                <form class="blockForm" action="controller" method="post">
                    <input class="blockButton" type="submit" name="transfer" value="<fmt:message key="button.back"/>"/>
                    <input type="hidden" name="command" value="TARIFF_LIST"/>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
