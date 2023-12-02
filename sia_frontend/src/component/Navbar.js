
import React, {useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import {
    Container,
    LogoContainer,
    Menu,
    MenuItem,
    MenuItemLink,
    MobileIcon,
    Wrapper} from "./NavbarElement";
import { IconContext } from "react-icons";
import { FaBars, FaTimes } from "react-icons/fa";
import { IoMdPerson } from "react-icons/io";
import { FaRegCalendarDays } from "react-icons/fa6";
import { GiArchiveRegister } from "react-icons/gi";
import { IoMdHelpCircleOutline } from "react-icons/io"


const Navbar = () => {
    const [showMobileMenu, setShowMobileMenu] = useState(false)

    const navigate = useNavigate();
    const navigatePerfil = (event) => {
        navigate("/");
    };

    const navigateHorario = (event) => {
        navigate("/horario-estudiante");
    };

    const navigateInscripcion = (event) => {
        navigate("/inscripcion-estudiante");
    };

    const navigateAyuda = (event) => {
        navigate("/ayuda");
    };


    return(
        <Container>
            <Wrapper>
                <IconContext.Provider value = {{ style: {fontSize: "2rem"} }}>

                    <LogoContainer>
                        <br/>
                        <p>SIA - </p>
                        <p>FING</p>
                    </LogoContainer>

                    <MobileIcon onClick={() => setShowMobileMenu(!showMobileMenu)}>
                        {
                            showMobileMenu ? <FaTimes /> : <FaBars />
                        }
                    </MobileIcon>

                    <Menu open ={showMobileMenu}>

                        <MenuItem>
                            <MenuItemLink onClick={navigatePerfil}>
                                <div>
                                    <IoMdPerson />
                                    PERFIL
                                </div>
                            </MenuItemLink>
                        </MenuItem>

                        <MenuItem>
                            <MenuItemLink onClick={navigateHorario}>
                                <div>
                                    <FaRegCalendarDays />
                                    MI HORARIO
                                </div>

                            </MenuItemLink>
                        </MenuItem>

                        <MenuItem>
                            <MenuItemLink onClick={navigateInscripcion}>
                                <div>
                                    <GiArchiveRegister />
                                    INSCRIBIR ASIGNATURA
                                </div>
                            </MenuItemLink>
                        </MenuItem>

                        <MenuItem>
                            <MenuItemLink onClick={navigateAyuda}>
                                <div>
                                    <IoMdHelpCircleOutline />
                                    AYUDA
                                </div>
                                </MenuItemLink>
                        </MenuItem>
                    </Menu>
                    </IconContext.Provider>

            </Wrapper>

        </Container>


    )
}
export default Navbar;