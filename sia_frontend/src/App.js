import {BrowserRouter as Router} from "react-router-dom";
import './App.css';
import Menu from "./component/MenuComponent";
import React from "react";

function App() {
  return (
      <Router>
          <div className="flex">
              <Menu />

          </div>
      </Router>
  );
}


export default App;
