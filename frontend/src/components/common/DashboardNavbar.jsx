import React from 'react';
import { useNavigate } from 'react-router-dom';

const DashboardNavbar = ({ role = 'Dashboard' }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  return (
    <nav className="bg-white shadow px-6 py-3 flex justify-between items-center">
      <h1 className="text-xl font-bold text-gray-800">{role} Dashboard</h1>
      <button
        onClick={handleLogout}
        className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded shadow"
      >
        🔒 Logout
      </button>
    </nav>
  );
};

export default DashboardNavbar;
