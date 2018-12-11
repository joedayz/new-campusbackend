import { ICurso } from 'app/shared/model//curso.model';

export interface IDatosCurso {
    id?: number;
    descripcion?: any;
    instructor?: string;
    metodologia?: string;
    participantes?: string;
    requisitos?: string;
    syllabus?: string;
    costo?: string;
    duracion?: string;
    fechaInscripcion?: string;
    horarios?: string;
    numeroParticipantes?: string;
    membresiaAnual?: string;
    curso?: ICurso;
}

export class DatosCurso implements IDatosCurso {
    constructor(
        public id?: number,
        public descripcion?: any,
        public instructor?: string,
        public metodologia?: string,
        public participantes?: string,
        public requisitos?: string,
        public syllabus?: string,
        public costo?: string,
        public duracion?: string,
        public fechaInscripcion?: string,
        public horarios?: string,
        public numeroParticipantes?: string,
        public membresiaAnual?: string,
        public curso?: ICurso
    ) {}
}
