import { render } from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import App from "./App";
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

const rootElement = document.getElementById("root");
render(
  <BrowserRouter>

    <Routes>
      <Route path="/" element={<App />} />

      <Route path="/home" element={<Home />} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/contact" element={<Contact />} />
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
  </BrowserRouter>,
  rootElement
);

/*
    <Route path="/activity" element={<View />} >
        <Route path=":browse" element={<Browse />} />
        <Route path=":create" element={<Create />} />
        <Route path=":points" element={<Points />} />
      </Route>

      <Route path="/activity" element={<View />} />
      <Route path="/activity/browse" element={<Browse />} />
      <Route path="/activity/create" element={<Create />} />
      <Route path="/activity/points" element={<Points />} />


     <Route path="/table" element={<Table />} />

*/