function submitFeedback() {
    const hotelId = document.getElementById('hotel-id').value;
    const serviceRating = document.getElementById('service-rating').value;
    const cleanlinessRating = document.getElementById('cleanliness-rating').value;
    const foodRating = document.getElementById('food-rating').value;
    const comments = document.getElementById('comments').value;

    const feedback = {
        serviceRating: serviceRating,
        cleanlinessRating: cleanlinessRating,
        foodRating: foodRating,
        comments: comments
    };

    fetch(`http://localhost:8080/feedback/${hotelId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(feedback)
    })
        .then(response => {
            if (response.ok) {
                alert('Feedback submitted successfully');
                loadFeedback(hotelId);
            } else {
                throw new Error('Failed to submit feedback');
            }
        })
        .catch(error => console.error('Error submitting feedback:', error));
}

function loadFeedback(hotelId) {
    fetch(`/feedback/${hotelId}`)
        .then(response => response.json())
        .then(data => {
            const feedbackList = document.getElementById('feedback-list');
            feedbackList.innerHTML = '';

            data.forEach(feedback => {
                const feedbackDiv = document.createElement('div');
                feedbackDiv.className = 'feedback';

                feedbackDiv.innerHTML = `
                    <p>Service Rating: ${feedback.serviceRating}</p>
                    <p>Cleanliness Rating: ${feedback.cleanlinessRating}</p>
                    <p>Food Rating: ${feedback.foodRating}</p>
                    <p>Comments: ${feedback.comments}</p>
                `;

                feedbackList.appendChild(feedbackDiv);
            });
        })
        .catch(error => console.error('Error loading feedback:', error));
}

document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const hotelId = urlParams.get('hotelId');
    document.getElementById('hotel-id').value = hotelId;
    loadFeedback(hotelId);
});