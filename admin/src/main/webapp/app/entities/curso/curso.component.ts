import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICurso } from 'app/shared/model/curso.model';
import { Principal } from 'app/core';
import { CursoService } from './curso.service';

@Component({
    selector: 'jhi-curso',
    templateUrl: './curso.component.html'
})
export class CursoComponent implements OnInit, OnDestroy {
    cursos: ICurso[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private cursoService: CursoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.cursoService.query().subscribe(
            (res: HttpResponse<ICurso[]>) => {
                this.cursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCursos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICurso) {
        return item.id;
    }

    registerChangeInCursos() {
        this.eventSubscriber = this.eventManager.subscribe('cursoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
