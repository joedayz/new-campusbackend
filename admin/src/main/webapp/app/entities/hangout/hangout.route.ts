import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Hangout } from 'app/shared/model/hangout.model';
import { HangoutService } from './hangout.service';
import { HangoutComponent } from './hangout.component';
import { HangoutDetailComponent } from './hangout-detail.component';
import { HangoutUpdateComponent } from './hangout-update.component';
import { HangoutDeletePopupComponent } from './hangout-delete-dialog.component';
import { IHangout } from 'app/shared/model/hangout.model';

@Injectable({ providedIn: 'root' })
export class HangoutResolve implements Resolve<IHangout> {
    constructor(private service: HangoutService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Hangout> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Hangout>) => response.ok),
                map((hangout: HttpResponse<Hangout>) => hangout.body)
            );
        }
        return of(new Hangout());
    }
}

export const hangoutRoute: Routes = [
    {
        path: 'hangout',
        component: HangoutComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.hangout.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangout/:id/view',
        component: HangoutDetailComponent,
        resolve: {
            hangout: HangoutResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.hangout.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangout/new',
        component: HangoutUpdateComponent,
        resolve: {
            hangout: HangoutResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.hangout.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangout/:id/edit',
        component: HangoutUpdateComponent,
        resolve: {
            hangout: HangoutResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.hangout.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hangoutPopupRoute: Routes = [
    {
        path: 'hangout/:id/delete',
        component: HangoutDeletePopupComponent,
        resolve: {
            hangout: HangoutResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adminApp.hangout.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
