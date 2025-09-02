// 📄 src/components/auth/Register.jsx

import React, { useState } from 'react';// adds a state variable to functional components
import { register } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [form, setForm] = useState({
    name: '',
    email: '',
    password: '',
    phone: '',
    address: '',
    role: 'CONSUMER',
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });// spread operator makes shalow copy to copy the values efficiently

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await register(form);
      alert('Registered successfully');
      navigate('/login');
    } catch (err) {
      setError(err.response?.data?.error || 'Registration failed');// ? chaning operator safe access of nested values
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-gray-800 mb-4 text-center">📝 Register</h2>

        {error && (
          <p className="text-red-600 bg-red-100 border border-red-300 p-2 rounded text-sm mb-4">
            {error}
          </p>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          {['name', 'email', 'password', 'phone', 'address'].map((field) => (
            <input
              key={field}
              type={field === 'password' ? 'password' : 'text'}
              name={field}
              value={form[field]}
              placeholder={field.charAt(0).toUpperCase() + field.slice(1)}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-400"
              required
            />
          ))}

          <select
            name="role"
            value={form.role}
            onChange={handleChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-400"
          >
            <option value="CONSUMER">Consumer</option>
            <option value="PROVIDER">Provider</option>
          </select>

          <button
            type="submit"
            className="w-full bg-green-600 hover:bg-green-700 transition text-white font-medium py-2 rounded-md shadow"
          >
            ✅ Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default Register;
