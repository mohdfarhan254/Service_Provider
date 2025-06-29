// 📁 src/services/adminService.js
import axios from 'axios';

// ✅ Axios instance for admin routes
const API = axios.create({
  baseURL: 'http://localhost:8080/api/admin',
});

// ✅ Automatically attach token for every request
API.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// ✅ Optional: Handle 403 errors globally
API.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 403 || error.response?.status === 401) {
      alert('⚠️ Session expired or unauthorized. Please login again.');
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// 🔹 Add a new service
export const addService = (data) => API.post('/services', data);

// 🔹 Get all services
export const getAllServices = () => API.get('/services');

// 🔹 Get all registered providers
export const getAllProviders = () => API.get('/providers');
