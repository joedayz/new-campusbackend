import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IHangout } from 'app/shared/model/hangout.model';

@Component({
    selector: 'jhi-hangout-detail',
    templateUrl: './hangout-detail.component.html'
})
export class HangoutDetailComponent implements OnInit {
    hangout: IHangout;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hangout }) => {
            this.hangout = hangout;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
