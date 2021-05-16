<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/changePassword2.css">
    <title>Change password</title>
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
<div class="changePasswordBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <c:choose>
            <c:when test="${accountRole.equals('admin')}">
                <jsp:include page="navigation.jsp"/>
            </c:when>
            <c:when test="${accountRole.equals('mainAdmin')}">
                <jsp:include page="navigation.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="userNavigation.jsp"/>
            </c:otherwise>
        </c:choose>
        <div class="changePasswordWindow">
            <form class="changePassForm"action="controller" method="post">
                <div class="passwordBox">
                    <c:choose>
                        <c:when test="${incorrectData.contains('password')}">
                            <label class="passLabel"><fmt:message key="label.wrongPassword"/></label>
                        </c:when>
                        <c:when test="${successMessage.equals('success')}">
                            <label class="passLabel"><fmt:message key="label.passwordIsChanged"/></label>
                        </c:when>
                        <c:otherwise>
                            <label class="passLabel"><fmt:message key="label.password"/></label>
                        </c:otherwise>
                    </c:choose>
                    <input class="changePassText" type="password" name="password" placeholder="<fmt:message key="helpMessage.enterPassword"/>"/>
                </div>
                <div class="passBox">
                    <div class="passwordBox">
                        <c:choose>
                            <c:when test="${incorrectData.contains('newPassword')}">
                                <label class="passLabel"><fmt:message key="label.incorrectNewPassword"/></label>
                            </c:when>
                            <c:when test="${incorrectData.contains('theSame')}">
                                <label class="passLabel"><fmt:message key="label.samePasswords"/></label>
                            </c:when>
                            <c:otherwise>
                                <label class="passLabel"><fmt:message key="label.newPassword"/></label>
                            </c:otherwise>
                        </c:choose>
                        <input class="changePassText" type="password" name="newPassword" placeholder="<fmt:message key="helpMessage.enterNewPassword"/>"/>
                    </div>
                    <div class="passwordBox">
                        <c:choose>
                            <c:when test="${incorrectData.contains('repeatPassword') && !incorrectData.contains('newPassword')}">
                                <label class="passLabel"><fmt:message key="label.incorrectRepeatPassword"/></label>
                            </c:when>
                            <c:otherwise>
                                <label class="passLabel"><fmt:message key="label.repeatPassword"/></label>
                            </c:otherwise>
                        </c:choose>
                        <input class="changePassText"type="password" name="repeatPassword" placeholder="<fmt:message key="helpMessage.repeatNewPassword"/>"/>
                    </div>
                    <div class="passwordBox">
                        <input class="changePassButton" type="submit" name="changePassword" value="<fmt:message key="button.changePassword"/>"/>
                        <input type="hidden" name="command" value="CHANGE_PASSWORD"/>
                        <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
