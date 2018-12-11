export interface ITipoCurso {
    id?: number;
    nombre?: string;
    code?: string;
    status?: boolean;
}

export class TipoCurso implements ITipoCurso {
    constructor(public id?: number, public nombre?: string, public code?: string, public status?: boolean) {
        this.status = this.status || false;
    }
}
