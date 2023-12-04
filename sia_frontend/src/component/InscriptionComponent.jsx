import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';

import { useState } from 'react';
import Navbar from "./Navbar";
import {useFetch} from "./useFetch";


export default function InscriptionComponent() {

    const {estudiante, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")
    const {ramosPosibles, loading2, error2} = useFetch("http://localhost:8080/ramo/getRamosTomar")


    return(
        <div>
            <Navbar />
            <h2>Sección de inscripción de asignatura</h2>

            <>
                {loading2 && <p>Cargando...</p>}
                {error2 && <p>Error: {error2}</p>}
                {ramosPosibles && (
                    <select>
                        <option>Seleccione</option>
                        {ramosPosibles.map((ramo, index) => (
                            <option key={index} value={ramo.id}>
                                {ramo.nombre}
                            </option>
                        ))}
                    </select>
                )}
            </>


        </div>


    );
}
