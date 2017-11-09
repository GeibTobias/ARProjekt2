function initMap() {
    // Brandenburger Tor, Berlin
    var start = {
        lat: 52.51369,
        lng: 13.391159
    };

    var map = new google.maps.Map(document.getElementById('map'), {
        center: start,
        zoom: 14,
        scrollwheel: false
    });

    //    var image = 'images/markers144/VuMark00.png';
    //    var brandenburgerMarker = new google.maps.Marker({
    //        position: start,
    //        map: map,
    //        icon: image
    //    });

    // Specify location, radius and place types for your Places API search.
    var request = {
        location: start,
        radius: '20000',
        type: 'point_of_interest'
    };

    // Create the PlaceService and send the request.
    // Handle the callback with an anonymous function.
    var service = new google.maps.places.PlacesService(map);
    var pagesCount = 0;
    var placesCount = 0;
    var waypoints = [];
    service.nearbySearch(request, function (results, status, pagination) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
            console.log(results);
            for (var i = 0; i < results.length; i++) {
                var image = 'images/qr50/' + (i+1) + '.png'
                var place = results[i];
                // If the request succeeds, draw the place location on
                // the map as a marker, and register an event to handle a
                // click on the marker.
                var marker = new google.maps.Marker({
                    map: map,
                    position: place.geometry.location,
                    icon: image
                });
                waypoints.push({location: place.geometry.location})
                placesCount++;

                if (placesCount == 8) {
                    // Set destination, origin and travel mode.
                    var request = {
                        destination: 'berlin tränenpalast',
                        origin: start,
                        travelMode: 'WALKING',
                        waypoints: waypoints,
                        optimizeWaypoints: true
                    };

                    // Pass the directions request to the directions service.
                    var directionsService = new google.maps.DirectionsService();
                    directionsService.route(request, function (response, status) {
                        if (status == 'OK') {
                            // Display the route on the map.
                            directionsDisplay.setDirections(response);
                        }
                    });
                }
            }

            if (pagesCount < 2) {
                pagesCount++;
                pagination.nextPage()
            }
        }
    });

    var directionsDisplay = new google.maps.DirectionsRenderer({
        map: map,
        suppressMarkers: true,
        preserveViewport: true,
        polylineOptions: {strokeColor: 'Red'}
    });
}
