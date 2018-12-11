import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    ResetRegistryComponent,
    ResetRegistryDetailComponent,
    ResetRegistryUpdateComponent,
    ResetRegistryDeletePopupComponent,
    ResetRegistryDeleteDialogComponent,
    resetRegistryRoute,
    resetRegistryPopupRoute
} from './';

const ENTITY_STATES = [...resetRegistryRoute, ...resetRegistryPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResetRegistryComponent,
        ResetRegistryDetailComponent,
        ResetRegistryUpdateComponent,
        ResetRegistryDeleteDialogComponent,
        ResetRegistryDeletePopupComponent
    ],
    entryComponents: [
        ResetRegistryComponent,
        ResetRegistryUpdateComponent,
        ResetRegistryDeleteDialogComponent,
        ResetRegistryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminResetRegistryModule {}
