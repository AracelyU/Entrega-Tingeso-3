import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../style/IngresoRamo.css';
import Navbar from "./Navbar";
import {useFetch} from "./useFetch";
import Swal from 'sweetalert2';
import ramoService from "../service/RamoService";
import {Button, Modal} from "react-bootstrap";
import {useNavigate} from "react-router-dom";



// componente para agregar un ramo
export default function InscriptionRamo() {

    const { data: ramosData, loading: ramosLoading, error: ramosError } = useFetch("http://localhost:8080/ramo/getRamosTomar");

    const initialState = {
        cod_asig: "",
        seccion: "",
        nom_asig: "",
        textoHorario: "", // Agregado
    };

    const [showModal, setShowModal] = useState(false);

    const handleCloseModal = () => setShowModal(false);
    const handleShowModal = () => setShowModal(true);

    const navigate = useNavigate();
    const recargar = () => {
        navigate("/inscripcion-estudiante");
    };

    const [input, setInput] = useState(initialState);
    const [cuposData, setCuposData] = useState([]);
    const [selectedPlan, setSelectedPlan] = useState(null);
    const [nroRamos, setNroRamos] = useState(null);
    const [senalCupos, setSenalCupos] = useState(false);

    useEffect(() => {
        // Obtener cupos para cada ramo y sección
        const fetchData = async () => {
            const cuposArray = [];

            for (const ramo of ramosData) {
                for (const seccion of ['A-1', 'B-2', 'C-3']) {
                    try {
                        //console.log("cod_asig: " + ramo.cod_asig + "seccion: " + seccion)
                        const cuposResponse = await fetch(`http://localhost:8080/ramo/getCupos/${ramo.cod_asig}/${seccion}`);
                        const cuposData = await cuposResponse.json();

                        cuposArray.push({
                            cod_asig: ramo.cod_asig,
                            nivel: ramo.nivel,
                            seccion: seccion,
                            cupos: cuposData,
                        });

                    } catch (error) {
                        console.error('Error fetching cupos', error);
                    }
                }
            }
            setCuposData(cuposArray);
        };

        if (senalCupos) {
            fetchData();
        }

    }, [ramosData, ramosLoading, selectedPlan]);


    // obtener el número de ramos
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


    const [horariosData, setHorariosData] = useState([]);
    const [textoHorario, setTextoHorario] = useState("");

    const handleMostrarSecciones = (ramo) => {
        setSelectedPlan(null);
        setSelectedPlan(ramo);
        setShowModal(true);
        setInput((prevInput) => ({ ...prevInput, cod_asig: ramo.cod_asig, nom_asig: ramo.nom_asig}));

        const horarioSeleccionado = horariosData.find(
            (horario) => horario.cod_asig === ramo.cod_asig && horario.seccion === input.seccion
        );

        setTextoHorario(horarioSeleccionado?.texto || "");
        setSenalCupos(true);
    };

    const obtenerCupos = async (event) => {
        // obtener los ramos de una carrera
        if (event.cod_asig) {
            try {
                const response = await fetch(`http://localhost:8080/horario/getAll`);
                const horariosData = await response.json();
                setHorariosData(horariosData);
            } catch (error) {
                console.error('Error fetching horarios', error);
            }

        } else {
            // Limpiar la lista de ramos si no hay una carrera seleccionada
            setHorariosData([]);
        }
    };


    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/horario/getAll`);
                const horariosData = await response.json();
                setHorariosData(horariosData);
            } catch (error) {
                console.error('Error fetching horarios', error);
            }
        };

        fetchData();
    }, []);


    const handleInscribirRamo = (cod_asig, seccion, nom_asig) => {
        // console.log(`Inscribir ramo: ${cod_asig} - ${seccion} - ${nom_asig}`);

        // Colocar la sección en input.seccion
        setInput((prevInput) => ({ ...prevInput, seccion }));

        const mensaje = `¿Desea inscribirse a la asignatura ${input.nom_asig} sección ${input.seccion}?`;

        Swal.fire({
            title: mensaje,
            text: "No podra desincribir la asignatura más tarde",
            icon: "question",
            showDenyButton: true,
            confirmButtonText: "Confirmar",
            confirmButtonColor: "rgb(68, 194, 68)",
            denyButtonText: "Cancelar",
            denyButtonColor: "rgb(190, 54, 54)",

        }).then((result) => {

            if (result.isConfirmed) {
                ramoService.inscribirRamo(cod_asig, seccion);

                const mensaje = `Se ha incrito ramo ${nom_asig} sección ${seccion}`
                Swal.fire({
                    title: mensaje,
                    timer: 2000,
                    icon: "success",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },
                })

                setShowModal(false);


            } else if (result.isDenied) {

                Swal.fire({
                    title: "Operación cancelada",
                    timer: 2000,
                    icon: "error",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },
                });

                recargar();

            }
        });

    };

    const getTextoHorario = (codAsig, seccion) => {
        const horarioSeleccionado = horariosData.find(
                (horario) => horario.cod_asig === codAsig && horario.seccion === seccion
        );

        return horarioSeleccionado?.texto || "sin horario registrado";
    };

    return (
        <div>
            <Navbar />
            <br></br>
            {nroRamos < 6 && (
                <div className="split-container">
                    {/* Lado Izquierdo */}
                    <div className="leftSection">
                        <h2>Inscripción de asignaturas 2024/01</h2>
                        {ramosError && <li>Error: {ramosError}</li>}
                        {ramosLoading && <h2>Cargando Información de Asignaturas...</h2>}
                        {ramosData && (
                            <div>
                                {ramosData.map((plan, index) => (
                                    index % 2 === 0 && ( // Renderizar solo las asignaturas en índices pares en el lado izquierdo
                                        <div
                                            key={plan.cod_asig}
                                            className="planInfo"
                                            style={{
                                                display: 'grid',
                                                gridTemplateColumns: 'auto auto',
                                                alignItems: 'center',
                                                marginBottom: '20px',
                                            }}
                                        >
                                            <div className="infoContainer">
                                                <div>Codigo: {plan.cod_asig}</div>
                                                <div>Nombre asignatura: {plan.nom_asig}</div>
                                                <div>Nivel: {plan.nivel}</div>
                                            </div>
                                            <div className="buttonContainer">
                                                <button onClick={() => handleMostrarSecciones(plan)}>Mostrar Secciones</button>
                                            </div>
                                        </div>
                                    )
                                ))}
                            </div>
                        )}
                    </div>


                    {/* Lado Derecho */}
                    <div className="rightSection">
                        <h2>
                            Limite de asignaturas por inscribir: {6 - nroRamos}
                        </h2>
                        {ramosData && (
                            <div>
                                {ramosData.map((plan, index) => (
                                    index % 2 !== 0 && ( // Renderizar solo las asignaturas en índices impares en el lado derecho
                                        <div
                                            key={plan.cod_asig}
                                            className="planInfo"
                                            style={{
                                                display: 'white',
                                                gridTemplateColumns: 'auto auto',
                                                alignItems: 'center',
                                                marginBottom: '20px',
                                            }}
                                        >
                                            <div className="infoContainer">
                                                <div>Codigo: {plan.cod_asig}</div>
                                                <div>Nombre asignatura: {plan.nom_asig}</div>
                                                <div>Nivel: {plan.nivel}</div>
                                            </div>
                                            <div className="buttonContainer">
                                                <button onClick={() => handleMostrarSecciones(plan)}>Mostrar Secciones</button>
                                            </div>
                                        </div>
                                    )
                                ))}
                            </div>
                        )}
                    </div>
                </div>

            )}
            {nroRamos >= 6 && (
                <h2> Has alcanzado el limite de asignaturas a la que puedes inscribir.
                </h2>

            )}

            {/* Modal para la información de la sección */}
            <Modal show={showModal} onHide={handleCloseModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Información de Sección</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {/* Contenido del modal, puedes mostrar aquí la información de la sección seleccionada */}
                    {!selectedPlan && <h5>* Cargando Secciones...</h5>}
                    {selectedPlan && (
                        <div style={{ marginLeft: '0.5cm' }}>
                            <h3>Información de Secciones</h3>
                            <div className="seccionInfo">
                                <h5>{input.nom_asig}</h5>
                                {['A-1', 'B-2', 'C-3'].map((seccion) => (
                                    <div key={seccion}>
                                        <div>{`Sección: ${seccion}`}</div>
                                        <div>{`Cupos disponibles: 50`}</div>
                                        <div>{`Horario: ${getTextoHorario(input.cod_asig, seccion)}`}</div>
                                        <button onClick={() => handleInscribirRamo(input.cod_asig, seccion, input.nom_asig)}>
                                            Inscribir en esta sección
                                        </button>
                                    </div>
                                ))}
                            </div>
                        </div>
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>
                        Cerrar
                    </Button>
                </Modal.Footer>
            </Modal>





        </div>
    );
}