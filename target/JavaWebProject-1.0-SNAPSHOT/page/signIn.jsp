<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/signIn.css">
    <title>SignIn</title>
</head>
<body>
    <jsp:include page="header/guest.jsp"/>
<div class="signInBox">
    <div class="signInWindow">
        <hr/>
        <div class="box">
            <form action="controller" method="post">
                <label class="label"><fmt:message key="label.login"/></label>
                <input class="inputText" type="text" name="login" placeholder= "<fmt:message key="helpMessage.enterLogin"/>"/>
                <p/>
                <label class="label"><fmt:message key="label.password"/></label>
                <input class="inputText" type="password" name="password" placeholder="<fmt:message key="helpMessage.enterPassword"/>"/>
                <c:choose>
                    <c:when test="${incorrectData.contains('login') || incorrectData.contains('password')}">
                        <p>
                            <label class="label"><fmt:message key="label.signInFail"/> </label>
                        </p>
                    </c:when>
                    <c:when test="${incorrectData.contains('status')}">
                        <p>
                            <label class="label"><fmt:message key="label.accountIsBlocked"/></label>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p/>
                        <label class="label"></label>
                        <p/>
                    </c:otherwise>
                </c:choose>

                <c:if test="${empty incorrectData}">

                </c:if>
                <input class="signInButton" type="submit" name="SignIn" value="<fmt:message key="button.signIn"/>"/>
                <input type="hidden" name="command" value="SIGN_IN">
                <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
            </form>
            <form action="controller" method="post">
                <input class="forgotPassword" type="submit" name="ForgotPassword" value="<fmt:message key="button.forgotPassword"/>"/>
                <input type="hidden" name="command" value="TRANSFER"/>
                <input type="hidden" name="page" value="FORGOT_PASSWORD"/>
                <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
            </form>
        </div>
        <div class="box">
            <img class="img_user" width="100" height="100" src="${pageContext.request.contextPath}/image/signIn.jpg">
        </div>
    </div>
</div>
</body>
</html>
