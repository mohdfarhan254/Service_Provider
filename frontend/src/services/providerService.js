// 📁 src/services/providerService.js
import axios from 'axios';

// ✅ Create axios instance for provider-related API calls
const API = axios.create({
  baseURL: 'http://localhost:8080/api/provider',
});

// ✅ Attach Bearer token if present in localStorage
API.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// 🔹 Enroll provider to a service
export const enrollService = (data) =>
  API.post('/enroll', data, {
    headers: { 'Content-Type': 'application/json' },
  });

// 🔹 Update provider's availability status
// ✅ Update provider's availability status
export const updateAvailability = (data) =>
  API.patch('/availability', data, {
    headers: { 'Content-Type': 'application/json' },
  });

