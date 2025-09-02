// 📄 src/components/auth/ResetPassword.jsx

import React, { useState, useEffect } from 'react';
import { resetPassword } from '../../services/authService';
import { useNavigate, useLocation } from 'react-router-dom';

const ResetPassword = () => {
  const location = useLocation();//Accessing Current URL Information:
  const navigate = useNavigate();
  const [newPassword, setNewPassword] = useState('');
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [error, setError] = useState('');
// useEffect hook to perform actions or side effects 
// whenever the URL or specific parts of the location object change.
  useEffect(() => {
    if (location.state?.email && location.state?.otp) {
      setEmail(location.state.email);
      setOtp(location.state.otp);
    } else {
      setError('Missing credentials. Please verify OTP again.');
    }
  }, [location]);

  const handleReset = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await resetPassword({ email, otp, newPassword });
      alert('Password reset successful. You can now login.');
      navigate('/login');
    } catch (err) {
      const msg = err.response?.data || 'Reset failed';
      setError(msg);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-gray-800 mb-4 text-center">🔒 Reset Password</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 rounded p-2 text-sm mb-4">
            {error}
          </p>
        )}

        <form onSubmit={handleReset} className="space-y-4">
          <input
            type="password"
            placeholder="New Password"
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            🔁 Reset Password
          </button>
        </form>
      </div>
    </div>
  );
};

export default ResetPassword;
