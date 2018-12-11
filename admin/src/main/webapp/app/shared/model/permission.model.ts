import { IModule } from 'app/shared/model//module.model';

export interface IPermission {
    id?: number;
    roleName?: string;
    permissionType?: string;
    moduleId?: IModule;
}

export class Permission implements IPermission {
    constructor(public id?: number, public roleName?: string, public permissionType?: string, public moduleId?: IModule) {}
}
