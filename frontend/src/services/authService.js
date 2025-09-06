// 📁 src/services/authService.js
import axios from 'axios';
 
// ✅ Axios instance for authenticated APIs (adds token automatically)
const API = axios.create({
  baseURL: 'http://localhost:8080/api/auth',
});

// ✅ Add token only for authenticated requests
API.interceptors.request.use((config) => {
  // 🔐 Skip attaching token for login or register
  if (
    !config.url.endsWith('/login') &&
    !config.url.endsWith('/register') &&
    !config.url.endsWith('/request-password-reset-otp') &&
    !config.url.endsWith('/verify-otp') &&
    !config.url.endsWith('/reset-password')
  ) {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
  }
  return config;
});

// ✅ Plain login with raw axios (no token attached)
export const login = (data) => {
  return axios.post('http://localhost:8080/api/auth/login', data, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
};

// ✅ Register (no token needed)
export const register = (data) => {
  return axios.post('http://localhost:8080/api/auth/register', data, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
};

// 🔁 Password Reset Flow (no token required)
export const requestOtp = (email) =>
  axios.post('http://localhost:8080/api/auth/request-password-reset-otp', { email });

export const verifyOtp = (data) =>
  axios.post('http://localhost:8080/api/auth/verify-otp', data);

export const resetPassword = (data) =>
  axios.post('http://localhost:8080/api/auth/reset-password', data);

// 🛡️ Protected APIs (token is auto-added)
export const fetchProtected = () => API.get('/some-protected-endpoint'); // example
