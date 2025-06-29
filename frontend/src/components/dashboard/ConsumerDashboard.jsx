import React from 'react';
import { useNavigate } from 'react-router-dom';
import DashboardNavbar from '../common/DashboardNavbar';

const ConsumerDashboard = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gray-100">
      <DashboardNavbar role="Consumer" />

      <div className="p-6 flex items-center justify-center">
        <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md text-center">
         
          <div className="flex flex-col gap-4">
            <button
              className="bg-blue-600 hover:bg-blue-700 transition text-white px-6 py-2 rounded-lg font-medium"
              onClick={() => navigate('/consumer/services')}
            >
              🛠️ View Services
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ConsumerDashboard;
