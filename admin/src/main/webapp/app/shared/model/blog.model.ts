import { Moment } from 'moment';

export interface IBlog {
    id?: number;
    titulo?: string;
    autor?: string;
    descripcion?: any;
    url?: string;
    fecha?: Moment;
    status?: boolean;
}

export class Blog implements IBlog {
    constructor(
        public id?: number,
        public titulo?: string,
        public autor?: string,
        public descripcion?: any,
        public url?: string,
        public fecha?: Moment,
        public status?: boolean
    ) {
        this.status = this.status || false;
    }
}
