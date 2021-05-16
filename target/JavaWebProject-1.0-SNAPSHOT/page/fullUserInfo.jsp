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
        <div class="navigationList">
            <form class="navigationForm" action="controller" method="post">
                <div class="navigationButton">
                    <img class="nav_img" width="40" height="40" src="${pageContext.request.contextPath}/image/user6.jpg">
                    <input class="navButton" type="submit" value="<fmt:message key="button.userInfo"/>"/>
                    <input type="hidden" name="command" value="ADMIN_INFO"/>>
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
                <c:if test="${!payment.equals('invalid')}">
                    <label class="infoLabel">${tariff}</label>
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
