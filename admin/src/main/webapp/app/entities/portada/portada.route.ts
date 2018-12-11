import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Portada } from 'app/shared/model/portada.model';
import { PortadaService } from './portada.service';
import { PortadaComponent } from './portada.component';
import { PortadaDetailComponent } from './portada-detail.component';
import { PortadaUpdateComponent } from './portada-update.component';
import { PortadaDeletePopupComponent } from './portada-delete-dialog.component';
import { IPortada } from 'app/shared/model/portada.model';

@Injectable({ providedIn: 'root' })
export class PortadaResolve implements Resolve<IPortada> {
    constructor(private service: PortadaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Portada> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Portada>) => response.ok),
                map((portada: HttpResponse<Portada>) => portada.body)
            );
        }
        return of(new Portada());
    }
}

export const portadaRoute: Routes = [
    {
        path: 'portada',
        component: PortadaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.portada.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'portada/:id/view',
        component: PortadaDetailComponent,
        resolve: {
            portada: PortadaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.portada.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'portada/new',
        component: PortadaUpdateComponent,
        resolve: {
            portada: PortadaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.portada.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'portada/:id/edit',
        component: PortadaUpdateComponent,
        resolve: {
            portada: PortadaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.portada.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const portadaPopupRoute: Routes = [
    {
        path: 'portada/:id/delete',
        component: PortadaDeletePopupComponent,
        resolve: {
            portada: PortadaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.portada.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
