<%--
  Created by IntelliJ IDEA.
  User: petrpopov
  Date: 12.02.13
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

    <title>Photo Tracker</title>

    <!-- Bootstrap -->
    <link href="<s:url value="/resources" />/css/bootstrap.min.css"
          rel="stylesheet" media="screen"
          type="text/css"/>

    <link href="<s:url value="/resources" />/css/style.css"
          rel="stylesheet"
          type="text/css"/>

    <!--meta name="viewport" content="width=device-width, initial-scale=1.0"-->
    <link href="<s:url value="/resources" />/css/bootstrap-responsive.min.css"
          rel="stylesheet"
          type="text/css"/>
</head>
<body>
    <!--script src="http://code.jquery.com/jquery-latest.js"></script-->
    <script src="<s:url value="/resources"/>/js/jquery.min.js"></script>
    <script src="<s:url value="/resources"/>/js/jquery.cookie.js"></script>
    <script src="<s:url value="/resources"/>/js/bootstrap.min.js"></script>
    <script src="<s:url value="/resources"/>/js/route.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
                'callback=initialize&key=AIzaSyAPyzaju1hxPGw9VhKl9iaW01OIB6q_rA0" type="text/javascript"></script>

    <t:insertAttribute name="header" />


    <div id="content">
        <t:insertAttribute name="content" />
    </div>

</body>
</html>





