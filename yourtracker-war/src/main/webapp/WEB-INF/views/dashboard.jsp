<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">

    var map;
    var chicago = new google.maps.LatLng(41.850033, -87.6500523);
    var marker;
    var image = '<s:url value="/resources" />/img/marker.png';

    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();

    function initialize() {
        var mapOptions = {
            center: new google.maps.LatLng(21.291982, -157.821856),
            zoom: 3,
            panControl: true,

            zoomControl: true,
            zoomControlOptions: {
                style: google.maps.ZoomControlStyle.DEFAULT
            },

            mapTypeControl: true,
            mapTypeControlOptions: {
                style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
            },

            scaleControl: true,
            streetViewControl: true,
            overviewMapControl: true,

            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map_canvas"),
                mapOptions);


        var json = '${checkins}';
        var arr = JSON.parse(json);

        var i = 0;
        var coordinates = [];
        for(i = 0; i < arr.checkins.length; i++) {
            var lat = arr.checkins[i].latitude;
            var lng = arr.checkins[i].longitude;

            coordinates.push(new google.maps.LatLng(lat, lng));
        }


    }

    function insertMarker(markerData) {

        var latitude = markerData.latitude;
        var longitude = markerData.longitude;
        var title = markerData.createdAt;

        var marker = createMarker(latitude, longitude, title);
        marker.setMap(map);

        map.setCenter(marker.getPosition());
        map.setZoom(4);
    }

    function createMarker(lat, lng, title) {
        var point = new google.maps.LatLng(lat, lng);
        var marker = new google.maps.Marker({
            position: point,
            animation: google.maps.Animation.DROP,
            title: title
        });

        google.maps.event.addDomListener(marker, 'click', function() {
            map.setCenter(marker.getPosition());
            map.setZoom(16);
        });

        return marker;
    }

    function toggleBounceMarker() {
        if (marker.getAnimation() != null) {
            marker.setAnimation(null);
        } else {
            marker.setAnimation(google.maps.Animation.BOUNCE);
        }
    }

    function loadScript() {
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
                'callback=initialize&key=AIzaSyAPyzaju1hxPGw9VhKl9iaW01OIB6q_rA0';
        document.body.appendChild(script);
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    //window.onload = loadScript;
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