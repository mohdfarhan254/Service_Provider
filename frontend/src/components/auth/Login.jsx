// 📄 src/components/auth/Login.jsx

import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { login } from '../../services/authService';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await login({ email, password });

      // ✅ Assuming backend sends: { token, role }
      const { token, role } = response.data;

      // ✅ Save token and role to localStorage
      localStorage.setItem('token', token);
      localStorage.setItem('role', role);

      // ✅ Navigate based on role
      if (role === 'ADMIN') navigate('/admin/dashboard');
      else if (role === 'PROVIDER') navigate('/provider/dashboard');
      else if (role === 'CONSUMER') navigate('/consumer/dashboard');
      else setError('Unknown role. Cannot redirect.');
    } catch (err) {
      const msg = err.response?.data || 'Login failed. Check your credentials.';
      setError(msg);
      console.error('Login Error:', err);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-gray-800 mb-4 text-center">🔐 Login</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 rounded p-2 text-sm mb-4">
            {error}
          </p>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="email"
            placeholder="Email"
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            value={email}
            onChange={(e) => setEmail(e.target.value)}// e represents the event object that is passed to the onChange handler function.
            required
          />

          <input
            type="password"
            placeholder="Password"
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            🔓 Login
          </button>
        </form>

        <p className="text-sm text-center mt-4">
          <Link to="/forgot-password" className="text-blue-600 hover:underline">
            Forgot Password?
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
