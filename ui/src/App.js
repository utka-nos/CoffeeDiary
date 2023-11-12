import './App.css';
import { useState } from 'react';
import { Header } from './Header.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { HomePage } from './pages/HomePage.js';
import { LoginPage } from './pages/LoginPage.js';
import { AuthorizePage } from './pages/AuthorizePage.js'
import { AdminPage } from './pages/AdminPage.js'

function App() {
  const [accessToken, setAccessToken] = useState("");

  return (
    <div className="App">
      <BrowserRouter>
        <Header accessToken={accessToken} />
        <Routes>
          <Route path="/home" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/authorize" element={<AuthorizePage setAccessToken={setAccessToken} />} />
          <Route path="/admin" element={<AdminPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
