import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import React from "react";
import Navbar from "./component/Navbar"
import PerfilEstudiante from "./component/PerfilEstudiante";
import Horario from "./component/HorarioComponent";
import Inscripcion from "./component/InscriptionComponent";
import Ayuda from "./component/AyudaComponent";

function App() {
  return (
      <>

          <BrowserRouter>
              <Routes>
                  <Route path= "/" element={< PerfilEstudiante />} />
                  <Route path="/horario-estudiante" element={< Horario/>} />
                  <Route path="/inscripcion-estudiante" element={< Inscripcion/>} />
                  <Route path="/ayuda" element={< Ayuda/>} />
              </Routes>
          </BrowserRouter>
      </>

  );
}


export default App;
