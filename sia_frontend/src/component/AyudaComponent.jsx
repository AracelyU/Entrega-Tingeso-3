import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../style/App.css';
import Navbar from "./Navbar";

export default function AyudaComponent() {

    return(

        <div>
            <Navbar />
            <br></br>
            <div style={{marginLeft: '0.5cm'}}>

                <h2>Sección de ayuda</h2>
                <h3>Proposito</h3>
                <p>SIA-FING es un programa para que el estudiante pueda ingresar y ver sus horarios de su carrera.
                También tiene habilitado una sección para que el academico ingrese los horarios de las asignaturas.</p>




            </div>

        </div>


    );

}
