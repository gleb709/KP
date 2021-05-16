<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/changePassword.css">

<html>
<head>
    <title>Change Password</title>
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
<div class="confirmationBox">
    <div class="box">
        <form class="changePasswordForm" action="controller" method="post">
            <c:if test="${incorrectData.contains('password')}">
                <label class="label"><fmt:message key="label.incorrectPassword"/> </label>
            </c:if>
            <c:if test="${!incorrectData.contains('password')}">
                <label class="label"><fmt:message key="label.password"/> </label>
            </c:if>
            <input class="inputText" type="password" name="password" placeholder= "<fmt:message key="helpMessage.enterPassword"/>"/>
            <p/>
            <c:if test="${incorrectData.contains('repeatPassword')}">
                <label class="label"><fmt:message key="label.incorrectRepeatPassword"/> </label>
            </c:if>
            <c:if test="${!incorrectData.contains('repeatPassword')}">
                <label class="label"><fmt:message key="label.repeatPassword"/> </label>
            </c:if>
            <input class="inputText" type="password" name="repeatPassword" placeholder="<fmt:message key="helpMessage.repeatPassword"/>"/>
            <p/>
            <label class="label"></label>
            <p/>
            <input class="changePasswordButton" type="submit" name="SignIn" value="<fmt:message key="button.changePassword"/>"/>
            <input type="hidden" name="command" value="CHANGE_FORGOT_PASSWORD">
            <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
        </form>
    </div>
    <div class="box">
        <img class="img_gears" src="${pageContext.request.contextPath}/image/gears1.png"/>
    </div>
</div>
</body>
</html>
