<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/userInfo.css">
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
    <c:when test="${accountRole.equals('user')}">
        <jsp:include page="header/user.jsp"/>
    </c:when>
</c:choose>

<div class="userInfoBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <jsp:include page="userNavigation.jsp"/>
        <div class="infoWindow">
            <div class="infoPart"><fmt:message key="label.personalInformation"/><p/></div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.login"/></label>
                    <label class="infoLabel">${login}</label>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.contractNumber"/></label>
                    <label class="infoLabel">${contractNumber}</label>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.firstName"/></label>
                    <label class="infoLabel">${firstName}</label>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.lastName"/></label>
                    <label class="infoLabel">${lastName}</label>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.phoneNumber"/></label>
                    <label class="infoLabel">${phoneNumber}</label>
                </div>
                <div class ="infoBox">
                    <label class="infoLabel"><fmt:message key="label.email"/></label>
                    <label class="infoLabel">${email}</label>
                </div>
            <p/>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.balance"/></label>
                <c:choose>
                    <c:when test="${balance.equals('invalid')}">
                        <label class="infoLabel">0</label>
                    </c:when>
                    <c:otherwise>
                        <label class="infoLabel">${balance}</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariff"/></label>
                <c:choose>
                    <c:when test="${tariff.equals('invalid')}">
                        <label class="infoLabel"><fmt:message key="label.noTariff"/></label>
                    </c:when>
                    <c:otherwise>
                        <label class="infoLabel">${tariff}</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.payment"/></label>
                <c:choose>
                    <c:when test="${payment.equals('invalid')}">
                        <label class="infoLabel"><fmt:message key="label.debtor"/></label>
                    </c:when>
                    <c:otherwise>
                        <label class="infoLabel">${payment}</label>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    </div>
</div>

</body>
</html>
