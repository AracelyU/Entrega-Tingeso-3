import {BrowserRouter as Router} from "react-router-dom";
import './App.css';
import Menu from "./component/MenuComponent";
import Cabeza from "./component/CabezaComponent"
import React from "react";

function App() {
  return (
      <Router>
          <div className="flex">
              <Cabeza />
              <Menu />

          </div>
      </Router>
  );
}


export default App;
