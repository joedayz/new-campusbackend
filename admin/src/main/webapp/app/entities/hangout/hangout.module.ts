import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminSharedModule } from 'app/shared';
import {
    HangoutComponent,
    HangoutDetailComponent,
    HangoutUpdateComponent,
    HangoutDeletePopupComponent,
    HangoutDeleteDialogComponent,
    hangoutRoute,
    hangoutPopupRoute
} from './';

const ENTITY_STATES = [...hangoutRoute, ...hangoutPopupRoute];

@NgModule({
    imports: [AdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HangoutComponent,
        HangoutDetailComponent,
        HangoutUpdateComponent,
        HangoutDeleteDialogComponent,
        HangoutDeletePopupComponent
    ],
    entryComponents: [HangoutComponent, HangoutUpdateComponent, HangoutDeleteDialogComponent, HangoutDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminHangoutModule {}
