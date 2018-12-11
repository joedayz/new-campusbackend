import { IModule } from 'app/shared/model//module.model';
import { IPermission } from 'app/shared/model//permission.model';

export interface IModule {
    id?: number;
    code?: string;
    nombre?: string;
    url?: string;
    menuLabel?: string;
    menuOrden?: number;
    orden?: number;
    visible?: boolean;
    status?: boolean;
    modules?: IModule[];
    permissions?: IPermission[];
    padre?: IModule;
}

export class Module implements IModule {
    constructor(
        public id?: number,
        public code?: string,
        public nombre?: string,
        public url?: string,
        public menuLabel?: string,
        public menuOrden?: number,
        public orden?: number,
        public visible?: boolean,
        public status?: boolean,
        public modules?: IModule[],
        public permissions?: IPermission[],
        public padre?: IModule
    ) {
        this.visible = this.visible || false;
        this.status = this.status || false;
    }
}
