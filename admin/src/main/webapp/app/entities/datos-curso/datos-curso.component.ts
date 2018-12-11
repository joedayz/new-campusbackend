import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IDatosCurso } from 'app/shared/model/datos-curso.model';
import { Principal } from 'app/core';
import { DatosCursoService } from './datos-curso.service';

@Component({
    selector: 'jhi-datos-curso',
    templateUrl: './datos-curso.component.html'
})
export class DatosCursoComponent implements OnInit, OnDestroy {
    datosCursos: IDatosCurso[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private datosCursoService: DatosCursoService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.datosCursoService.query().subscribe(
            (res: HttpResponse<IDatosCurso[]>) => {
                this.datosCursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDatosCursos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDatosCurso) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInDatosCursos() {
        this.eventSubscriber = this.eventManager.subscribe('datosCursoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
