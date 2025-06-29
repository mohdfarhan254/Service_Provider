import React from 'react';
import { useNavigate } from 'react-router-dom';
import DashboardNavbar from '../common/DashboardNavbar';

const AdminDashboard = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gray-100">
      <DashboardNavbar role="Admin" />

      <div className="p-6 flex items-center justify-center">
        <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md text-center">
         

          <div className="flex flex-col gap-4">
            <button
              className="bg-blue-600 hover:bg-blue-700 transition text-white px-6 py-2 rounded-lg font-medium"
              onClick={() => navigate('/admin/add-service')}
            >
              ➕ Add Service
            </button>
            <button
              className="bg-green-600 hover:bg-green-700 transition text-white px-6 py-2 rounded-lg font-medium"
              onClick={() => navigate('/admin/providers')}
            >
              👥 View Providers
            </button>
            <button
              className="bg-purple-600 hover:bg-purple-700 transition text-white px-6 py-2 rounded-lg font-medium"
              onClick={() => navigate('/admin/services')}
            >
              📋 View Services
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
