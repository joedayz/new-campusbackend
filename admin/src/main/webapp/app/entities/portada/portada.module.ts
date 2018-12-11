import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    PortadaComponent,
    PortadaDetailComponent,
    PortadaUpdateComponent,
    PortadaDeletePopupComponent,
    PortadaDeleteDialogComponent,
    portadaRoute,
    portadaPopupRoute
} from './';

const ENTITY_STATES = [...portadaRoute, ...portadaPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PortadaComponent,
        PortadaDetailComponent,
        PortadaUpdateComponent,
        PortadaDeleteDialogComponent,
        PortadaDeletePopupComponent
    ],
    entryComponents: [PortadaComponent, PortadaUpdateComponent, PortadaDeleteDialogComponent, PortadaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminPortadaModule {}
