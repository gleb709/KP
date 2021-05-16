<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/subscriptionFee.css">
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
            <div class="infoPart"><fmt:message key="label.SubscriptionFee"/><p/></div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.lastSubscriptionFee"/></label>
                <label class="infoLabel">${lastPayment}</label>
            </div>
            <form action="controller" method="post">
                <input class="subscriptionButton" type="submit" value="<fmt:message key="button.collectSubscriptionFee"/>"/>
                <input type="hidden" name="command" value="COLLECT_SUBSCRIPTION_FEE">
            </form>
            <c:if test="${success != true && not empty success}">
                <label class="infoLabel"><fmt:message key="label.subscriptionFeeError"/></label>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
