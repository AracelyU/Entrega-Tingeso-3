import React, { Suspense } from 'react';
import Navbar from "./Navbar";
import { fetchData } from "./fetchData"
import Malla from "./Malla";
import {useFetch} from "./useFetch";
import "../style/PerfilEstudiante.css"

export default function PerfilEstudiante() {
    const {data, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")

    return (
        <>
            <Navbar />
            <br></br>
            <h2 style={{marginLeft: '0.5cm'}}>Bienvenido a SIA-FING</h2>
            <h4 style={{marginLeft: '0.5cm'}}>Información básica</h4>
            <div id="perfil-estudiante-container">
                <div id="perfil-estudiante-info">
                    <h1>Perfil Estudiante</h1>
                    {error && <p>Error: {error}</p>}
                    {loading && <p>Cargando Estudiante...</p>}
                    {data && (
                        <ul>
                            <li>RUT: {data.rut}</li>
                            <li>Nombre: {data.nombre}</li>
                            <li>Apellido: {data.apellido}</li>
                            <li>Email: {data.email}</li>
                            <li>Nivel: {data.nivel}</li>
                        </ul>
                    )}
                </div>
            </div>
        </>
    );
}
