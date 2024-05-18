// function getUserLocationAndFetchHotels() {
//     // Check if Geolocation is supported by the browser
//     if (navigator.geolocation) {
//         // Request the user's current position
//         navigator.geolocation.getCurrentPosition(
//             function(position) {
//                 // Success callback: position object contains user's coordinates
//                 const latitude = position.coords.latitude;
//                 const longitude = position.coords.longitude;
//
//                 let radius= document.getElementById("radius").value;
//
//                 // Call your backend endpoint with the user's coordinates
//                 fetch(`/find-hotels?latitude=${latitude}&longitude=${longitude}&radius=${radius}`)
//                     .then(response => response.text()) // Expecting HTML content
//                     .then(html => {
//                         // Render the HTML content received from the backend
//                         document.getElementById('hotelsList').innerHTML = html;
//                     })
//                     .catch(error => {
//                         console.error('Error fetching nearby hotels:', error);
//                     });
//             },
//             function(error) {
//                 // Error callback: handle errors if user's location cannot be obtained
//                 console.error('Error getting user location:', error);
//             }
//         );
//     } else {
//         // Geolocation is not supported
//         console.error('Geolocation is not supported by this browser.');
//     }
// }
//
// // Call the function to retrieve user's location and fetch nearby hotels when needed
// getUserLocationAndFetchHotels();


function getUserLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function(position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;
                const radiusInput = document.getElementById("radius");
                const radius = parseFloat(radiusInput.value);

                window.location.href = `/find-hotels?latitude=${latitude}&longitude=${longitude}&radius=${radius}`;
            },
            function(error) {
                console.error('Error getting user location:', error);
            }
        );
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
}

document.getElementById("searchForm").addEventListener("submit", function(event) {
    event.preventDefault();
    getUserLocation();
});

