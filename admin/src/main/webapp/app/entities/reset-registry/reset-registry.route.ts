import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ResetRegistry } from 'app/shared/model/reset-registry.model';
import { ResetRegistryService } from './reset-registry.service';
import { ResetRegistryComponent } from './reset-registry.component';
import { ResetRegistryDetailComponent } from './reset-registry-detail.component';
import { ResetRegistryUpdateComponent } from './reset-registry-update.component';
import { ResetRegistryDeletePopupComponent } from './reset-registry-delete-dialog.component';
import { IResetRegistry } from 'app/shared/model/reset-registry.model';

@Injectable({ providedIn: 'root' })
export class ResetRegistryResolve implements Resolve<IResetRegistry> {
    constructor(private service: ResetRegistryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ResetRegistry> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ResetRegistry>) => response.ok),
                map((resetRegistry: HttpResponse<ResetRegistry>) => resetRegistry.body)
            );
        }
        return of(new ResetRegistry());
    }
}

export const resetRegistryRoute: Routes = [
    {
        path: 'reset-registry',
        component: ResetRegistryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.resetRegistry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reset-registry/:id/view',
        component: ResetRegistryDetailComponent,
        resolve: {
            resetRegistry: ResetRegistryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.resetRegistry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reset-registry/new',
        component: ResetRegistryUpdateComponent,
        resolve: {
            resetRegistry: ResetRegistryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.resetRegistry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reset-registry/:id/edit',
        component: ResetRegistryUpdateComponent,
        resolve: {
            resetRegistry: ResetRegistryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.resetRegistry.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resetRegistryPopupRoute: Routes = [
    {
        path: 'reset-registry/:id/delete',
        component: ResetRegistryDeletePopupComponent,
        resolve: {
            resetRegistry: ResetRegistryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.resetRegistry.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
