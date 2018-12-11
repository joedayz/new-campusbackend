import { ICurso } from 'app/shared/model//curso.model';

export interface ITemasCurso {
    id?: number;
    titulo?: string;
    descripcion?: any;
    url?: string;
    orden?: number;
    status?: boolean;
    curso?: ICurso;
}

export class TemasCurso implements ITemasCurso {
    constructor(
        public id?: number,
        public titulo?: string,
        public descripcion?: any,
        public url?: string,
        public orden?: number,
        public status?: boolean,
        public curso?: ICurso
    ) {
        this.status = this.status || false;
    }
}
