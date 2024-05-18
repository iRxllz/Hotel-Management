function getUserLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            const locationDiv = document.getElementById('location');
            // locationDiv.innerHTML = `Your location: <br>Lat: ${latitude}, Long: ${longitude}`;

            searchHotels(latitude, longitude);
        });
    } else {
        alert('Geolocation is not supported by this browser.');
    }
}

function searchHotels(latitude, longitude) {
    const radius = document.getElementById('radius-input').value * 1000; // km => m

    fetch(`http://localhost:8080/hotels?userLat=${latitude}&userLong=${longitude}&radius=${radius}`)
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = '';

            data.forEach(hotel => {
                const card = document.createElement('article');
                card.className = 'card';
                card.onclick = () => {
                    window.location.href = `hotel_details.html?hotelId=${hotel.hotelId}`;
                };

                const cardTitle = document.createElement('h2');
                cardTitle.className = 'card-title';
                cardTitle.textContent = hotel.name;

                const cardDetails = document.createElement('div');
                cardDetails.className = 'card-details';
                cardDetails.innerHTML = `
                            <p>Latitude: ${hotel.latitude}</p>
                            <p>Longitude: ${hotel.longitude}</p>
                        `;

                console.log(hotel);

                const availableRooms = hotel.rooms.filter(room => room.available).length;
                const roomAvailability = document.createElement('p');
                roomAvailability.className = 'card-details';
                roomAvailability.textContent = `Available Rooms: ${availableRooms}`;

                card.appendChild(cardTitle);
                card.appendChild(cardDetails);
                card.appendChild(roomAvailability);
                resultsDiv.appendChild(card);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}