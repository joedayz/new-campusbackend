export interface IResetRegistry {
    id?: number;
    token?: string;
    userName?: string;
    status?: boolean;
}

export class ResetRegistry implements IResetRegistry {
    constructor(public id?: number, public token?: string, public userName?: string, public status?: boolean) {
        this.status = this.status || false;
    }
}
