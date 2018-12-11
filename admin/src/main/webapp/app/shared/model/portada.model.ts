export interface IPortada {
    id?: number;
    tituloFoto?: string;
    urlFoto?: string;
    status?: boolean;
}

export class Portada implements IPortada {
    constructor(public id?: number, public tituloFoto?: string, public urlFoto?: string, public status?: boolean) {
        this.status = this.status || false;
    }
}
