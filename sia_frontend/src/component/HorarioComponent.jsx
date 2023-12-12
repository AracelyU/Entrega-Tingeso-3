import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../style/App.css';
import Navbar from "./Navbar";
import { Table, Container as StyledContainer } from 'react-bootstrap';
import styled from "styled-components";



const Container = styled(StyledContainer)`
  background-color: #ffffff;
  padding: 20px;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;

const TableContainer = styled.div`
  background-color: #f5f5f5;
  margin-top: 20px;
  width: 100%;
`;


const HorariosTable = styled(Table)`
  margin-bottom: 0;
  background-color: #ffffff;
  border: 2px solid  #e5702a;

  thead {
    background-color: #7fb3d5;
    
    color: #ffffff;


    th {
      border: 2px solid  #e5702a;

    }
  }

  tbody {
    td {
      border: 2px solid  #e5702a;
      color: ${({inscrito}) => (inscrito ? '#23394d' : '#000000')};
    }
  }
`;

const RamosTable = styled(Table)`
  
  margin-top: 20px;
  width: 100%;
  background-color: #ffffff;
  border: 2px solid #23394d;

  thead {
    background-color: #e5702a;
    color: #ffffff;
    border: 2px solid #23394d;

    th {
      border: 2px solid  #e5702a;
      
    }
  }

  tbody {
    td {
      border: 2px solid  #e5702a;
      //border: 2px solid #dddddd;
    }
  }
`;


const LegendContainer = styled.div`
  margin-top: 20px;
  display: flex;
  align-items: center;
`;

const LegendItem = styled.div`
  display: flex;
  align-items: center;
  margin-right: 20px;
`;

const LegendColor = styled.div`
  width: 20px;
  height: 20px;
  margin-right: 10px;
  background-color: ${({ color }) => color};
`;

// Obtener un array de colores únicos para cada cod_asig
const getUniqueColors = (ramos) => {
    const uniqueColors = {};
    const colors = [];
    ramos.forEach((ramo) => {
        if (!uniqueColors[ramo.cod_asig]) {
            uniqueColors[ramo.cod_asig] = getRandomColor();
            colors.push({ cod_asig: ramo.cod_asig, color: uniqueColors[ramo.cod_asig] });
        }
    });
    return colors;
};

const getRandomColor = () => {
    const colors = ['#64b2ff', '#F1D23BFF', '#75D275FF', '#FFC9BBFF', '#8484D7FF', '#B776ECFF'];
    return colors[Math.floor(Math.random() * colors.length)];
};

// componente para agregar un horario a una asignatura
export default function Horario() {



    const [horariosInscribir, setHorariosInscribir] = useState([]);
    const [legendColors, setLegendColors] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const [horariosResponse, ramosResponse] = await Promise.all([
                fetch("http://localhost:8080/horario/getHorariosInscribir").then(response => response.json()),
            ]);
            setHorariosInscribir(horariosResponse.map(({ id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, cod_asig, nom_asig, cupos, seccion }) => ({
                id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, cod_asig,nom_asig, cupos,seccion
            })));
            // Obtener colores únicos para cada cod_asig
            const colors = getUniqueColors(horariosResponse);
            setLegendColors(colors);
        };
        fetchData();
    }, []);


    const getCellInfo = (currentModulo, dia, ramos) => {
        for (const ramo of ramos) {
            for (const horario of horariosInscribir) {
                if (horario && horario[`modulo${currentModulo}`]) {
                    const index = dia - 1;
                    if (horario[`modulo${currentModulo}`][index] === 1 && horario.cod_asig === ramo.cod_asig) {
                        return {
                            text: `${horario.cod_asig} - ${horario.seccion}`,
                            color: ramo.color,
                        };
                    }
                }
            }
        }
        return '';
    };



    return (
        <div style={{backgroundColor: "blue"}}>
            <Navbar />
            <Container fluid>
                <div className="row">
                    <div className="col-md-6">
                        <h2>Horario 2024/01</h2>
                        {!horariosInscribir && (
                            <h5>Cargando Asignaturas incritas...</h5>
                        )}
                        <TableContainer>
                            <HorariosTable striped bordered hover inscrito>
                                <thead>
                                <tr>
                                    <th></th>
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
                                    <tr key={modulo + 1}>
                                        <td>Modulo {modulo + 1}</td>
                                        {Array.from({ length: 6 }, (_, dia) => (
                                            <td
                                                key={dia + 1}
                                                style={{
                                                    backgroundColor: getCellInfo(modulo + 1, dia + 1, legendColors) ? getCellInfo(modulo + 1, dia + 1, legendColors).color : 'white',
                                                    color: getCellInfo(modulo + 1, dia + 1, legendColors) ? '#23394d' : '#000000',
                                                }}
                                            >
                                                {getCellInfo(modulo + 1, dia + 1, legendColors) && getCellInfo(modulo + 1, dia + 1, legendColors).text}
                                            </td>
                                        ))}
                                    </tr>
                                ))}
                                </tbody>
                            </HorariosTable>
                        </TableContainer>
                    </div>

                    <div className="col-md-6">
                        <h2>Listado de asignaturas</h2>
                        <TableContainer>
                            <RamosTable striped bordered hover>
                                <thead>
                                <tr>
                                    <th>Código Asignatura</th>
                                    <th>Nombre Asignatura</th>
                                    <th>Sección</th>
                                    <th>Nro Inscritos</th>
                                </tr>
                                </thead>
                                <tbody>
                                {horariosInscribir &&
                                    horariosInscribir.map(ramo => (
                                        <tr key={ramo.id}>
                                            <td>{ramo.cod_asig}</td>
                                            <td>{ramo.nom_asig}</td>
                                            <td>{ramo.seccion}</td>
                                            <td>{ramo.cupos}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </RamosTable>
                        </TableContainer>

                        <LegendContainer>
                            {legendColors.map(colorInfo => (
                                <LegendItem key={colorInfo.cod_asig}>
                                    <LegendColor color={colorInfo.color} />
                                    <div>{colorInfo.cod_asig}</div>
                                </LegendItem>
                            ))}
                        </LegendContainer>
                    </div>
                </div>
                <br></br><br></br>



                <h5>* Si el estudiante está inscrito a una asignatura que no tenga a lo menos un
                horario asignado, no se verá la información en está sección</h5>
                <br></br>
                <br></br>
            </Container>

        </div>
    );
}