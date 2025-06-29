// 📄 src/pages/Home.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 px-4">
      <div className="bg-white shadow-lg rounded-xl p-8 max-w-md w-full text-center">
        <h1 className="text-3xl font-semibold text-gray-800 mb-6">
          Welcome to the Service Provider App
        </h1>
        <div className="flex justify-center gap-4">
          <button
            className="bg-blue-600 hover:bg-blue-700 transition text-white px-6 py-2 rounded-lg font-medium shadow"
            onClick={() => navigate('/login')}
          >
            Login
          </button>
          <button
            className="bg-green-600 hover:bg-green-700 transition text-white px-6 py-2 rounded-lg font-medium shadow"
            onClick={() => navigate('/register')}
          >
            Register
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
