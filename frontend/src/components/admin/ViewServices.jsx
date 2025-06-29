// 📄 src/pages/admin/ViewServices.jsx
import React, { useEffect, useState } from 'react';
import { getAllServices } from '../../services/adminService';

const ViewServices = () => {
  const [services, setServices] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchServices = async () => {
      try {
        const res = await getAllServices();
        setServices(res.data);
      } catch (err) {
        console.error('Error fetching services:', err);
        setError('Failed to load services. Please make sure you are logged in as an admin.');
      }
    };

    fetchServices();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-4xl mx-auto bg-white shadow-lg rounded-xl p-6">
        <h2 className="text-3xl font-semibold text-gray-800 mb-6 text-center">📋 All Services</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 rounded p-3 mb-4 text-sm">
            {error}
          </p>
        )}

        <ul className="space-y-4">
          {services.map((service) => (
            <li
              key={service.id}
              className="border border-gray-300 rounded-lg p-4 bg-gray-50 shadow-sm"
            >
              <h3 className="text-xl font-bold text-gray-700 mb-1">{service.name}</h3>
              <p className="text-gray-600 text-sm">{service.description}</p>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ViewServices;
