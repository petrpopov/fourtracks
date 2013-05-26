var map;
var username;
var requests = [];
var nextRequest = 0;
var routesRequestDelay = 500;


function initialize() {
    var haight = new google.maps.LatLng(37.7699298, -122.4469157);
    var mapOptions = {
        center: haight,
        zoom: 8,
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

    username = $.cookie('name');

    map = new google.maps.Map(document.getElementById("map_canvas"),
        mapOptions);

    developCheckins();
}

function developCheckins() {
    var api = 'api/checkins/' + username;
    $.get(api, function(data) {
        var points = getPointsFromCheckins(data);

        requests = getRequestsFromPoints(points);
        console.log(requests);
        placeMarkers(points);
      //  placeRoutes();
    });
}

function getPointsFromCheckins(checkins) {
    var points = [];

    for(i = 0; i < checkins.length; i++) {
        var venue = checkins[i].venue;
        if( venue == null )
            continue;

        var location = venue.location;
        if( location == null )
            continue;

        var spoint = new google.maps.LatLng(checkins[i].venue.location.latitude,
            checkins[i].venue.location.longitude);
        points.push(spoint);
    }
    return points;
}


function placeMarkers(points) {
    for(i = 0; i < points.length; i++) {
        placeMarker(points[i]);
    }
}

function placeMarker(point) {
    var markerImage = "resources/img/marker.png";

    //console.log("placing marker at position: " + point);
    var marker = new google.maps.Marker({
        map: map,
        position: point,
        icon: markerImage
    });
    marker.setMap(map);

    map.setCenter(point);
    map.setZoom(6);
}


function placeRoutes() {
    theNextRoute();
}

function theNextRoute() {
    if (nextRequest < requests.length) {
        setTimeout(requestRoute,routesRequestDelay, requests[nextRequest], theNextRoute);
        nextRequest++;
    } else {
        // We're done.
    }
}

function requestRoute(request, next) {
    var directionsService = new google.maps.DirectionsService();

    console.log(request);

    directionsService.route(request, function(result, status) {

        console.log("developing a result from routing service, status is: " + status);

        if (status == google.maps.DirectionsStatus.OK) {
            console.log("status number " +nextRequest+ " is ok - " + status);

            renderDirections(result);
            //placeLinesBetweenRouteAndMarkers(result, request);
        }
        else {
            if( status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT ) {
                nextRequest--;
                routesRequestDelay++;
                console.log("status number " +nextRequest+ " is not ok - " + status);
            } else {
                var reason="Code "+status;
                console.log(reason);
            }
        }

        next();
    });
}

function renderDirections(result) {
    var lineOptions = {
        strokeColor: '#3F9CFA',
        strokeOpacity: 0.5,
        strokeWeight: 4
    };
    var rendererOptions = {
        suppressMarkers:true,
        polylineOptions: lineOptions,
        draggable: false,
        suppressInfoWindows: true
    };

    var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setDirections(result);
}

function getRequestsFromPoints(points) {
    var requests = [];

    var max_waypoints = 8;
    var max_points = max_waypoints+2;


    var count = Math.floor(points.length / max_points);
    var rem = points.length % max_points;

    for(i = 0; i < count; i++) {
        var waypoints = [];

        for(j = 0; j < max_waypoints; j++) {
            var loc = points[i*max_points + j + 1];
            var w = {
                location: loc,
                stopover: true
            };
            waypoints.push(w);
        }

        var request = {
            origin: points[i*max_points],
            destination: points[i*max_points + max_waypoints + 1],
            travelMode: google.maps.TravelMode.DRIVING,
            waypoints: waypoints,
            optimizeWaypoints: false
        };

        requests.push(request);
    }

    return requests;
}



function placeLinesBetweenRouteAndMarkers(result, request) {
    var start = request.origin;
    var end = request.destination;
    var real_start = result.routes[0].legs[0].start_location;
    var real_end = result.routes[0].legs[0].end_location;

    if( real_start != start ) {
        placeLine(start, real_start);
    }

    if( real_end != end ) {
        placeLine(end, real_end);
    }
}



function placeLine(start, end) {
    var lineOptions = {
        strokeColor: '#3F9CFA',
        strokeOpacity: 0.5,
        strokeWeight: 4
    };

    var line = new google.maps.Polyline({
        map: map,
        options: lineOptions,
        path: [start, end]
    });
}

function createRouteReques(route) {
    var request = {
        origin: route.start,
        destination: route.end,
        travelMode: google.maps.TravelMode.DRIVING
    };

    return request;
}

function getRoutesFromCheckins(checkins) {

    var points = getPointsFromCheckins(checkins);
    var routes = [];
    for(i = 0; i < points.length-1; i++) {
        var route = {start: points[i], end: points[i+1]};
        routes.push(route);
    }

    return routes;
}



