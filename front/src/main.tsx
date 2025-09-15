import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { SidebarProvider, SidebarInset } from '@/components/ui/sidebar'
import { AppSidebar } from '@/components/app-sidebar'
import './index.css'
import Signup from './pages/signup/index'
import Signin from './pages/signin/index'
import Todo from './pages/todo/index'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/signup" element={<Signup />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/todo" element={
          <SidebarProvider>
            <div className="flex w-screen h-screen">
              <aside className='w-80 flex-shrink'>
                <AppSidebar />
              </aside>
              <main className="flex-1 ">
                <Todo />
              </main>
            </div>
          </SidebarProvider>
        } />
        <Route path="/" element={<Navigate to="/signin" replace />} />
      </Routes>
    </Router>
  )
}


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
