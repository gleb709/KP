<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountActivation1.css">

<html>
<head>
    <title>Password recovery</title>
</head>
<body>
<jsp:include page="header/guest.jsp"/>
<div class="block"></div>
<div class="confirmationBox">
    <form class="confirmationForm" action="controller" method="post">
        <p class="confirmationInfo"><fmt:message key="label.confirmationInfo"/></p>
        <hr/>
        <div class="box_gears">
            <div class="box">
                <c:choose>
                    <c:when test="${incorrectData.contains('login')}">
                        <label class="incorrectKey"><fmt:message key="label.incorrectLogin"/> </label>
                    </c:when>
                    <c:when test="${incorrectData.contains('isActive')}">
                        <label class="incorrectKey"><fmt:message key="label.accountIsActive"/> </label>
                    </c:when>
                    <c:otherwise>
                        <label class="incorrectKey"><fmt:message key="label.login"/> </label>
                    </c:otherwise>
                </c:choose>
                </br>
                <input class="inputText" type="text" name="login" placeholder="<fmt:message key="helpMessage.enterLogin"/>"/>
                <p/>
                <c:choose>
                    <c:when test="${incorrectData.contains('key')}">
                        <label class="incorrectKey"><fmt:message key="label.activationFail"/></label>
                    </c:when>
                    <c:otherwise>
                        <label class="incorrectKey"><fmt:message key="label.key"/> </label>
                    </c:otherwise>
                </c:choose>
                </br>
                <input class="inputText" type="text" name="emailKey" placeholder="<fmt:message key="label.emailKey"/>"/>
                <p>
                    <label class="label"/>
                </p>
                <input class="confirmationButton" type="submit" value="<fmt:message key="button.confirmation"/>"/>
                <input type="hidden" name="command" value="ACCOUNT_ACTIVATION"/>
            </div>
            <div><img class="gears" width="250" height="250" src="${pageContext.request.contextPath}/image/gears1.png"></div>
        </div>
    </form>
</div>
</body>
</html>
