<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/transaction.css">
    <title>Transaction</title>
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
<div class="transactionBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <jsp:include page="userNavigation.jsp"/>
        <div class="transactionWindow">
            <form class="transactionForm"action="controller" method="post">
                <div class="transaction_box">
                    <c:choose>
                        <c:when test="${incorrectData.contains('sum')}">
                            <label class="transactionLabel"><fmt:message key="label.incorrectSum"/></label>
                        </c:when>
                        <c:when test="${successMessage.equals('success')}">
                            <label class="transactionLabel"><fmt:message key="label.transactionSuccess"/></label>
                        </c:when>
                        <c:otherwise>
                            <label class="transactionLabel"><fmt:message key="label.transaction"/></label>
                        </c:otherwise>
                    </c:choose>
                    <input class="transactionText" type="number" min="1" step="0.01" name="sum" required placeholder="<fmt:message key="helpMessage.enterSum"/>"/>
                </div>
                <div class="t_box">
                    <div class="transaction_box">
                        <c:choose>
                            <c:when test="${incorrectData.contains('creditCardNumber')}">
                                <label class="transactionLabel"><fmt:message key="label.incorrectCreditCard"/></label>
                            </c:when>
                            <c:otherwise>
                                <label class="transactionLabel"><fmt:message key="label.creditCardNumber"/></label>
                            </c:otherwise>
                        </c:choose>
                        <input class="transactionText" type="text" name="creditCardNumber" required placeholder="<fmt:message key="helpMessage.creditCardNumber"/>"/>
                    </div>
                    <div class="transaction_box">
                        <c:choose>
                            <c:when test="${incorrectData.contains('creditCardName')}">
                                <label class="transactionLabel"><fmt:message key="label.incorrectCreditCardName"/></label>
                            </c:when>
                            <c:otherwise>
                                <label class="transactionLabel"><fmt:message key="label.creditCardName"/></label>
                            </c:otherwise>
                        </c:choose>
                        <input class="transactionText"type="text" name="creditCardName" required placeholder="<fmt:message key="helpMessage.creditCardName"/>"/>
                    </div>
                    <div class="transaction_box">
                        <c:choose>
                            <c:when test="${incorrectData.contains('creditCardCode')}">
                                <label class="transactionLabel"><fmt:message key="label.incorrectCreditCardCode"/></label>
                            </c:when>
                            <c:otherwise>
                                <label class="transactionLabel"><fmt:message key="label.creditCardCode"/></label>
                            </c:otherwise>
                        </c:choose>
                        <input class="transactionText"type="text" name="creditCardCode" required placeholder="<fmt:message key="helpMessage.creditCardCode"/>"/>
                    </div>
                    <div class="transaction_box">
                        <input class="transactionButton" type="submit" name="transaction" value="<fmt:message key="button.transaction"/>"/>
                        <input type="hidden" name="command" value="TRANSACTION"/>
                        <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
