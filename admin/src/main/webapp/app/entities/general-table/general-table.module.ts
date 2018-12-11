import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    GeneralTableComponent,
    GeneralTableDetailComponent,
    GeneralTableUpdateComponent,
    GeneralTableDeletePopupComponent,
    GeneralTableDeleteDialogComponent,
    generalTableRoute,
    generalTablePopupRoute
} from './';

const ENTITY_STATES = [...generalTableRoute, ...generalTablePopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GeneralTableComponent,
        GeneralTableDetailComponent,
        GeneralTableUpdateComponent,
        GeneralTableDeleteDialogComponent,
        GeneralTableDeletePopupComponent
    ],
    entryComponents: [
        GeneralTableComponent,
        GeneralTableUpdateComponent,
        GeneralTableDeleteDialogComponent,
        GeneralTableDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminGeneralTableModule {}
