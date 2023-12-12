import React, {useEffect, useState} from 'react';
import Navbar from "./Navbar";
import "../style/IngresoHorario.css"
import Swal from 'sweetalert2';
import Tabla from './Tabla';
import horarioService from "../service/HorarioService";
import {useNavigate} from "react-router-dom";
import styled from 'styled-components';

const LegendContainer = styled.div`
  margin: 90px;
  position: fixed;
  bottom: 10px;
  right: 110px;
  display: flex;
`;

const LegendItem = styled.div`
  display: flex;
  align-items: center;
  margin-right: 10px;
`;

const LegendColor = styled.div`
  width: 20px;
  height: 20px;
  margin-right: 10px;
  background-color: ${({ color }) => color};
`;




function CheckboxList(options) {
    return (
        <li>
            <label
                htmlFor={options.id}
                className={`checkbox-label ${options.checked ? 'selected' : ''}`}
            >
                {options.tileInfo}
                <input
                    type="checkbox"
                    id={options.id}
                    value={options.value}
                    checked={options.checked}
                    onChange={options.onChange}
                />
            </label>
        </li>
    );
}

export default function InscriptionHorario() {

    const navigate = useNavigate();
    const recargar = () => {
        navigate("/ingresar-horario");
    };

    const initialState = {
        cod_asig: "",
        codigo_carr: "",
        seccion: "",
        dia: "",
        modulo: "",
    };

    const [ramos, setRamos] = useState([]);
    const [carreras, setCarreras] = useState([]);  // traer ramos que puede inscribir
    const [input, setInput] = useState(initialState);


    const [selectedDia, setSelectedDia] = useState(null);
    const [selectedModulo, setSelectedModulo] = useState(null);
    const [horarioIngresado, setHorarioIngresado] = useState(false);


    // obtener todas las carreras
    useEffect(()=>{
        fetch("http://localhost:8080/carrera/getAll")
            .then(response=>response.json())
            .then(data=>setCarreras(data.map(({id, nom_carr, codigo_carr})=>({id, nom_carr, codigo_carr}))))
    },[])


    const diasSemana = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
    const modulos = ['Modulo 1', 'Modulo 2', 'Modulo 3', 'Modulo 4', 'Modulo 5', 'Modulo 6'];
    const secciones = ['A-1', 'B-2', 'C-3'];

    const changeCod_asigHandler = event => {
        setInput({...input, cod_asig: event.target.value});
        console.log(input.cod_asig)
    };

    const handleDiaChange = (value) => {
        setInput({ ...input, dia: value || "" });
        setSelectedDia(value);
    };

    const handleModuloChange = (value) => {
        setInput({ ...input, modulo: value || "" });
        setSelectedModulo(value);
    };

    const handleSeccionChange = (value) => {
        setInput({ ...input, seccion: value || "" });
    };

    const obtenerRamos = async (event) => {
        setInput({...input, codigo_carr: event.target.value});
        const codigoCarrera = event.target.value;
        console.log("codio: " + codigoCarrera);

        // obtener los ramos de una carrera
        if (codigoCarrera) {
            try {
                const response = await fetch(`http://localhost:8080/ramo/getRamos/${codigoCarrera}`);
                const data = await response.json();
                setRamos(data);
            } catch (error) {
                console.error('Error al obtener ramos:', error);
            }
        } else {
            // Limpiar la lista de ramos si no hay una carrera seleccionada
            setRamos([]);
        }

        setHorarioIngresado(true);
    };


    const mostrarError = (campoFaltante) => {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: `Por favor, completa el campo ${campoFaltante}`,
            className: 'mi-alerta-error', // Agrega una clase personalizada
        });
    };




    const crearHorario = (event) => {
        console.log(input.cod_asig)
        console.log(input.dia)
        console.log(input.codigo_carr)
        console.log(input.modulo)
        console.log(input.seccion)

        if (!input.codigo_carr) {
            mostrarError('Código de Carrera');
            return;
        }

        if (!input.cod_asig) {
            mostrarError('Código de asignatura');
            return;
        }

        if (!input.dia) {
            mostrarError('Día');
            return;
        }

        if (!input.modulo) {
            mostrarError('Módulo');
            return;
        }

        if (!input.seccion) {
            mostrarError('Sección');
            return;
        }

        const mensaje = `¿Desea inscribir la asignatura elegida el día ${input.dia} modulo ${input.modulo}`

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
                // El usuario hizo clic en "Confirmar"
                horarioService.createHorario(input);

                Swal.fire({
                    title: "Horario creado exitosamente",
                    timer: 2000,
                    icon: "success",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },
                });

                recargar();

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
            }
        });


    }




    return(
      <div>
          <Navbar></Navbar>
          <br></br>
          <div style={{marginLeft: '0.5cm'}}>
              <h2>Inscribir horario a una asignatura</h2>
              <div className="selectCarrera-container" >
                  <div>
                      <label htmlFor="carreraSelect">Seleccione una carrera:</label>
                      <select
                          className="agregar"
                          name="codigo_carr"
                          required
                          value={input.codigo_carr}
                          onChange={obtenerRamos}
                      >
                          <option value={""} disabled>Seleccione Carrera</option>
                          {carreras.map((c) => (
                              <option key={c.id} value={c.codigo_carr}>
                                  {c.nom_carr}
                              </option>
                          ))}
                      </select>
                  </div>

                  <div className="selectRamo-container">
                      <label htmlFor="ramoSelect">Seleccione una asignatura:</label>
                      <select
                          className="agregar"
                          name="cod_asig"
                          required
                          value={input.cod_asig}
                          onChange={changeCod_asigHandler}
                      >
                          <option value={""} disabled>Seleccione Asignatura</option>
                          {ramos.map((r) => (
                              <option key={r.id} value={r.cod_asig}>
                                  Nivel {r.nivel}:  {r.nom_asig}
                              </option>
                          ))}
                      </select>
                  </div>
              </div>


              <div className="checkbox-list">
                  <label htmlFor="diaSelect">Seleccione un dia:</label>
                  <ul className="grid">
                      {diasSemana.map((dia, index) => (
                          <CheckboxList
                              key={index}
                              id={`checkbox-${index + 1}`}
                              tileInfo={dia}
                              value={(index + 1).toString()}
                              checked={input.dia === (index + 1).toString()}
                              onChange={() => handleDiaChange((index + 1).toString())}
                          />
                      ))}
                  </ul>
              </div>

              <div className="checkbox-list">
                  <label htmlFor="moduloSelect">Seleccione un módulo:</label>
                  <ul className="grid">
                      {modulos.map((modulo, index) => (
                          <CheckboxList
                              key={index}
                              id={`checkbox-modulo-${index + 1}`}
                              tileInfo={modulo}
                              value={(index + 1).toString()}
                              checked={input.modulo === (index + 1).toString()}
                              onChange={() => handleModuloChange((index + 1).toString())}
                          />
                      ))}
                  </ul>
              </div>

              <div className="checkbox-list">
                  <label htmlFor="seccionSelect">Seleccione la sección de la asignatura:</label>
                  <ul className="grid">
                      {secciones.map((seccion, index) => (
                          <CheckboxList
                              key={index}
                              id={`checkbox-seccion-${index + 1}`}
                              tileInfo={`Sección ${seccion}`}
                              value={seccion}
                              checked={input.seccion === seccion}
                              onChange={() => handleSeccionChange(seccion)}
                          />
                      ))}
                  </ul>
              </div>

              <div className="tabla-container">
                  <Tabla selectedDia={selectedDia} selectedModulo={selectedModulo} input={input} horarioIngresado={horarioIngresado} />
              </div>

              {/* Leyenda para horario ya inscrito */}
              {
                  <LegendContainer>
                      <LegendItem>
                          <LegendColor color="#23394d" />
                          <div>Horario ya inscrito</div>
                      </LegendItem>

                      <LegendItem>
                          <LegendColor color="#e0702a" />
                          <div>Horario por inscribir</div>
                      </LegendItem>

                  </LegendContainer>
              }

              <br></br>
              <button className="submit" onClick={crearHorario}>Ingresar Horario</button>
              <br></br>
              <br></br>
              <h5>* Esta sección es solo para un académico. Un estudiante no</h5>
              <h5>debería de poder acceder aquí normalmente.</h5>

          </div>




      </div>


    );
}