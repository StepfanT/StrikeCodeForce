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
import AuthContext from './context/AuthContext'
import Delete from "./components/Delete";
import Pagination from "./components/Paginate";

let PageSize = 10;

function App() {
  const [token, setToken] = useState();
  const [userIds, setUserId] = useState();

  const [currentPage, setCurrentPage] = useState(1);

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * PageSize;
    const lastPageIndex = firstPageIndex + PageSize;
    return data.slice(firstPageIndex, lastPageIndex);
  }, [currentPage]);

  const [userStatus, setUserStatus] = useState({
    user: null,
    login(userDetail) {
      // Use previous state to preserve login and logout methods when updating user
      setUserStatus((prev) => ({ ...prev, user: userDetail }));
      console.log(this.user);
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
      <>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>FIRST NAME</th>
              <th>LAST NAME</th>
              <th>EMAIL</th>
              <th>PHONE</th>
            </tr>
          </thead>
          <tbody>
            {currentTableData.map(item => {
              return (
                <tr>
                  <td>{item.id}</td>
                  <td>{item.first_name}</td>
                  <td>{item.last_name}</td>
                  <td>{item.email}</td>
                  <td>{item.phone}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <Pagination
          className="pagination-bar"
          currentPage={currentPage}
          totalCount={data.length}
          pageSize={PageSize}
          onPageChange={page => setCurrentPage(page)}
        />
      </>
      <BrowserRouter>
        <AuthContext.Provider value={[userStatus, setUserStatus]}>
          <NavBar userStatus={userStatus} />
          <Routes>
            <Route path="/" element={<Dashboard />} />

            <Route path="/home" element={<Home />} />
            <Route path="/authenticate/register" element={<Register />} />

            <Route path="/login" element=
              {userStatus.user ? (
                <Navigate to="/" />
              ) : (
                <Login />
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
            <Route path="/activity/delete/:activityId" element={<Delete />} />

            <Route path="/test" element={<TestApp />} />

            <Route
              path="*"
              element={
                <main style={{ padding: "1rem" }}>
                  <p>There's nothing here!</p>
                </main>}
            />
          </Routes>
        </AuthContext.Provider>
      </BrowserRouter>
    </div>

  );
};
export default App;