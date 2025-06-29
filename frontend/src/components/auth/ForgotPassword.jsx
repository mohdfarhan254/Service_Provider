// 📄 src/components/auth/ForgotPassword.jsx

import React, { useState } from 'react';
import { requestOtp } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRequestOtp = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await requestOtp(email);
      alert("OTP sent to your email.");
      navigate('/verify-otp', { state: { email } });
    } catch (err) {
      const msg = err.response?.data || 'Failed to send OTP';
      setError(msg);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-gray-800 mb-4 text-center">🔑 Forgot Password</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 p-2 rounded text-sm mb-4">
            {error}
          </p>
        )}

        <form onSubmit={handleRequestOtp} className="space-y-4">
          <input
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            📩 Send OTP
          </button>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
