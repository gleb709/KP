<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/guest6.css">

<html>
<body>
<div class="header">
    <div class="nav_logo">
        <div class="img_homePage">
            <form action="controller" method="post">
                <input class="submit_img" type="submit" name="home" value=""/>
                <input type="hidden" name="command" value="TRANSFER"/>
                <input type="hidden" name="page" value="HOME_PAGE"/>
            </form>
        </div>
        <div class="logo">InetCorp</div>
    </div>
    <div class="nav">
        <div class="navigation" id="summary"></div>
        <div id="detail" class="details">
            <form class="nav_form" action="controller" method="post">
                <div class="nav_box">
                    <img width="40" height="40" src="${pageContext.request.contextPath}/image/user6.jpg">
                    <input class="inputbutton" type="submit" value="<fmt:message key="button.signIn"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="SIGN_IN_PAGE"/>
                </div>
            </form>
            <form class="nav_form" action="controller" method="post">
                <div class="nav_box">
                    <img width="40" height="40" src="${pageContext.request.contextPath}/image/settings.jpg">
                    <input class="inputButton" type="submit" value="<fmt:message key="button.accountActivation"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="ACCOUNT_ACTIVATION"/>
                </div>
            </form>
            <form class="nav_form" action="controller" method="post">
                <div class="nav_box">
                    <img width="40" height="40" src="${pageContext.request.contextPath}/image/help.png"/>
                    <input class="inputButton" type="submit" value="<fmt:message key="button.help"/>"/>
                    <input type="hidden" name="command" value="TRANSFER"/>
                    <input type="hidden" name="page" value="HELP_PAGE"/>
                </div>
            </form>
            <form class="nav_form" action="controller" method="post">
                <div class="nav_box">
                        <img width="40" height="40" src="${pageContext.request.contextPath}/image/icons8_geography_50px_1.png"/>
                    <div class="inputButton">
                        <jsp:include page="locale.jsp"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<hr class="hr"/>
</body>
</html>



