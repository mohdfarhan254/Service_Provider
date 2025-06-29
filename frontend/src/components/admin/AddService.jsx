// 📄 src/pages/admin/AddService.jsx
import React, { useState } from 'react';
import { addService } from '../../services/adminService';

const AddService = () => {
  const [name, setName] = useState('');
  const [desc, setDesc] = useState('');
  const [message, setMessage] = useState('');

  const handleAdd = async (e) => {
    e.preventDefault();
    try {
      await addService({ name, description: desc });
      setMessage('Service added successfully!');
      setName('');
      setDesc('');
    } catch (err) {
      setMessage(err.response?.data || 'Failed to add service.');
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-semibold text-gray-800 mb-4">➕ Add Service</h2>

        {message && (
          <p className="text-sm mb-4 text-blue-600 bg-blue-50 p-2 rounded border border-blue-200">
            {message}
          </p>
        )}

        <form onSubmit={handleAdd} className="space-y-4">
          <input
            type="text"
            placeholder="Service Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
          <textarea
            placeholder="Description"
            value={desc}
            onChange={(e) => setDesc(e.target.value)}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-md resize-none focus:outline-none focus:ring-2 focus:ring-blue-400"
            rows={4}
          />
          <button
            type="submit"
            className="w-full bg-green-600 hover:bg-green-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            ✅ Add Service
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddService;
