<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/tariffPayment2.css">
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
            <div class="infoPart"><fmt:message key="label.SubscriptionFee"/><p/></div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariff"/></label>
                <label class="infoLabel">${tariff}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.balance"/></label>
                <label class="infoLabel">${balance}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffPrice"/></label>
                <label class="infoLabel">${price}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffDiscount"/></label>
                <label class="infoLabel">${discount}%</label>
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
            <form class="paymentForm" action="controller" method="post">
                <input class="paymentButton" type="submit" value="<fmt:message key="button.payment"/>"/>
                <input type="number" class="paymentLabel" name="dayCount" required min="1" step="1" placeholder="<fmt:message key="helpMessage.enterCount"/>">
                <input type="hidden" name="command" value="PAYMENT">
            </form>
            <c:if test="${success != true && not empty success}">
                <label class="infoLabel"><fmt:message key="label.subscriptionFeeError"/></label>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
