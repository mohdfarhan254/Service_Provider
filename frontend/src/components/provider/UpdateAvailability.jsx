// 📄 src/components/provider/UpdateAvailability.jsx

import React, { useState } from 'react';
import { updateAvailability } from '../../services/providerService';

const UpdateAvailability = () => {
  const [status, setStatus] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleUpdate = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      await updateAvailability({ availability: status }); // ✅ Backend expects 'availability'
      setSuccess(`✅ Availability updated to "${status}"`);
      setStatus('');
    } catch (err) {
      setError(err.response?.data?.message || '❌ Failed to update availability');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="w-full max-w-md p-6 bg-white rounded-xl shadow-md">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">🕒 Update Availability</h2>

        {error && <p className="text-red-600 text-center mb-4">{error}</p>}
        {success && <p className="text-green-600 text-center mb-4">{success}</p>}

        <form onSubmit={handleUpdate} className="space-y-4">
          <select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
            className="w-full p-3 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="" disabled>Select your current status</option>
            <option value="AVAILABLE">✅ Available</option>
            <option value="BUSY">⛔ Busy</option>
            <option value="OFFLINE">🚫 Offline</option>
          </select>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
          >
            🔄 Update Status
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateAvailability;
