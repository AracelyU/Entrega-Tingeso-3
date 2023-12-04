import React, { Suspense } from 'react';
import Navbar from "./Navbar";
import { fetchData } from "./fetchData"
import InscriptionComponent from "./InscriptionComponent";
import {useFetch} from "./useFetch";

export default function PerfilEstudiante() {
    const {data, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")

    return(
        <>
            <Navbar />
            <h1>Perfil Estudiante</h1>
            <ul>
                {error && <li>Error: {error}</li>}
                {loading && <li>Cargando...</li>}
                {data && (
                    <>
                        <li>RUT: {data.rut}</li>
                        <li>Nombre: {data.nombre}</li>
                        <li>Apellido: {data.apellido}</li>
                        <li>Email: {data.email}</li>
                        <li>Nivel: {data.nivel}</li>
                    </>

                )}
            </ul>






        </>
    )

}
