import React, {useState } from 'react'
import API_URL  from './Api/post';
import { useNavigate } from 'react-router-dom';
import './App.css'
const Login = () => {

const [email,setEmail]=useState('');
const [password,setPassword]=useState('');
const navigate=useNavigate();

const handleSubmit=async(e)=>{
  e.preventDefault();

  try{
  const response=await API_URL.post(`/auth/login`,{email,password})
  localStorage.setItem("token",response.data.token);
  localStorage.setItem("username",response.data.username);
  setEmail("");
  setPassword("")
  alert("Login Success")
  navigate('/post')
  }
  catch(err){
    console.log(err)
    alert('Login failed')
  }
}
// handleSubmit();

  
  return (
  
    <form  className="auth-form" onSubmit={handleSubmit}>
      <label htmlFor="email">Enter Your Email</label>
      <input type="email" placeholder='Enter Your Email' value={email} onChange={(e)=>setEmail(e.target.value)} />

    <label htmlFor="password">Enter Your Password</label>
    <input type="password" placeholder='Enter Your Password' value={password} onChange={(e)=>setPassword(e.target.value)} />

    <button type='submit'>Login</button>
    </form>
  )
}

export default Login