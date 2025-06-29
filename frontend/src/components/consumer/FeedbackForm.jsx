// 📁 src/components/consumer/FeedbackForm.jsx
import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const FeedbackForm = () => {
  const { providerId } = useParams();
  const [comment, setComment] = useState('');
  const [rating, setRating] = useState('');
  const [liked, setLiked] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    try {
      await axios.post(
        `http://localhost:8080/api/consumer/providers/${providerId}/feedback`,
        {
          comment,
          rating: Number(rating),
          liked,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      alert('Feedback submitted successfully.');
      setComment('');
      setRating('');
      setLiked(false);
    } catch (err) {
      console.error(err);
      alert('Failed to submit feedback.');
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-xl">
        <h2 className="text-2xl font-bold text-gray-800 mb-6 text-center">
          📝 Feedback for Provider #{providerId}
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <textarea
            placeholder="Your comment"
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            required
            className="w-full h-28 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400 resize-none"
          />

          <input
            type="number"
            min="1"
            max="5"
            placeholder="Rating (1-5)"
            value={rating}
            onChange={(e) => setRating(e.target.value)}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-400"
          />

          <label className="flex items-center gap-2 text-gray-700 text-sm">
            <input
              type="checkbox"
              checked={liked}
              onChange={(e) => setLiked(e.target.checked)}
              className="w-4 h-4"
            />
            👍 Like this provider?
          </label>

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 rounded-md shadow transition"
          >
            🚀 Submit Feedback
          </button>
        </form>
      </div>
    </div>
  );
};

export default FeedbackForm;
