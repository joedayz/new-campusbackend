export interface IGeneralTable {
    id?: number;
    code?: string;
    tabla?: string;
    valor?: string;
    orden?: number;
    status?: boolean;
}

export class GeneralTable implements IGeneralTable {
    constructor(
        public id?: number,
        public code?: string,
        public tabla?: string,
        public valor?: string,
        public orden?: number,
        public status?: boolean
    ) {
        this.status = this.status || false;
    }
}
