import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import API_URL from './Api/post';
import './App.css'

const Register = () => {

  const [username,setUserName]=useState("");
  const [email,setEmail]=useState("");
  const [password,setPassword]=useState("");
  const navigate=useNavigate();

  const handleSubmit=async(e)=>{
  e.preventDefault();
    try{
    const response=await API_URL .post(`/auth/register`,{
    username,email,password})
    setUserName('');
    setEmail('');
    setPassword('');
    console.log(response.data)
    navigate('/')
    }
    catch(err){console.log(err)}
  }
  return (

    

    <form className="auth-form" onSubmit={handleSubmit}>
      <label htmlFor="userName">UserName</label>
      <input type="text" name="username" placeholder='Enter Your Name' value={username} onChange={(e)=>setUserName(e.target.value)}/>

      <label htmlFor="email">Email</label>
      <input type="email" name="email" placeholder='Enter Your Email' value={email} onChange={(e)=>setEmail(e.target.value)}/> 
    <label htmlFor="password">Password</label>
    <input type="password" name="password" placeholder='Enter Your Password' value={password} onChange={(e)=>setPassword(e.target.value)}/>
    <button type='submit'>SignUP</button>
    </form>
    
   
  )
}

export default Register