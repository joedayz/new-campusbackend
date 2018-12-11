import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoCurso } from 'app/shared/model/tipo-curso.model';
import { TipoCursoService } from './tipo-curso.service';
import { TipoCursoComponent } from './tipo-curso.component';
import { TipoCursoDetailComponent } from './tipo-curso-detail.component';
import { TipoCursoUpdateComponent } from './tipo-curso-update.component';
import { TipoCursoDeletePopupComponent } from './tipo-curso-delete-dialog.component';
import { ITipoCurso } from 'app/shared/model/tipo-curso.model';

@Injectable({ providedIn: 'root' })
export class TipoCursoResolve implements Resolve<ITipoCurso> {
    constructor(private service: TipoCursoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoCurso> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoCurso>) => response.ok),
                map((tipoCurso: HttpResponse<TipoCurso>) => tipoCurso.body)
            );
        }
        return of(new TipoCurso());
    }
}

export const tipoCursoRoute: Routes = [
    {
        path: 'tipo-curso',
        component: TipoCursoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.tipoCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-curso/:id/view',
        component: TipoCursoDetailComponent,
        resolve: {
            tipoCurso: TipoCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.tipoCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-curso/new',
        component: TipoCursoUpdateComponent,
        resolve: {
            tipoCurso: TipoCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.tipoCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-curso/:id/edit',
        component: TipoCursoUpdateComponent,
        resolve: {
            tipoCurso: TipoCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.tipoCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoCursoPopupRoute: Routes = [
    {
        path: 'tipo-curso/:id/delete',
        component: TipoCursoDeletePopupComponent,
        resolve: {
            tipoCurso: TipoCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.tipoCurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
