import axios from "axios";

const API_URL_HORARIO = "http://localhost:8080/ramo";

class RamoService{

    // inscribir ramo  /inscribirRamo/{cod_asig}/{seccion}
    inscribirRamo(cod_asig, seccion) {
        console.log(cod_asig);
        console.log(seccion);
        const url = `${API_URL_HORARIO}/inscribirRamo/${cod_asig}/${seccion}`;
        return axios.post(url);
    }




}

export default new RamoService()