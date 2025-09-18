
import React, { useState } from 'react';
import Login from './Login';
import Register from './Register';
import './App.css';

const Auth = () => {
  const [isLogin, setIsLogin] = useState(true);

  return (
    <div className="auth-container">
  <div className="form-box">
    <h2>{isLogin ? 'Login' : 'Signup'}</h2>
    {isLogin ? <Login /> : <Register />}
    <p>
      {isLogin ? (
        <>Don’t have an account? <span onClick={() => setIsLogin(false)}>Signup here</span></>
      ) : (
        <>Already have an account? <span onClick={() => setIsLogin(true)}>Login here</span></>
      )}
    </p>
  </div>
</div>

  );
};

export default Auth;
