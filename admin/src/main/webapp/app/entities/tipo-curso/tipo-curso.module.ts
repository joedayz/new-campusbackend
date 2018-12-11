import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    TipoCursoComponent,
    TipoCursoDetailComponent,
    TipoCursoUpdateComponent,
    TipoCursoDeletePopupComponent,
    TipoCursoDeleteDialogComponent,
    tipoCursoRoute,
    tipoCursoPopupRoute
} from './';

const ENTITY_STATES = [...tipoCursoRoute, ...tipoCursoPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoCursoComponent,
        TipoCursoDetailComponent,
        TipoCursoUpdateComponent,
        TipoCursoDeleteDialogComponent,
        TipoCursoDeletePopupComponent
    ],
    entryComponents: [TipoCursoComponent, TipoCursoUpdateComponent, TipoCursoDeleteDialogComponent, TipoCursoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminTipoCursoModule {}
