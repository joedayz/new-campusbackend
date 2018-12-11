import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGeneralTable } from 'app/shared/model/general-table.model';
import { Principal } from 'app/core';
import { GeneralTableService } from './general-table.service';

@Component({
    selector: 'jhi-general-table',
    templateUrl: './general-table.component.html'
})
export class GeneralTableComponent implements OnInit, OnDestroy {
    generalTables: IGeneralTable[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private generalTableService: GeneralTableService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.generalTableService.query().subscribe(
            (res: HttpResponse<IGeneralTable[]>) => {
                this.generalTables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGeneralTables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGeneralTable) {
        return item.id;
    }

    registerChangeInGeneralTables() {
        this.eventSubscriber = this.eventManager.subscribe('generalTableListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
