<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<body>
<link rel="stylesheet" href="css/adminInfo1.css">
<div class="navigationList">
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/user6.jpg">
            <input class="navButton" type="submit" value="<fmt:message key="button.userInfo"/>"/>
            <input type="hidden" name="command" value="ADMIN_INFO"/>
        </div>
    </form>
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/router.png">
            <input class="navButton" type="submit" value="<fmt:message key="button.tariffList"/>"/>
            <input type="hidden" name="command" value="TARIFF_LIST"/>
        </div>
    </form>
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/userList.jpg">
            <input class="navButton" type="submit" value="<fmt:message key="button.users"/>"/>
            <input type="hidden" name="command" value="USER_LIST"/>
        </div>
    </form>
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/addAdmin.jpg">
            <input class="navButton" type="submit" value="<fmt:message key="button.admins"/>"/>
            <input type="hidden" name="command" value="ADMIN_LIST"/>
        </div>
    </form>
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/transaction.jpg">
            <input class="navButton" type="submit" value="<fmt:message key="button.getPayment"/>"/>
            <input type="hidden" name="command" value="SUBSCRIPTION_FEE"/>
        </div>
    </form>
    <form class="navigationForm" action="controller" method="post">
        <div class="navigationButton">
            <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
            <input class="navButton" type="submit" value="<fmt:message key="button.changePassword"/>"/>
            <input type="hidden" name="command" value="TRANSFER"/>
            <input type="hidden" name="page" value="CHANGE_PASSWORD"/>
        </div>
    </form>
</div>
</body>
</html>
