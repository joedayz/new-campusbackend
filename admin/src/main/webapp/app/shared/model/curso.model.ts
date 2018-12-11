import { ITipoCurso } from 'app/shared/model//tipo-curso.model';

export interface ICurso {
    id?: number;
    titulo?: string;
    tituloFooter?: string;
    keyCurso?: string;
    destacado?: boolean;
    status?: boolean;
    tipoCurso?: ITipoCurso;
}

export class Curso implements ICurso {
    constructor(
        public id?: number,
        public titulo?: string,
        public tituloFooter?: string,
        public keyCurso?: string,
        public destacado?: boolean,
        public status?: boolean,
        public tipoCurso?: ITipoCurso
    ) {
        this.destacado = this.destacado || false;
        this.status = this.status || false;
    }
}
