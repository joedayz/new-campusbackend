import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    TemasCursoComponent,
    TemasCursoDetailComponent,
    TemasCursoUpdateComponent,
    TemasCursoDeletePopupComponent,
    TemasCursoDeleteDialogComponent,
    temasCursoRoute,
    temasCursoPopupRoute
} from './';

const ENTITY_STATES = [...temasCursoRoute, ...temasCursoPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TemasCursoComponent,
        TemasCursoDetailComponent,
        TemasCursoUpdateComponent,
        TemasCursoDeleteDialogComponent,
        TemasCursoDeletePopupComponent
    ],
    entryComponents: [TemasCursoComponent, TemasCursoUpdateComponent, TemasCursoDeleteDialogComponent, TemasCursoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminTemasCursoModule {}
