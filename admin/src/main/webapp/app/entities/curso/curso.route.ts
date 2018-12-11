import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Curso } from 'app/shared/model/curso.model';
import { CursoService } from './curso.service';
import { CursoComponent } from './curso.component';
import { CursoDetailComponent } from './curso-detail.component';
import { CursoUpdateComponent } from './curso-update.component';
import { CursoDeletePopupComponent } from './curso-delete-dialog.component';
import { ICurso } from 'app/shared/model/curso.model';

@Injectable({ providedIn: 'root' })
export class CursoResolve implements Resolve<ICurso> {
    constructor(private service: CursoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Curso> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Curso>) => response.ok),
                map((curso: HttpResponse<Curso>) => curso.body)
            );
        }
        return of(new Curso());
    }
}

export const cursoRoute: Routes = [
    {
        path: 'curso',
        component: CursoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.curso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'curso/:id/view',
        component: CursoDetailComponent,
        resolve: {
            curso: CursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.curso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'curso/new',
        component: CursoUpdateComponent,
        resolve: {
            curso: CursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.curso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'curso/:id/edit',
        component: CursoUpdateComponent,
        resolve: {
            curso: CursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.curso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cursoPopupRoute: Routes = [
    {
        path: 'curso/:id/delete',
        component: CursoDeletePopupComponent,
        resolve: {
            curso: CursoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.curso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
