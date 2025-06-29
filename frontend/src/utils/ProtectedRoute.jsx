// 📁 src/utils/ProtectedRoute.jsx
import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ element, allowedRole }) => {
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');

  // ✅ Not logged in → redirect to login
  if (!token || !role) {
    return <Navigate to="/login" replace />;
  }

  // ✅ Normalize roles to prevent case mismatch
  const normalizedRole = role.toUpperCase();
  const normalizedAllowed = allowedRole.toUpperCase();

  // ✅ Role mismatch → redirect to own dashboard
  if (normalizedRole !== normalizedAllowed) {
    return <Navigate to={`/${normalizedRole.toLowerCase()}`} replace />;
  }

  // ✅ Authorized → render component
  return element;
};

export default ProtectedRoute;
