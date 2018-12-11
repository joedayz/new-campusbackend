import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TemasCurso } from 'app/shared/model/temas-curso.model';
import { TemasCursoService } from './temas-curso.service';
import { TemasCursoComponent } from './temas-curso.component';
import { TemasCursoDetailComponent } from './temas-curso-detail.component';
import { TemasCursoUpdateComponent } from './temas-curso-update.component';
import { TemasCursoDeletePopupComponent } from './temas-curso-delete-dialog.component';
import { ITemasCurso } from 'app/shared/model/temas-curso.model';

@Injectable({ providedIn: 'root' })
export class TemasCursoResolve implements Resolve<ITemasCurso> {
    constructor(private service: TemasCursoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TemasCurso> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TemasCurso>) => response.ok),
                map((temasCurso: HttpResponse<TemasCurso>) => temasCurso.body)
            );
        }
        return of(new TemasCurso());
    }
}

export const temasCursoRoute: Routes = [
    {
        path: 'temas-curso',
        component: TemasCursoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.temasCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'temas-curso/:id/view',
        component: TemasCursoDetailComponent,
        resolve: {
            temasCurso: TemasCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.temasCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'temas-curso/new',
        component: TemasCursoUpdateComponent,
        resolve: {
            temasCurso: TemasCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.temasCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'temas-curso/:id/edit',
        component: TemasCursoUpdateComponent,
        resolve: {
            temasCurso: TemasCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.temasCurso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const temasCursoPopupRoute: Routes = [
    {
        path: 'temas-curso/:id/delete',
        component: TemasCursoDeletePopupComponent,
        resolve: {
            temasCurso: TemasCursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.temasCurso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
