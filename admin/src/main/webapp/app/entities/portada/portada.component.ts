import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPortada } from 'app/shared/model/portada.model';
import { Principal } from 'app/core';
import { PortadaService } from './portada.service';

@Component({
    selector: 'jhi-portada',
    templateUrl: './portada.component.html'
})
export class PortadaComponent implements OnInit, OnDestroy {
    portadas: IPortada[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private portadaService: PortadaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.portadaService.query().subscribe(
            (res: HttpResponse<IPortada[]>) => {
                this.portadas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPortadas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPortada) {
        return item.id;
    }

    registerChangeInPortadas() {
        this.eventSubscriber = this.eventManager.subscribe('portadaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
