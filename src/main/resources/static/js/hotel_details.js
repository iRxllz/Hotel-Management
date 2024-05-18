document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const hotelId = urlParams.get('hotelId');
    fetchHotelDetails(hotelId);
});

function fetchHotelDetails(hotelId) {
    fetch(`http://localhost:8080/hotels/${hotelId}`)
        .then(response => response.json())
        .then(data => {
            const hotelContainer = document.getElementById('hotel-details')
            const feedbackButton = document.createElement('button');
            feedbackButton.className = 'button feedback-button';
            feedbackButton.textContent = 'Leave feedback';
            feedbackButton.onclick = () => {
                window.location.href = `feedback.html?hotelId=${data.hotelId}`;
            };
            feedbackButton.style.position = 'absolute';
            feedbackButton.style.top = '0';
            feedbackButton.style.right = '0';
            feedbackButton.style.margin = '64px 64px 0 0';
            hotelContainer.appendChild(feedbackButton);

            document.getElementById('hotel-name').textContent = data.name;
            document.getElementById('hotel-latitude').textContent = `Latitude: ${data.latitude}`;
            document.getElementById('hotel-longitude').textContent = `Longitude: ${data.longitude}`;

            displayRooms(data.rooms);
        })
        .catch(error => console.error('Error fetching hotel details:', error));
}

function displayRooms(rooms) {
    const roomList = document.getElementById('room-list');
    roomList.innerHTML = '';

    const roomTypeMap = {
        1: 'Single Room',
        2: 'Double Room',
        3: 'Suite Room',
        4: 'Matrimonial Room'
    };

    rooms.forEach(room => {
        const roomCard = document.createElement('div');
        roomCard.className = 'room-card';

        const roomType = roomTypeMap[room.type] || 'Unknown Type';

        const roomDetails = document.createElement('div');
        roomDetails.innerHTML = `
                    <p>Room Number: ${room.roomNumber}</p>
                    <p>Type: ${roomType}</p>
                    <p>Price: ${room.price}</p>
                    <p>Available: ${room.available ? 'Yes' : 'No'}</p>
                `;

        const reserveButton = document.createElement('button');
        reserveButton.className = 'button reserve-button';
        reserveButton.textContent = 'Reserve';
        reserveButton.onclick = () => reserveRoom(room.roomId);

        roomCard.appendChild(roomDetails);
        roomCard.appendChild(reserveButton);
        roomList.appendChild(roomCard);

        if (room.available == false) {
            reserveButton.disabled = !room.isAvailable;

            const cancelButton = document.createElement('button');
            cancelButton.className = 'button cancel-button';
            cancelButton.textContent = 'Cancel';
            cancelButton.onclick = () => cancelReservation(room.roomId);
            roomCard.appendChild(cancelButton);
        }
    });
}

function reserveRoom(roomId) {
    fetch(`http://localhost:8080/rooms/${roomId}/reserve`, {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            alert('Room reserved successfully!');
            location.reload();
        })
        .catch(error => console.error('Error reserving room:', error));
}

function cancelReservation(roomId) {
    fetch(`http://localhost:8080/rooms/${roomId}/cancel`, {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            alert('Room cancelled successfully!');
            location.reload();
        })
        .catch(error => console.error('Error cancelling room:', error));
}

function leaveFeedback(hotelId) {
    window.location.href = 'http://localhost:8080/feedback';
}