import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../style/App.css';
import Navbar from "./Navbar";
import { Table, Container } from 'react-bootstrap';

// componente para agregar un horario a una asignatura
export default function Horario() {

    const [horariosInscribir, setHorariosInscribir] = useState([]);
    const [ramosInscribir, setRamosInscribir] = useState([]);

    useEffect(()=>{
        fetch("http://localhost:8080/ramo/getHorariosInscribir")
            .then(response=>response.json())
            .then(data=>setHorariosInscribir(data.map(({id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, cod_asig, seccion})=>({id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, cod_asig, seccion}))))
    },[])

    useEffect(()=>{
        fetch("http://localhost:8080/nota/ramosInscritos")
            .then(response=>response.json())
            .then(data=>setRamosInscribir(data.map(({id, cod_asig, seccion})=>({id, cod_asig, seccion}))))
    },[])

    const getCellInfo = (horario, modulo, dia) => {

        console.log("MODULO" + modulo);
        console.log("Dia" + dia);

        if (horario && horario[`modulo${modulo}`]) {
            const index = horario[`modulo${modulo}`][dia];
            if (index === 1) {
                const asignatura = horariosInscribir.find((asig) => asig.id === horario.id);
                return `${asignatura.cod_asig} - ${asignatura.seccion}`;
            }
        }
        return '';
    };

    return (
        <div>
            <Navbar />
            <h1>En la sección de MI HORARIO</h1>

            <Container fluid>
                <div className="row">
                    {/* Lado Izquierdo */}
                    <div className="col-md-6">
                        <h2>Horarios a inscribir</h2>
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>Modulos</th>
                                <th>Lunes</th>
                                <th>Martes</th>
                                <th>Miércoles</th>
                                <th>Jueves</th>
                                <th>Viernes</th>
                                <th>Sábado</th>
                            </tr>
                            </thead>
                            <tbody>
                            {Array.from({ length: 6 }, (_, modulo) => (
                                <tr key={modulo}>
                                    <td>Modulo {modulo + 1}</td>
                                    {Array.from({ length: 6 }, (_, dia) => (
                                        <td key={dia} style={{ backgroundColor: getCellInfo(horariosInscribir[modulo], modulo, dia) ? 'lightgray' : 'white' }}>
                                            {getCellInfo(horariosInscribir[modulo], modulo, dia)}
                                        </td>
                                    ))}
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </div>

                    {/* Lado Derecho */}
                    <div className="col-md-6">
                        <h2>Ramos Inscritos</h2>
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>Código Asignatura</th>
                                <th>Sección</th>
                            </tr>
                            </thead>
                            <tbody>
                            {ramosInscribir.map((ramo) => (
                                <tr key={ramo.id}>
                                    <td>{ramo.cod_asig}</td>
                                    <td>{ramo.seccion}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </div>
                </div>
            </Container>
        </div>
    );
}