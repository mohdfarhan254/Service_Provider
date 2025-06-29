// 📄 src/pages/admin/ViewProviders.jsx
import React, { useEffect, useState } from 'react';
import { getAllProviders } from '../../services/adminService';

const ViewProviders = () => {
  const [providers, setProviders] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchProviders = async () => {
      try {
        const res = await getAllProviders();
        setProviders(res.data);
      } catch (err) {
        console.error('Error fetching providers:', err);
        setError('Failed to load providers. Make sure you are logged in as an admin.');
      }
    };

    fetchProviders();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-4xl mx-auto bg-white rounded-xl shadow p-6">
        <h2 className="text-3xl font-semibold text-gray-800 mb-6 text-center">
          🧑‍🔧 All Service Providers
        </h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 rounded p-3 mb-4 text-sm">
            {error}
          </p>
        )}

        <ul className="space-y-4">
          {providers.map((p) => (
            <li
              key={p.id}
              className="border border-gray-300 rounded-lg p-4 bg-gray-50 shadow-sm"
            >
              <h3 className="text-xl font-bold text-gray-700 mb-1">{p.name}</h3>
              <p className="text-gray-600 text-sm">📧 Email: {p.email}</p>
              <p className="text-gray-600 text-sm">📞 Phone: {p.phone}</p>
              <p className="text-gray-600 text-sm">🏠 Address: {p.address}</p>
              <p className="text-gray-600 text-sm">
                🔁 Status:{' '}
                <span className={p.availabilityStatus ? 'text-green-600' : 'text-red-600'}>
                  {p.availabilityStatus ? 'Available' : 'Unavailable'}
                </span>
              </p>
              <p className="text-gray-600 text-sm">
                ✅ Verified:{' '}
                <span className={p.isVerified ? 'text-green-600' : 'text-red-600'}>
                  {p.isVerified ? 'Yes' : 'No'}
                </span>
              </p>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ViewProviders;
