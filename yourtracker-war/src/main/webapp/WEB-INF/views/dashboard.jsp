<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
    google.maps.event.addDomListener(window, 'load', initialize);
</script>

<div class="container-fluid">

    <img id="marker_img" src=""/>

    <div class="row-fluid">
        <div class="span9 ">

            <h2>Maps content</h2>

            <div id="map_canvas" class="map_canvas"></div>
        </div>

        <div class="span3">

            <h2>Profile content</h2>
        </div>
    </div>
</div>