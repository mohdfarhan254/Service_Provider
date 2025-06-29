// 📄 src/components/auth/VerifyOtp.jsx

import React, { useState, useEffect } from 'react';
import { verifyOtp } from '../../services/authService';
import { useNavigate, useLocation } from 'react-router-dom';

const VerifyOtp = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    if (location.state?.email) {
      setEmail(location.state.email);
    } else {
      setError('Email missing. Please verify OTP first.');
    }
  }, [location]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await verifyOtp({ email, otp });
      alert('OTP verified! Please reset your password.');
      navigate('/reset-password', { state: { email, otp } });
    } catch (err) {
      const msg = err.response?.data || 'Invalid OTP';
      setError(msg);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-gray-800 mb-4 text-center">🔑 Verify OTP</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 p-2 rounded text-sm mb-4">
            {error}
          </p>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Enter OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-400"
          />
          <button
            type="submit"
            className="w-full bg-green-600 hover:bg-green-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            ✅ Verify OTP
          </button>
        </form>
      </div>
    </div>
  );
};

export default VerifyOtp;
