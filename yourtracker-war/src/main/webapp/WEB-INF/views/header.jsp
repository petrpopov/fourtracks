<%--
  Created by IntelliJ IDEA.
  User: petrpopov
  Date: 12.02.13
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="navbar navbar-fixed-top navbar-inverse">
    <div class="navbar-inner">
        <div class="container">

            <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <!-- Be sure to leave the brand out there if you want it shown -->
            <a class="brand" href="<c:url value="/" />">fourtracks</a>

            <div class="pull-right">
                <ul class="nav">
                    <li class="active"><a href="<s:url value="/"/>">Main</a></li>
                    <li><a href="<s:url value="/dashboard"/>">Dashboard</a></li>
                </ul>

                <form class="navbar-form pull-left" action="<c:url value="/connect/foursquare" />" method="POST">
                    <a class="btn btn-inverse" onclick="$(this).closest('form').submit()">
                        <img src="<s:url value="/resources"/>/img/connect-black.png" />
                    </a>
                </form>

                <form class="navbar-form pull-left" action="<c:url value="/logout" />" method="POST">
                    <button type="submit">Disconnect</button>
                    <input type="hidden" name="_method" value="delete" />
                </form>
            </div>

            <!-- Everything you want hidden at 940px or less, place within here -->
            <div class="nav-collapse collapse">
                <!-- .nav, .navbar-search, .navbar-form, etc -->
            </div>

        </div>
    </div>
</div>