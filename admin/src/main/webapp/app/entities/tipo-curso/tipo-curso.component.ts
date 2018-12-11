import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITipoCurso } from 'app/shared/model/tipo-curso.model';
import { Principal } from 'app/core';
import { TipoCursoService } from './tipo-curso.service';

@Component({
    selector: 'jhi-tipo-curso',
    templateUrl: './tipo-curso.component.html'
})
export class TipoCursoComponent implements OnInit, OnDestroy {
    tipoCursos: ITipoCurso[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tipoCursoService: TipoCursoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tipoCursoService.query().subscribe(
            (res: HttpResponse<ITipoCurso[]>) => {
                this.tipoCursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTipoCursos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITipoCurso) {
        return item.id;
    }

    registerChangeInTipoCursos() {
        this.eventSubscriber = this.eventManager.subscribe('tipoCursoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
