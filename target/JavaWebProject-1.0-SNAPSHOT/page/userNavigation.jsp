<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<body>
<link rel="stylesheet" href="css/userMenu1.css">
<div class="settingsList">
    <form class="settingsForm" action="controller" method="post">
        <div class="settingsButton">
            <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/user6.jpg">
            <input class="settingButton" type="submit" value="<fmt:message key="button.userInfo"/>"/>
            <input type="hidden" name="command" value="USER_INFO"/>
        </div>
    </form>
    <form class="settingsForm" action="controller" method="post">
        <div  class="settingsButton">
            <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/payment.jpg">
            <input class="settingButton" type="submit" value="<fmt:message key="button.paymentForServices"/>"/>
            <input type="hidden" name="command" value="TARIFF_PAYMENT"/>
        </div>
    </form>
    <form class="settingsForm" action="controller" method="post">
        <div class="settingsButton">
            <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/transaction.jpg">
            <input class="settingButton" type="submit" value="<fmt:message key="button.transaction"/>"/>
            <input type="hidden" name="command" value="TRANSFER"/>
            <input type="hidden" name="page" value="TRANSACTION"/>
        </div>
    </form>
    <form class="settingsForm" action="controller" method="post">
        <div class="settingsButton">
            <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/router.png">
            <input class="settingButton" type="submit" value="<fmt:message key="button.tariffInfo"/>"/>
            <input type="hidden" name="command" value="USER_TARIFF_INFO"/>
        </div>
    </form>
    <form class="settingsForm" action="controller" method="post">
        <div class="settingsButton">
            <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
            <input class="settingButton" type="submit" value="<fmt:message key="button.changePassword"/>"/>
            <input type="hidden" name="command" value="TRANSFER"/>
            <input type="hidden" name="page" value="CHANGE_PASSWORD"/>
        </div>
    </form>
</div>
</body>
</html>
