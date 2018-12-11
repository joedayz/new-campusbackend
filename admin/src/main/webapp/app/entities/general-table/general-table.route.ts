import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GeneralTable } from 'app/shared/model/general-table.model';
import { GeneralTableService } from './general-table.service';
import { GeneralTableComponent } from './general-table.component';
import { GeneralTableDetailComponent } from './general-table-detail.component';
import { GeneralTableUpdateComponent } from './general-table-update.component';
import { GeneralTableDeletePopupComponent } from './general-table-delete-dialog.component';
import { IGeneralTable } from 'app/shared/model/general-table.model';

@Injectable({ providedIn: 'root' })
export class GeneralTableResolve implements Resolve<IGeneralTable> {
    constructor(private service: GeneralTableService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GeneralTable> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GeneralTable>) => response.ok),
                map((generalTable: HttpResponse<GeneralTable>) => generalTable.body)
            );
        }
        return of(new GeneralTable());
    }
}

export const generalTableRoute: Routes = [
    {
        path: 'general-table',
        component: GeneralTableComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.generalTable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'general-table/:id/view',
        component: GeneralTableDetailComponent,
        resolve: {
            generalTable: GeneralTableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.generalTable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'general-table/new',
        component: GeneralTableUpdateComponent,
        resolve: {
            generalTable: GeneralTableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.generalTable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'general-table/:id/edit',
        component: GeneralTableUpdateComponent,
        resolve: {
            generalTable: GeneralTableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.generalTable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const generalTablePopupRoute: Routes = [
    {
        path: 'general-table/:id/delete',
        component: GeneralTableDeletePopupComponent,
        resolve: {
            generalTable: GeneralTableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.generalTable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
