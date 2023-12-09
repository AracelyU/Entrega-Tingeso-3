import axios from "axios";

const API_URL_HORARIO = "http://localhost:8080/horario";

class HorarioService{

    // crear horario  /createHorario/{dia}/{modulo}/{cod_asig}/{seccion}
    createHorario(input) {
        const dia = input.dia;
        const modulo = input.modulo;
        const cod_asig = input.cod_asig;
        const seccion = input.seccion;
        const url = `${API_URL_HORARIO}/createHorario/${dia}/${modulo}/${cod_asig}/${seccion}`;
        return axios.post(url);
    }




}

export default new HorarioService()