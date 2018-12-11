import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DatosCurso } from 'app/shared/model/datos-curso.model';
import { DatosCursoService } from './datos-curso.service';
import { DatosCursoComponent } from './datos-curso.component';
import { DatosCursoDetailComponent } from './datos-curso-detail.component';
import { DatosCursoUpdateComponent } from './datos-curso-update.component';
import { DatosCursoDeletePopupComponent } from './datos-curso-delete-dialog.component';
import { IDatosCurso } from 'app/shared/model/datos-curso.model';

@Injectable({ providedIn: 'root' })
export class DatosCursoResolve implements Resolve<IDatosCurso> {
    constructor(private service: DatosCursoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DatosCurso> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DatosCurso>) => response.ok),
                map((datosCurso: HttpResponse<DatosCurso>) => datosCurso.body)
            );
        }
        return of(new DatosCurso());
    }
}

export const datosCursoRoute: Routes = [
    {
        path: 'datos-curso',
        component: DatosCursoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.datosCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'datos-curso/:id/view',
        component: DatosCursoDetailComponent,
        resolve: {
            datosCurso: DatosCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.datosCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'datos-curso/new',
        component: DatosCursoUpdateComponent,
        resolve: {
            datosCurso: DatosCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.datosCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'datos-curso/:id/edit',
        component: DatosCursoUpdateComponent,
        resolve: {
            datosCurso: DatosCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.datosCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const datosCursoPopupRoute: Routes = [
    {
        path: 'datos-curso/:id/delete',
        component: DatosCursoDeletePopupComponent,
        resolve: {
            datosCurso: DatosCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.datosCurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
