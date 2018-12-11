import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    DatosCursoComponent,
    DatosCursoDetailComponent,
    DatosCursoUpdateComponent,
    DatosCursoDeletePopupComponent,
    DatosCursoDeleteDialogComponent,
    datosCursoRoute,
    datosCursoPopupRoute
} from './';

const ENTITY_STATES = [...datosCursoRoute, ...datosCursoPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DatosCursoComponent,
        DatosCursoDetailComponent,
        DatosCursoUpdateComponent,
        DatosCursoDeleteDialogComponent,
        DatosCursoDeletePopupComponent
    ],
    entryComponents: [DatosCursoComponent, DatosCursoUpdateComponent, DatosCursoDeleteDialogComponent, DatosCursoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminDatosCursoModule {}
