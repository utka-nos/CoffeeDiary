import './App.css';
import React from "react";
import {BrowserRouter, Routes, Link, Route} from 'react-router-dom'
import CoffeePage from "./CoffeePage";
import WelcomePage from "./WelcomePage";
import HeaderComponent from "./HeaderComponent";

function App() {
    return (
        <BrowserRouter>
            <HeaderComponent/>
            <Routes>
                <Route path="/dairy" element={<CoffeePage />}/>
                <Route path="/" element={<WelcomePage />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default App;
