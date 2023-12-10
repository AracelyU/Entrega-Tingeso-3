import React, {useEffect, useState} from 'react';
import '../style/Tabla.css';

const diasSemana = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
const modulos = ['Modulo 1  (8:15 - 9:30)', 'Modulo 2  (9:50 - 11:10)', 'Modulo 3  (11:25 - 12:45)', 'Modulo 4  (13:45 - 15:05)', 'Modulo 5  (15:20 - 16:40)', 'Modulo 6  (16:55 - 18:15)'];

const Tabla = ({ selectedDia, selectedModulo, input }) => {
    const [horario, setHorario] = useState(null);

    console.log(input.cod_asig);
    console.log(input.seccion);

    useEffect(() => {
        const fetchData = async () => {
            try {
                if (input.cod_asig && input.seccion) {
                    const horarioResponse = await fetch(`http://localhost:8080/horario/getHorarios/${input.cod_asig}/${input.seccion}`);

                    if (horarioResponse.ok) {
                        const horarioData = await horarioResponse.text();

                        // Verificar si la cadena JSON no está vacía
                        if (horarioData.trim() !== "") {
                            try {
                                const parsedData = JSON.parse(horarioData);

                                if (parsedData && Object.keys(parsedData).length > 0) {
                                    setHorario(parsedData);
                                } else {
                                    console.warn('La respuesta de horario está vacía o no contiene datos');
                                    // Puedes tomar alguna acción adicional aquí, como mostrar un mensaje al usuario.
                                }
                            } catch (error) {
                                console.error('Error al analizar el JSON:', error);
                            }
                        } else {
                            console.warn('La respuesta de horario está vacía');
                            // Puedes tomar alguna acción adicional aquí, como mostrar un mensaje al usuario.
                        }
                    } else {
                        console.error('Error al obtener horario:', horarioResponse.status);
                    }
                }
            } catch (error) {
                console.error('Error fetching horario', error);
            }
        };

        fetchData();
    }, [input.cod_asig, input.seccion]);

    const estiloCelda = (dia, modulo) => {
        const esSeccionSeleccionada = parseInt(selectedDia) === parseInt(dia) && parseInt(selectedModulo) === parseInt(modulo);

        if (horario && horario[`modulo${modulo}`]) {
            const tieneClase = horario[`modulo${modulo}`][dia - 1] === 1;

            // Si la celda corresponde a la sección seleccionada y hay una clase, utilizar color #e07924
            if (esSeccionSeleccionada && tieneClase) {
                return {
                    backgroundColor: "#e07924",
                };
            } else if (tieneClase) {
                // Si hay una clase pero no corresponde a la sección seleccionada, utilizar color #23394d
                return {
                    backgroundColor: "#23394d",
                };
            }
        }

        // Si la celda corresponde a la sección seleccionada o no tiene clase, utilizar color #e07924
        if (esSeccionSeleccionada) {
            return {
                backgroundColor: "#e07924",
            };
        }

        // En cualquier otro caso, utilizar color blanco
        return {
            backgroundColor: "white",
        };
    };

    return (
        <div className="tabla-container">
            <table>
                <thead>
                <tr>
                    <th className="primera-columna">Modulos/Dias</th>
                    {diasSemana.map((dia, index) => (
                        <th key={index}>{dia}</th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {modulos.map((modulo, index) => (
                    <tr key={index}>
                        <td className="primera-columna">{modulo}</td>
                        {diasSemana.map((dia, diaIndex) => (
                            <td
                                key={diaIndex}
                                style={estiloCelda(diaIndex+1, index+1)}
                            ></td>
                        ))}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Tabla;