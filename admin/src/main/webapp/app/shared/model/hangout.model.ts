import { Moment } from 'moment';

export interface IHangout {
    id?: number;
    titulo?: string;
    ponente?: string;
    descripcion?: any;
    url?: string;
    fecha?: Moment;
    status?: boolean;
}

export class Hangout implements IHangout {
    constructor(
        public id?: number,
        public titulo?: string,
        public ponente?: string,
        public descripcion?: any,
        public url?: string,
        public fecha?: Moment,
        public status?: boolean
    ) {
        this.status = this.status || false;
    }
}
