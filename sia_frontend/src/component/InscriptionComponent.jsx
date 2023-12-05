import {React, useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Navbar from "./Navbar";
import {useFetch} from "./useFetch";
import {Button, Form} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';

export default function InscriptionComponent() {
    const {data, loading, error} = useFetch("http://localhost:8080/estudiantePrincipal/getEstudiante")

    const initialState = {
        cod_asig: "",
    };

    const [ramos, setRamos] = useState([]);  // traer ramos que puede inscribir
    const [input, setInput] = useState(initialState);

    useEffect(()=>{
        fetch("http://localhost:8080/ramo/getRamosTomar")
            .then(response=>response.json())
            .then(data=>setRamos(data.map(({id, nom_asig, cod_asig})=>({id, nom_asig, cod_asig}))))
    },[])

    const changeCod_AsigHandler = event => {
        setInput({...input, cod_asig: event.target.value});
    };

    const incribirRamo = (event) => {
        console.log(input.cod_asig);
    };

    return(
        <div>
            <Navbar />
            <h2>Inscripción de Asignaturas (2024/01)</h2>

            <ul>
                {error && <li>Error: {error}</li>}
                {loading && <li>Cargando...</li>}
                {data && (
                    <li>Carrera: {data.nom_carr}</li>
                )}
            </ul>

            <Form>

                <Form.Group>
                    <p>Seleccione una asignatura</p>
                    <select className="agregar" name="cod_asig" required value={input.cod_asig} onChange={changeCod_AsigHandler}>
                        <option value={""}>Seleccione Asignatura</option>
                        {ramos.map((r) => (
                            <option key={r.id} value={r.cod_asig}>
                                {r.nom_asig}
                            </option>
                        ))}
                    </select>
                </Form.Group>
                <Button className="btn-check" onClick={incribirRamo}> Incribir Ramo </Button>
            </Form>


        </div>


    );
}
