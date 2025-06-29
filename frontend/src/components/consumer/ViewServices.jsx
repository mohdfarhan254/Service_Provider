import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const ViewServices = () => {
  const [services, setServices] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchServices = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/api/consumer/services', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setServices(response.data);
      } catch (err) {
        console.error('Error fetching services:', err);
        setError('❌ Failed to load services. Please log in as a consumer.');
      }
    };

    fetchServices();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 py-10 px-4">
      <div className="max-w-5xl mx-auto">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-8">🛠️ Available Services</h2>

        {error && <p className="text-red-600 text-center mb-6">{error}</p>}

        <div className="grid gap-6">
          {services.map((service) => (
            <div
              key={service.id}
              className="bg-white border border-gray-200 p-6 rounded-xl shadow hover:shadow-md transition"
            >
              <h3 className="text-lg font-semibold text-gray-800">{service.name}</h3>
              <p className="text-sm text-gray-600 mt-2">{service.description}</p>
              <button
                onClick={() => navigate(`/consumer/providers/${service.id}`)}
                className="mt-4 bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded shadow"
              >
                🔍 View Providers
              </button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ViewServices;
