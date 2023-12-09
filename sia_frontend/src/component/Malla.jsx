import {React, Fragment, useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Navbar from "./Navbar";
import {useFetch} from "./useFetch";
import 'bootstrap/dist/css/bootstrap.css';
import "../style/IngresoRamo"
import "../style/Malla.css"

export default function Malla() {
    const {data, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")


    const [ramos, setRamos] = useState([]);  // traer ramos que puede inscribir
    const [malla, setMalla] = useState([]);
    const [inscrito, setInscrito] = useState([]);
    const [reprobado, setReprobado] = useState([]);
    const [aprobado, setAprobado] = useState([]);


    // obtener los ramos que puede tomar
    useEffect(()=>{
        fetch("http://localhost:8080/ramo/getRamosTomar")
            .then(response=>response.json())
            .then(data=>setRamos(data.map(({id, nom_asig, cod_asig})=>({id, nom_asig, cod_asig}))))
    },[])


    // obtener los ramos de su carrera
    useEffect(()=>{
        fetch("http://localhost:8080/ramo/getRamos")
            .then(response=>response.json())
            .then(data=>setMalla(data.map(({id,cod_plan, nivel, nom_asig,cupos, cod_asig})=>({id,cod_plan, nivel, nom_asig,cupos, cod_asig}))))
    },[])


    // obtener los ramos inscritos
    useEffect(()=>{
        fetch("http://localhost:8080/nota/ramosInscritos")
            .then(response=>response.json())
            .then(data=>setInscrito(data.map(({id,cod_asig, nota})=>({id,cod_asig, nota}))))
    },[])

    // obtener los ramos reprobados
    useEffect(()=>{
        fetch("http://localhost:8080/nota/ramosReprobados")
            .then(response=>response.json())
            .then(data=>setReprobado(data.map(({id,cod_asig, nota})=>({id,cod_asig, nota}))))
    },[])

    // obtener los ramos aprobados
    useEffect(()=>{
        fetch("http://localhost:8080/nota/ramosAprobados")
            .then(response=>response.json())
            .then(data=>setAprobado(data.map(({id,cod_asig, nota})=>({id,cod_asig, nota}))))
    },[])


    const [showModal, setShowModal] = useState(false);
    const [modalData, setModalData] = useState({});
    const [modalNota, setModalNota] = useState(null);

    const handleCellClick = (cellData) => {
        setModalData(cellData);

        // Buscar la nota correspondiente en inscrito, reprobado o aprobado
        const notaInscrito = inscrito.find((i) => i.cod_asig === cellData.cod_asig)?.nota;
        const notaReprobado = reprobado.find((r) => r.cod_asig === cellData.cod_asig)?.nota;
        const notaAprobado = aprobado.find((a) => a.cod_asig === cellData.cod_asig)?.nota;

        // Establecer la nota correspondiente en el estado del modal
        setModalNota(notaAprobado || notaReprobado || notaInscrito);

        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setModalData({});
        setModalNota(null);
    };


    const renderTable = () => {
        const levels = Array.from(new Set(malla.map((asignatura) => asignatura.nivel)));
        const columns = [];

        for (const level of levels) {
            const columnData = malla
                .filter((asignatura) => asignatura.nivel === level)
                .map((asignatura) => ({
                    cod_asig: asignatura.cod_asig,
                    nom_asig: asignatura.nom_asig,
                }));

            columns.push(columnData);
        }

        const maxRowCount = columns.reduce((max, column) => Math.max(max, column.length), 0);

        return (
            <div className="table-container">
                <div className="legend">
                    <div className="legend-item" style={{ backgroundColor: 'rgba(255, 255, 161)' }}>
                        Inscrito
                    </div>
                    <div className="legend-item" style={{ backgroundColor: 'rgba(236, 119, 119)' }}>
                        Reprobado
                    </div>
                    <div className="legend-item" style={{ backgroundColor: 'rgb(117, 210, 117)' }}>
                        Aprobado
                    </div>
                    <div className="legend-item" style={{ backgroundColor: 'rgba(140, 174, 238)' }}>
                        Derecho
                    </div>
                    <div className="legend-item" style={{ backgroundColor: 'rgba(213, 213, 213)' }}>
                        Pendiente
                    </div>
                </div>
                <table className="table">
                    <thead>
                    <tr>
                        {levels.map((level) => (
                            <th key={level}>Nivel {level}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {[...Array(maxRowCount)].map((_, rowIndex) => (
                        <tr key={rowIndex}>
                            {columns.map((column, colIndex) => {
                                const cellData = column[rowIndex] || {};
                                const isEnrolled = inscrito.some((inscrito) => inscrito.cod_asig === cellData.cod_asig);
                                const isFailed = reprobado.some((reprobado) => reprobado.cod_asig === cellData.cod_asig);
                                const isPassed = aprobado.some((aprobado) => aprobado.cod_asig === cellData.cod_asig);
                                const isInRamos = ramos.some((ramo) => ramo.cod_asig === cellData.cod_asig);

                                const cellStyle = {
                                    backgroundColor:
                                        isEnrolled ? '#ffffa1' :
                                            isFailed ? '#ec7676' :
                                                isPassed ? '#75d275' :
                                                    isInRamos ? '#8caeee' :
                                                        '#d5d5d5',
                                };

                                return (
                                    <td key={colIndex} style={cellStyle}>
                                        {cellData && (
                                            <>
                                                <div>{cellData.cod_asig}</div>
                                                <div>{cellData.nom_asig}</div>
                                            </>
                                        )}
                                    </td>
                                );
                            })}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        );
    };





        return(
        <div>
            <Navbar />
            <br></br>

            <div style={{marginLeft: '0.5cm'}}>
                <div>
                    {!data && (
                        <h2>Espere a que se carguen todos los datos de la carrera</h2>
                    )}


                    {data && (
                        <h4>Malla Curricular: {data.nom_carr} </h4>
                    )}
                    {renderTable()}
                </div>




            </div>


        </div>


    );
}
