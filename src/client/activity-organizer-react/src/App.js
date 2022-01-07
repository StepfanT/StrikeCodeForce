import { render } from "react-dom";
import React, { useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import './App.css';
import Home from './components/Home';
import Create from './components/Create';
import Browse from './components/Browse';
import View from './components/View';
import Points from './components/Points';
import Register from './components/Register';
import Login from './components/Login';
import Contact from './components/Contact';
import Detail from './components/Detail';
import TestApp from './TestApp';
import Dashboard from './components/Dashboard';
import NavBar from "./components/NavBar";


function App() {
  const [token, setToken] = useState();
  const [userIds, setUserId] = useState();

  const [userStatus, setUserStatus] = useState({
    user: null,
    login(username) {
      // Use previous state to preserve login and logout methods when updating user
      setUserStatus((prev) => ({ ...prev, user: username }));
    },
    logout() {
      // "token" must match the name used in "/Login" route
      localStorage.removeItem("token");
      setUserStatus((prev) => ({ ...prev, user: null }));
    },
  });

  const addUserId = userId => {
    setUserId([...userIds, userId])
  }

  return (
    <div className="wrapper">
      <BrowserRouter>
        <NavBar userStatus={userStatus} />
        <Routes>
          <Route path="/" element={<Dashboard />} />

          <Route path="/home" element={<Home />} />
          <Route path="/authenticate/register" element={<Register />} />

          <Route path="/login" element=
            {userStatus.user ? (
              <Navigate to="/" />
            ) : (
              <Login userStatus={userStatus} />
            )}
          />


          <Route path="/contact" element={<Contact />} />
          <Route path="/dashboard" element={<Dashboard addUserId={addUserId} />}
          />

          <Route path="/activity" element={<View />} />
          <Route path="/activity/browse" element={<Browse />} />
          <Route path="/activity/create" element={<Create />} />
          <Route path="/activity/points" element={<Points />} />
          <Route path="/activity/detail/:activityId" element={<Detail />} />


          <Route path="/test" element={<TestApp />} />

          <Route
            path="*"
            element={
              <main style={{ padding: "1rem" }}>
                <p>There's nothing here!</p>
              </main>}
          />
        </Routes>
      </BrowserRouter>
    </div>

  );
};
export default App;