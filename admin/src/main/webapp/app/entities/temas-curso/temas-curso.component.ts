import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITemasCurso } from 'app/shared/model/temas-curso.model';
import { Principal } from 'app/core';
import { TemasCursoService } from './temas-curso.service';

@Component({
    selector: 'jhi-temas-curso',
    templateUrl: './temas-curso.component.html'
})
export class TemasCursoComponent implements OnInit, OnDestroy {
    temasCursos: ITemasCurso[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private temasCursoService: TemasCursoService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.temasCursoService.query().subscribe(
            (res: HttpResponse<ITemasCurso[]>) => {
                this.temasCursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTemasCursos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITemasCurso) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInTemasCursos() {
        this.eventSubscriber = this.eventManager.subscribe('temasCursoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
