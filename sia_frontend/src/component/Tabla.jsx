import React from 'react';
import '../style/Tabla.css';

const diasSemana = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
const modulos = ['Modulo 1  (8:15 - 9:30)', 'Modulo 2  (9:50 - 11:10)', 'Modulo 3  (11:25 - 12:45)', 'Modulo 4  (13:45 - 15:05)', 'Modulo 5  (15:20 - 16:40)', 'Modulo 6  (16:55 - 18:15)'];

const Tabla = ({ selectedDia, selectedModulo, horario }) => {
    const estiloCelda = (dia, modulo) => {
        //console.log('dia' + dia)
        //console.log('modulo' + modulo)

        return {
            backgroundColor: parseInt(selectedDia) === dia && parseInt(selectedModulo) === modulo ? "#e07924" : "white",
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
                                style={estiloCelda(diaIndex + 1, index + 1)}
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