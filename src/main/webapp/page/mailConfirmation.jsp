<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mailConfirmation.css">

<html>
<head>
    <title>Confirmation</title>
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
                    <br>
                    <c:if test="${not empty emailKey && emailKey.equals('invalid')}">
                        <label class="incorrectKey"><fmt:message key="label.incorrectKey"/></label>
                    </c:if>
                    <br/>
                    <input class="inputText" type="text" name="emailKey" placeholder="<fmt:message key="label.emailKey"/>"/>
                    <input class="confirmationButton" type="submit" value="<fmt:message key="button.confirmation"/>"/>
                    <input type="hidden" name="command" value="REGISTRATION_CONFIRMATION"/>
                </div>
                <div><img class="gears" width="250" height="250" src="${pageContext.request.contextPath}/image/gears1.png"></div>

            </div>
            </form>
    </div>
</body>
</html>
