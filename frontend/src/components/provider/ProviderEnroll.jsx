import React, { useState } from 'react';
import { enrollService } from '../../services/providerService';

const ProviderEnroll = () => {
  const [serviceId, setServiceId] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleEnroll = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      await enrollService({ serviceId });
      setSuccess('✅ Enrolled successfully!');
      setServiceId('');
    } catch (err) {
      setError(err.response?.data || '❌ Failed to enroll');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="w-full max-w-md p-6 bg-white rounded-xl shadow-md">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">📥 Enroll to Service</h2>

        {error && <p className="text-red-600 mb-4 text-center">{error}</p>}
        {success && <p className="text-green-600 mb-4 text-center">{success}</p>}

        <form onSubmit={handleEnroll} className="space-y-4">
          <input
            type="text"
            placeholder="Enter Service ID"
            value={serviceId}
            onChange={(e) => setServiceId(e.target.value)}
            required
            className="w-full p-3 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
          >
            🚀 Enroll
          </button>
        </form>
      </div>
    </div>
  );
};

export default ProviderEnroll;
