<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<html>
<head>
    <link rel="stylesheet" href="css/signUp.css">
    <title>SignUp</title>
</head>
<body>
    <jsp:include page="header/locale.jsp"/>
    <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
    <div class="signUpBox">
        <form action="controller" method="post">
            <label><fmt:message key="label.login"/></label>
            <c:if test="${incorrectData.contains('login')}">
                <label><fmt:message key="label.incorrectLogin"/> </label>
            </c:if>
            <input type="text" name="login" placeholder="<fmt:message key="helpMessage.enterLogin"/>"/>
            <label><fmt:message key="label.password"/></label>
            <c:if test="${incorrectData.contains('password')}">
                <label><fmt:message key="label.incorrectPassword"/> </label>
            </c:if>
            <input type="password" name="password" placeholder="<fmt:message key="helpMessage.enterPassword"/>"/>
            <label><fmt:message key="label.repeatPassword"/></label>
            <c:if test="${incorrectData.contains('repeatPassword')}">
                <label><fmt:message key="label.incorrectRepeatPassword"/></label>
            </c:if>
            <input type="password" name="repeatPassword"  placeholder="<fmt:message key="helpMessage.repeatPassword"/>"/>
            <label><fmt:message key="label.personalInformation"/></label>
            <input type="text" name="firstName" placeholder="<fmt:message key="helpMessage.firstName"/>">
            <c:if test="${incorrectData.contains('firstName')}">
                <fmt:message key="label.incorrectFirstName"/>
            </c:if>
            <input type="text" name="lastName" placeholder="<fmt:message key="helpMessage.lastName"/>"/>
            <c:if test="${incorrectData.contains('lastName')}">
                <fmt:message key="label.incorrectLastName"/>
            </c:if>
            <input type="text" name="creditCardNumber" placeholder="<fmt:message key="helpMessage.creditCardNumber"/>"/>
            <c:if test="${incorrectData.contains('creditCard')}">
                <fmt:message key="label.incorrectCreditCard"/>
            </c:if>
            <input type="text" name="email" placeholder="<fmt:message key="helpMessage.Email"/>"/>
            <c:if test="${incorrectData.contains('email')}">
                <fmt:message key="label.incorrectEmail"/>
            </c:if>
            <input type="submit" name="SignUp" value="<fmt:message key="button.signUp"/>"/>
            <input type="hidden" name="command" value="SIGN_UP">
            <input type="hidden" name="postTime" value="${postTime}">
        </form>
    </div>
</body>
</html>
