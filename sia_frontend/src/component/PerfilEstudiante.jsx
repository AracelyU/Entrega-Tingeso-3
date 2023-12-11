import React, {Suspense, useEffect, useState} from 'react';
import Navbar from "./Navbar";
import { fetchData } from "./fetchData"
import Malla from "./Malla";
import {useFetch} from "./useFetch";
import "../style/PerfilEstudiante.css"

export default function PerfilEstudiante() {
    const {data, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")

    const [nroRamos, setNroRamos] = useState(null);

    useEffect(() => {
        const fetchNroRamos = async () => {
            try {
                const response = await fetch("http://localhost:8080/nota/nroRamosInscritos");
                const data = await response.json();
                setNroRamos(data);
            } catch (error) {
                console.error('Error al obtener el número de ramos:', error);
                // Manejar el error según sea necesario
            }
        };

        fetchNroRamos();
    }, []);

    return (
        <>
            <Navbar />
            <br></br>
            <h2 style={{marginLeft: '0.5cm'}}>Bienvenido a SIA-FING V.1.2</h2>
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

                            {nroRamos < 3 && (
                                <li>Situación: IRREGULAR (Tiene menos de 3 asignaturas inscritas)</li>
                            )}
                            {nroRamos > 2 && (
                                <li>Situación: REGULAR</li>
                            )}
                        </ul>
                    )}
                </div>
            </div>
            <br></br>
            <h5 style={{marginLeft: '0.5cm'}}>* Se recomienda ir a la sección Ayuda si no sabes como comenzar</h5>


        </>
    );
}
