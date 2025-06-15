import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Layout } from './components/Layout'
import { Dashboard } from './pages/Dashboard'
import { Users } from './pages/Users'
import { Simulations } from './pages/Simulations'
import { Bets, Gamification, Education } from './pages/index'

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/users" element={<Users />} />
          <Route path="/bets" element={<Bets />} />
          <Route path="/simulations" element={<Simulations />} />
          <Route path="/gamification" element={<Gamification />} />
          <Route path="/education" element={<Education />} />
        </Routes>
      </Layout>
    </Router>
  )
}

export default App

