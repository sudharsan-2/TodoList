import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Post from './Post'
import Update from './Update'
import Read from './Read'
import Auth from './Auth'
function App() {

  return (

    <BrowserRouter>
      <Routes>
         <Route path="/" element={<Auth />} />
        <Route path='/post' element={<Post />} />
        <Route path='/read' element={<Read />} />
        <Route path='/update' element={<Update />} />

      </Routes>
    </BrowserRouter>

  )
}
export default App
