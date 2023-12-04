import React, { Suspense } from 'react';
import Navbar from "./Navbar";
import { fetchData } from "./fetchData"
import InscriptionComponent from "./InscriptionComponent";
import {useFetch} from "./useFetch";

export default function inscriptionHorario() {

    return(
      <>
        <Navbar></Navbar>
        <h2>Sección de ingresar horario</h2>

      </>


    );
}