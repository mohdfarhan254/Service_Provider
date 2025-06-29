import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const ProviderFeedbackList = () => {
  const { providerId } = useParams();
  const [feedbacks, setFeedbacks] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchFeedbacks = async () => {
      try {
        const token = localStorage.getItem('token');
        const res = await axios.get(
          `http://localhost:8080/api/consumer/providers/${providerId}/feedback`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setFeedbacks(res.data);
      } catch (err) {
        console.error(err);
        setError('Failed to load feedbacks.');
      }
    };

    fetchFeedbacks();
  }, [providerId]);

  return (
    <div className="min-h-screen bg-gray-100 px-4 py-10">
      <div className="max-w-4xl mx-auto bg-white shadow-md rounded-xl p-6">
        <h2 className="text-2xl font-bold mb-6 text-gray-800 text-center">
          🗣️ Feedback for Provider #{providerId}
        </h2>

        {error && <p className="text-red-600 text-center mb-4">{error}</p>}

        {feedbacks.length === 0 ? (
          <p className="text-center text-gray-600">No feedback yet.</p>
        ) : (
          <ul className="space-y-4">
            {feedbacks.map((fb) => (
              <li key={fb.id} className="bg-gray-50 border rounded-lg p-4 shadow-sm">
                <p className="text-lg font-medium text-yellow-600">⭐ Rating: {fb.rating}</p>
                <p className="mt-1 text-gray-800">
                  <strong>Comment:</strong> {fb.comment}
                </p>
                {fb.liked && (
                  <p className="mt-1 text-green-600 font-medium">👍 Liked</p>
                )}
                <p className="text-sm text-gray-500 mt-2">
                  Submitted on: {new Date(fb.createdAt).toLocaleString()}
                </p>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default ProviderFeedbackList;
