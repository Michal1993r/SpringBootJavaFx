<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/flightindicators.css" />
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJWOKUyatUHukA-qslSXXC_RuSvsuKgqI&callback=initMap">
    </script>
    <script src="js/jquery.flightindicator.js"></script>
    <style>
        #map {
            height: 400px;
            width: 50%;
        }
    </style>
</head>
<body>
    <span id="attitude"></span>
    <div id="map"></div>
</body>

</html>

<script>
    var marker;
    var map;
    var indicator = $.flightIndicator('#attitude', 'attitude');
    setInterval(function () {
        $.get("gyroData", function (response) {
            indicator.setRoll(response[0]);
            indicator.setPitch(response[1]);
//            var newPosition = new google.maps.LatLng(50.978500, 20.547611);
//            marker.setPosition(newPosition);
//            map.panTo(newPosition);
        });
    }, 100);
    function initMap() {
        var zeroPosition = new google.maps.LatLng(0, 0);
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15, //0-18
            center: zeroPosition
        });
        marker = new google.maps.Marker({
            position: zeroPosition,
            map: map
        });
    }
</script>
