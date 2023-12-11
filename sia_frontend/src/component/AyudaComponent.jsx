import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../style/App.css';
import Navbar from "./Navbar";
import "../style/Ayuda.css"

export default function AyudaComponent() {

    return(

        <div>
            <Navbar />
            <br></br>
            <div className="container" style={{marginLeft: '0.5cm'}}>

                <h2>Sección de ayuda</h2>
                <br></br>
                <h4>Proposito</h4>
                <h5>* SIA-FING es un programa para que el estudiante pueda ver su información, ingresar asignaturas y ver sus horarios.
                    También tiene habilitado una sección para que el academico ingrese los horarios de las asignaturas. </h5>
                <h5>* Aún está en un periodo de pruebas. Si detecta un problema en alguna de sus solicitudes puede enviarnos
                    un correo a siafing@gmail.com </h5>
                <br></br>
                <h3>Aclaraciones</h3>
                <h5>* Esta plataforma aún no tiene control sobre los topes de horarios en caso de que ocurran, por lo que se
                recomienda no inscribirse a asignaturas causen tope</h5>
                <h5>* Esta plataforma aún no muestra el horario de una asignatura </h5>


                <br></br>
                <h3>Recomendaciones</h3>
                <h5>* Para los nuevos usuarios de está aplicación, se recomienda ir a la sección "Malla", elegir una
                asignatura al cuál tiene derecho a dar y desde la sección "Ingresar horario" asignar una o dos horarios
                a esa asignatura a una sección de su preferencia</h5>
                <h5>* Tras eso puede ir a la sección "Inscribir asignatura (2024/01) a inscribir esa asignatura y sección
                    con horario asignado. Finalmente, verificar que el ramo está inscrito, esto lo puede ver desde
                    la sección "Malla" o "Mi horario"</h5>






            </div>

        </div>


    );

}
