import { BrowserRouter, Route, Routes } from "react-router-dom";
import './style/App.css';
import React from "react";
import PerfilEstudiante from "./component/PerfilEstudiante";
import Horario from "./component/HorarioComponent";
import Inscripcion from "./component/InscriptionRamo";
import Ayuda from "./component/AyudaComponent";
import InscripcionHorario from "./component/InscriptionHorario";
import Malla from "./component/Malla";

function App() {
  return (
      <>

          <BrowserRouter>
              <Routes>
                  <Route path= "/" element={< PerfilEstudiante />} />
                  <Route path="/malla" element={< Malla/>} />
                  <Route path="/horario-estudiante" element={< Horario/>} />
                  <Route path="/inscripcion-estudiante" element={< Inscripcion/>} />
                  <Route path="/ingresar-horario" element={< InscripcionHorario />} />
                  <Route path="/ayuda" element={< Ayuda/>} />
              </Routes>
          </BrowserRouter>
      </>

  );
}


export default App;
