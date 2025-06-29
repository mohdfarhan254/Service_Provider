// 📁 src/services/consumerService.js
import axios from 'axios';

// ✅ Axios instance for consumer APIs
const API = axios.create({
  baseURL: 'http://localhost:8080/api/consumer',
});

// ✅ Attach token only if present
API.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// 🔹 Get all available services (requires token)
export const getAllServices = () => API.get('/services');

// 🔹 Get all providers under a selected service
export const getProvidersByService = (serviceId) =>
  API.get(`/providers/${serviceId}`);

// 🔹 Submit feedback for a provider
export const submitFeedback = (data) => API.post('/feedback', data);


// 🔹 Get all feedbacks for a provider
export const getProviderFeedbacks = (providerId) =>
  API.get(`/feedback/${providerId}`);

// 🔹 Book a provider by ID
export const bookProvider = (providerId) =>
  API.post(`/book/${providerId}`, null, {
    headers: { 'Content-Type': 'application/json' },
  });
