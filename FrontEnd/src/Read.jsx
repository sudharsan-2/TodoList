
import React, { useEffect, useState } from 'react'
import API_URL from './Api/post';
import { useNavigate} from 'react-router-dom';
import './Todo.css';

const Read = ({refresh}) => {

  const [todos, setTodos] = useState([]);
  const navigate = useNavigate();

  //update
  async function handleUpdate(item) {
    localStorage.setItem('id',item.id.toString());
    localStorage.setItem('task',item.task);
    localStorage.setItem('checked',item.checked?true:false);
    navigate('/update'); 
  }

 // logOut
  const handleLogOut=()=>{
    localStorage.clear();
    navigate('/')
  };

  //Delete
   const handleDelete=async(id)=> {
    try{
    await API_URL.delete(`/todo/delete/${id}`);
    setTodos((prev)=>prev.filter((item) => item.id !== id));}
    catch(err){console.log(err)}
  }

  //fetchAllData
const fetchAllData=async()=> {

    try {
      const response = await API_URL.get(`/todo/getAll`);
      setTodos(response.data);
      console.log(response.data);
    }
    catch (err) { console.log(err) }
  }
  useEffect(() => {
    fetchAllData()
  },[refresh])

  return (
    <div className="read-container">
    <button className="logout-btn" onClick={handleLogOut}>LogOut</button>
    <div className="table-container">
    <table>
      <thead >
        <tr>
          <th>Task</th>
          <th>Checked</th>
          <th>Delete</th>
          <th>Update</th>
        </tr>
      </thead>
      <tbody>
        {todos.map((item) => (
          <tr key={item.id}>
            <td>{item.task}</td>
            <td>{item.checked ? "Completed" : "Not Completed"}</td>
            <td><button className="delete-btn" onClick={()=>handleDelete(item.id)}>Delete</button></td>
            <td><button className="update-btn" onClick={()=>handleUpdate(item)}>Update</button></td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
</div>


  )
}

export default Read