import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faBars, faBookBookmark, faEye, faPlus, faCalendarDays, faAddressBook, faCalendarCheck,
    faUserCircle, faTable, faInfo} from '@fortawesome/free-solid-svg-icons';

import { useState } from 'react';


export default function Menu() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const [hoveredButton, setHoveredButton] = useState(null);

    const handleMouseEnter = (buttonId) => {
        setHoveredButton(buttonId);
    };

    const handleMouseLeave = () => {
        setHoveredButton(null);
    };

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <div className={`container-menu ${isMenuOpen ? 'menu-open' : ''}`}>
            <div className='group-bottoms'>
                <div className='menu-icon' onClick={toggleMenu}>
                    <FontAwesomeIcon icon={faBars} style={{ fontSize: '30px', cursor: 'pointer' }} />
                </div>
                { !isMenuOpen && (
                    <>
                        <br></br>
                        <a
                            href="/students"  // URL DE DONDE SE VA AL APRETAR ESE BOTÓN
                            className={`boton_cerrado list-group-item ${hoveredButton === 1 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(1)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faCalendarDays} beat={hoveredButton === 1} style={{ color: '#050529',
                                fontSize: "50px", paddingRight: "10px"}} />
                            <FontAwesomeIcon icon={faPlus} beat={hoveredButton === 1} style={{ color: '#050529', fontSize: "30px" }} />
                        </a>
                        <h1></h1>
                        <a
                            href="/students"
                            className={`boton_cerrado list-group-item ${hoveredButton === 2 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(2)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faBookBookmark} beat={hoveredButton === 2} style={{ fontSize: "50px", paddingRight: "8px"}} />
                            <FontAwesomeIcon icon={faEye} beat={hoveredButton === 2} style={{ fontSize: "30px" }} />
                        </a>

                        <br></br>

                        <a
                            href="/students"
                            className={`be boton_cerrado list-group-item ${hoveredButton === 3 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(3)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faAddressBook} beat={hoveredButton === 3} style={{ fontSize: "50px"}} />
                        </a>

                        <h1></h1>

                        <a
                            href="/students"
                            className={`be boton_cerrado list-group-item ${hoveredButton === 4 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(4)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faCalendarCheck} beat={hoveredButton === 4} style={{ fontSize: "50px", paddingRight: "10px"}} />
                            <FontAwesomeIcon icon={faUserCircle} beat={hoveredButton === 4} style={{ fontSize: "30px" }} />
                        </a>


                        <h1></h1>

                        <a
                            href="/students"
                            className={`be boton_cerrado list-group-item ${hoveredButton === 5 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(5)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faTable} beat={hoveredButton === 5} style={{ fontSize: "50px", paddingRight: "10px"}} />
                            <FontAwesomeIcon icon={faInfo} beat={hoveredButton === 5} style={{ fontSize: "30px" }} />
                        </a>
                        <br></br>
                    </>
                )}
                {isMenuOpen && (
                    <>
                        <h4>M贸dulo Administraci贸n</h4>
                        <a
                            href="/students"
                            className={`boton list-group-item ${hoveredButton === 1 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(1)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faCalendarDays} beat={hoveredButton === 1} style={{ fontSize: "50px", paddingRight: "10px"}} />
                            <FontAwesomeIcon icon={faPlus} beat={hoveredButton === 1} style={{ fontSize: "30px" }} />
                            <span style={{ marginLeft: "10px" }}>Ingresar horarios</span>
                        </a>

                        <h1></h1>

                        <a
                            href="/students"
                            className={`boton list-group-item ${hoveredButton === 2 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(2)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faBookBookmark} beat={hoveredButton === 2} style={{ fontSize: "50px", paddingRight: "15px"}} />
                            <FontAwesomeIcon icon={faEye} beat={hoveredButton === 2} style={{ fontSize: "30px" }} />
                            <span style={{ marginLeft: "10px" }}>Ver Cursos</span>
                        </a>

                        <br></br>

                        <h4>M贸dulo Estudiante</h4>

                        <a
                            href="/students"
                            className={`be boton list-group-item ${hoveredButton === 3 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(3)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faAddressBook} beat={hoveredButton === 3} style={{ fontSize: "50px"}} />
                            <span style={{ marginLeft: "10px" }}>Inscripci贸n de Cursos</span>
                        </a>

                        <h1></h1>

                        <a
                            href="/students"
                            className={`be boton list-group-item ${hoveredButton === 4 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(4)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faCalendarCheck} beat={hoveredButton === 4} style={{ fontSize: "50px", paddingRight: "15px"}} />
                            <FontAwesomeIcon icon={faUserCircle} beat={hoveredButton === 4} style={{ fontSize: "30px" }} />
                            <span style={{ marginLeft: "10px" }}>Ver horario</span>
                        </a>


                        <h1></h1>

                        <a
                            href="/students"
                            className={`be boton list-group-item ${hoveredButton === 5 ? 'hovered' : ''}`}
                            onMouseEnter={() => handleMouseEnter(5)}
                            onMouseLeave={handleMouseLeave}
                        >
                            <FontAwesomeIcon icon={faTable} beat={hoveredButton === 5} style={{ fontSize: "50px", paddingRight: "15px"}} />
                            <FontAwesomeIcon icon={faInfo} beat={hoveredButton === 5} style={{ fontSize: "30px" }} />
                            <span style={{ marginLeft: "10px" }}>Ver malla</span>
                        </a>
                    </>
                )}
            </div>
        </div>
    );
};