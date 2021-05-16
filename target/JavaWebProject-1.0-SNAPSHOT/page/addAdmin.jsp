<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/signUp5.css">

<html>
<head>
    <title>SignUp</title>
</head>
<body>
<c:choose>
    <c:when test="${accountRole.equals('mainAdmin')}">
        <jsp:include page="header/mainAdmin.jsp"/>
    </c:when>
    <c:when test="${accountRole.equals('admin')}">
        <jsp:include page="header/admin.jsp"/>
    </c:when>
</c:choose>

<c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
<form action="controller" method="post">
    <div class="signUpBox">
        <div class="signUpWindow">
            <div class="box">
                <c:if test="${incorrectData.contains('login')}">
                    <label class="incorrectKey"><fmt:message key="label.incorrectLogin"/> </label>
                </c:if>
                <c:if test="${!incorrectData.contains('login')}">
                    <label class="incorrectKey"><fmt:message key="label.login"/></label>
                </c:if>
                <input class="inputText" type="text" name="login" placeholder="<fmt:message key="helpMessage.enterLogin"/>"/>
                <c:if test="${incorrectData.contains('password')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.incorrectPassword"/> </label>
                </c:if>
                <c:if test="${!incorrectData.contains('password')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.password"/> </label>
                </c:if>
                <input class="inputText" type="password" name="password" placeholder="<fmt:message key="helpMessage.enterPassword"/>"/>
                <c:if test="${incorrectData.contains('repeatPassword')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.incorrectRepeatPassword"/></label>
                </c:if>
                <c:if test="${!incorrectData.contains('repeatPassword')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.repeatPassword"/></label>
                </c:if>
                <input class="inputText" type="password" name="repeatPassword"  placeholder="<fmt:message key="helpMessage.repeatPassword"/>"/>
                <c:if test="${incorrectData.contains('email')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.incorrectEmail"/></label>
                </c:if>
                <c:if test="${!incorrectData.contains('email')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.email"/></label>
                </c:if>
                <input class="inputText" type="text" name="email" placeholder="<fmt:message key="helpMessage.Email"/> "/>
                <p>
                    <input class="signUpButton" type="submit" name="SignUp" value="<fmt:message key="button.signUp"/>"/>
                    <input type="hidden" name="command" value="SIGN_UP">
                    <input type="hidden" name="role" value="admin"/>
                    <input type="hidden" name="postTime" value="${postTime}">
                </p>
            </div>
            <div class="box">
                <c:if test="${incorrectData.contains('firstName')}">
                    <label class="incorrectKey">
                        <fmt:message key="label.incorrectFirstName"/>
                    </label>
                </c:if>
                <c:if test="${!incorrectData.contains('firstName')}">
                    <label class="incorrectKey"><fmt:message key="label.firstName"/></label>
                </c:if>
                <input class="inputText" type="text" name="firstName" placeholder="<fmt:message key="helpMessage.firstName"/> ">
                <c:if test="${incorrectData.contains('lastName')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.incorrectLastName"/></label>
                </c:if>
                <c:if test="${!incorrectData.contains('lastName')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.lastName"/></label>
                </c:if>
                <input class="inputText" type="text" name="lastName" placeholder="<fmt:message key="helpMessage.lastName"/> "/>
                <c:if test="${incorrectData.contains('phoneNumber')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.incorrectPhoneNumber"/></label>
                </c:if>
                <c:if test="${!incorrectData.contains('phoneNumber')}">
                    <p/>
                    <label class="incorrectKey"><fmt:message key="label.phoneNumber"/></label>
                </c:if>
                <input class="inputText" type="text" name="phoneNumber" placeholder="<fmt:message key="helpMessage.phoneNumber"/> "/>
            </div>
        </div>
    </div>
</form>
</body>
</html>
