<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/userTariffInfo.css">
    <title>User menu</title>
</head>
<body>
<jsp:include page="header/user.jsp"/>
<div class="userInfoBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <jsp:include page="userNavigation.jsp"/>
        <div class="infoWindow">
            <div class="infoPart"><fmt:message key="label.tariff"/><p/></div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariff"/></label>
                <label class="infoLabel">${tariff}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffPrice"/></label>
                <label class="infoLabel">${price}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffDiscount"/></label>
                <label class="infoLabel">${discount}</label>
            </div>
            <div class ="infoBox">
                <label class="infoLabel"><fmt:message key="label.tariffInfo"/></label>
                <textarea rows="3" class="textArea" readonly>${tariffInfo}</textarea></p>
            </div>
            <p/>
        </div>
    </div>
</div>
</div>

</body>
</html>
