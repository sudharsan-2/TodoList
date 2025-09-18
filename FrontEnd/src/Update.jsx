import { useEffect, useState } from 'react'
import API_URL from './Api/post';
import { useNavigate} from 'react-router-dom';
import './Todo.css';

const Update = () => {

  const [task, setTask] = useState('');
  const [checked, setChecked] = useState(false);
  const [id, setId] = useState(null);


  const navigate = useNavigate();


  useEffect(() => {

     setId((localStorage.getItem('id')));
      setChecked(localStorage.getItem('checked') === 'true');
      setTask(localStorage.getItem('task'));
  }, [])

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await API_URL.put(`/todo/update/${id}`, { task, checked });

      navigate('/post');
    }
    catch (err) {
      console.log(err)
    }

  }

  return (

    <div className="page-container">
    <form onSubmit={handleUpdate}>
      <label htmlFor="task">Enter Update Task </label>
      <input
        name="task"
        placeholder="Enter Update Task"
        value={task}
        onChange={(e) => setTask(e.target.value)} />
        <div className="form-group">
        <label htmlFor="checked">Update Here</label>
      <input type="checkbox" checked={checked}
        onChange={(e) => setChecked(e.target.checked)}>
        </input>
     </div>
      <button >Update</button><br />
    </form>
    </div>

  )
}

export default Update