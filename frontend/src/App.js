// 📁 src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './components/Home/Home';

// Auth
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import ForgotPassword from './components/auth/ForgotPassword';
import VerifyOtp from './components/auth/VerifyOtp';
import ResetPassword from './components/auth/ResetPassword';

// Admin
import AdminDashboard from './components/dashboard/AdminDashboard';
import AddService from './components/admin/AddService';
import ViewServices from './components/admin/ViewServices';
import ViewProviders from './components/admin/ViewProviders';

// Provider
import ProviderDashboard from './components/dashboard/ProviderDashboard';
import ProviderEnroll from './components/provider/ProviderEnroll';
import UpdateAvailability from './components/provider/UpdateAvailability';

// Consumer
import ConsumerDashboard from './components/dashboard/ConsumerDashboard';
import ViewServicesConsumer from './components/consumer/ViewServices';
import ViewProvidersConsumer from './components/consumer/ViewProviders';
import FeedbackForm from './components/consumer/FeedbackForm';
import ProviderFeedbackList from './components/consumer/ProviderFeedbackList';

// Utils
import ProtectedRoute from './utils/ProtectedRoute';

const App = () => {
  return (
    <Router>
      <Routes>

        {/* Public Routes */}
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/verify-otp" element={<VerifyOtp />} />
        <Route path="/reset-password" element={<ResetPassword />} />

        {/* 🔐 Admin Routes */}
        <Route path="/admin" element={<ProtectedRoute element={<AdminDashboard />} allowedRole="ADMIN" />} />
        <Route path="/admin/dashboard" element={<ProtectedRoute element={<AdminDashboard />} allowedRole="ADMIN" />} />
        <Route path="/admin/add-service" element={<ProtectedRoute element={<AddService />} allowedRole="ADMIN" />} />
        <Route path="/admin/services" element={<ProtectedRoute element={<ViewServices />} allowedRole="ADMIN" />} />
        <Route path="/admin/providers" element={<ProtectedRoute element={<ViewProviders />} allowedRole="ADMIN" />} />

        {/* 🔐 Provider Routes */}
        <Route path="/provider" element={<ProtectedRoute element={<ProviderDashboard />} allowedRole="PROVIDER" />} />
        <Route path="/provider/dashboard" element={<ProtectedRoute element={<ProviderDashboard />} allowedRole="PROVIDER" />} />
        <Route path="/provider/enroll" element={<ProtectedRoute element={<ProviderEnroll />} allowedRole="PROVIDER" />} />
        <Route path="/provider/availability" element={<ProtectedRoute element={<UpdateAvailability />} allowedRole="PROVIDER" />} />

        {/* 🔐 Consumer Routes */}
        <Route path="/consumer" element={<ProtectedRoute element={<ConsumerDashboard />} allowedRole="CONSUMER" />} />
        <Route path="/consumer/dashboard" element={<ProtectedRoute element={<ConsumerDashboard />} allowedRole="CONSUMER" />} />
        <Route path="/consumer/services" element={<ProtectedRoute element={<ViewServicesConsumer />} allowedRole="CONSUMER" />} />
        <Route path="/consumer/providers/:serviceId" element={<ProtectedRoute element={<ViewProvidersConsumer />} allowedRole="CONSUMER" />} />
        <Route path="/consumer/feedback/:providerId" element={<ProtectedRoute element={<FeedbackForm />} allowedRole="CONSUMER" />} />
        <Route path="/consumer/feedbacks/:providerId" element={<ProtectedRoute element={<ProviderFeedbackList />} allowedRole="CONSUMER" />} />

      </Routes>
    </Router>
  );
};

export default App;
