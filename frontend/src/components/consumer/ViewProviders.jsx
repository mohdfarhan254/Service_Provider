import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const ViewProviders = () => {
  const { serviceId } = useParams();
  const [providers, setProviders] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProviders = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(`http://localhost:8080/api/consumer/providers/${serviceId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setProviders(response.data);
      } catch (err) {
        console.error('Error fetching providers:', err);
        setError('❌ Failed to fetch providers. Make sure you are logged in as a consumer.');
      }
    };

    if (serviceId) {
      fetchProviders();
    } else {
      setError('Invalid service ID.');
    }
  }, [serviceId]);

  const handleBook = async (providerId) => {
    try {
      const token = localStorage.getItem('token');
      await axios.post(
        `http://localhost:8080/api/consumer/book/${providerId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      alert('✅ Provider booked successfully.');
    } catch (err) {
      console.error('Booking failed:', err);
      alert('❌ Booking failed.');
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 py-10 px-4">
      <div className="max-w-5xl mx-auto">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-8">
          🧑‍🔧 Providers for This Service
        </h2>

        {error && <p className="text-red-600 text-center mb-6">{error}</p>}

        <div className="grid gap-6">
          {providers.map((p) => (
            <div
              key={p.id}
              className="bg-white border border-gray-200 p-6 rounded-xl shadow hover:shadow-md transition"
            >
              <div className="mb-3">
                <h3 className="text-lg font-semibold text-gray-800">{p.name}</h3>
                <p className="text-sm text-gray-600">📧 {p.email}</p>
                <p className="text-sm text-gray-600">📞 {p.phone}</p>
              </div>

              <div className="flex flex-wrap gap-3 mt-4">
                <button
                  onClick={() => handleBook(p.id)}
                  className="bg-green-600 hover:bg-green-700 text-white py-1.5 px-4 rounded shadow"
                >
                  ✅ Book
                </button>

                <button
                  onClick={() => navigate(`/consumer/feedback/${p.id}`)}
                  className="bg-blue-600 hover:bg-blue-700 text-white py-1.5 px-4 rounded shadow"
                >
                  ✍️ Leave Feedback
                </button>

                <button
                  onClick={() => navigate(`/consumer/feedbacks/${p.id}`)}
                  className="bg-gray-700 hover:bg-gray-800 text-white py-1.5 px-4 rounded shadow"
                >
                  📋 View Feedback
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ViewProviders;
