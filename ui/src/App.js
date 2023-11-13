import './App.css';
import { Header } from './Header.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { HomePage } from './pages/HomePage.js';
import { LoginPage } from './pages/LoginPage.js';
import { AuthorizePage } from './pages/AuthorizePage.js'
import { AdminPage } from './pages/AdminPage.js'
import { ProfilePage } from './pages/ProfilePage.js'
import { useState } from 'react';

function App() {

  const [authorities, setAuthorities] = useState([]);

  return (
    <div className="App">
      <BrowserRouter>
        <Header setAuthorities={setAuthorities} authorities={authorities} />
        <Routes>
          <Route path="/home" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/authorize" element={<AuthorizePage />} />
          <Route path="/admin" element={<AdminPage />} />
          <Route path="/profile" element={<ProfilePage authorities={authorities} />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
