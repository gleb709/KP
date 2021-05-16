<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/settings.css">
    <title>SignIn</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty accountStatus}">
            <jsp:include page="header/guest.jsp"/>
        </c:when>
        <c:when test="${accountStatus.equals('user')}">
            <jsp:include page="header/user.jsp"/>
        </c:when>
    </c:choose>

    <div class="settings">
        <p class="settingsInfo"><fmt:message key="label.settings"/></p>
        <hr/>
        <div class="settingsList">
            <form class="settingsForm" action="controller" method="post">
                <div class="settingsButton">
                    <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
                    <input class="settingButton" type="submit" value="<fmt:message key="button.settings"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="SETTINGS"/>
                </div>
            </form>
            <form class="settingsForm" action="controller" method="post">
                <div class="settingsButton">
                    <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
                    <input class="settingButton" type="submit" value="<fmt:message key="button.settings"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="SETTINGS"/>
                </div>
            </form>
            <form class="settingsForm" action="controller" method="post">
                <div id="settingsButton" class="settingsButton">
                    <img class="settings_img" width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
                    <input id="settingButton" class="settingButton" type="submit" value="<fmt:message key="button.settings"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="SETTINGS"/>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
