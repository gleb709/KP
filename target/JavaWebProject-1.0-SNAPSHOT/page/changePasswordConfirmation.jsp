<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgotPassword2.css">

<html>
<head>
    <title>Password recovery</title>
</head>
<body>
<jsp:include page="header/guest.jsp"/>
<div class="block"></div>
<div class="confirmationBox">
<form class="confirmationForm" action="controller" method="post">
    <p class="confirmationInfo"><fmt:message key="label.forgotPasswordConfirmation"/></p>
    <hr/>
    <div class="box_gears">
        <div class="box">
            <br>
            <c:if test="${emailKey.equals('invalid')}">
                <label class="incorrectKey"><fmt:message key="label.incorrectKey"/></label>
            </c:if>
            <c:if test="${!emailKey.contains('invalid')}">
                <label class="incorrectKey"><fmt:message key="label.key"/> </label>
            </c:if>
            </br>
            <input class="inputText" type="text" name="emailKey" placeholder="<fmt:message key="label.emailKey"/>"/>
            <p>
                <label class="label"/>
            </p>
            <input class="confirmationButton" type="submit" value="<fmt:message key="button.confirmation"/>"/>
            <input type="hidden" name="command" value="CHANGE_PASSWORD_CONFIRMATION"/>
        </div>
        <div><img class="gears" width="250" height="250" src="${pageContext.request.contextPath}/image/gears1.png"></div>
    </div>
</form>
</div>
</body>
</html>
