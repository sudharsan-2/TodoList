
import { useState } from 'react'
import API_URL from './Api/post';
// import { useNavigate } from 'react-router-dom';
import Read from './Read'
import './Todo.css';


const Post = () => {
  const [task, setTask] = useState('');
  const [checked, setChecked] = useState(false);
  const [refresh, setRefresh] = useState(false);
  


  //Add Task
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await API_URL.post('/todo/addtodo', { task, checked });
      setTask("");
      setChecked(false);
      console.log(response.data)
      setRefresh(prev=>!prev);
    }
    catch (err) { console.log(err) }
  }

  

  return (

    <div className="page-container">
      <form onSubmit={handleSubmit}>
        <div className='task'>
        <label htmlFor="task">Enter Task</label>
        <input type="text" name='task' placeholder='Enter Your Task' value={task} onChange={(e) => setTask(e.target.value)} />
        </div>
        <div className='checked'>
        <input type="checkbox" name='checked' checked={checked} onChange={(e) => setChecked(e.target.checked)} />
        <label htmlFor="">Click Here</label>
        </div>
        <button disabled={!task.trim()}>Add Task</button>
      </form>

        <Read refresh={refresh} />


    </div>
  )
}

export default Post