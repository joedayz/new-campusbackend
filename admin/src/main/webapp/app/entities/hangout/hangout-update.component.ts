import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { IHangout } from 'app/shared/model/hangout.model';
import { HangoutService } from './hangout.service';

@Component({
    selector: 'jhi-hangout-update',
    templateUrl: './hangout-update.component.html'
})
export class HangoutUpdateComponent implements OnInit {
    hangout: IHangout;
    isSaving: boolean;
    fecha: string;

    constructor(private dataUtils: JhiDataUtils, private hangoutService: HangoutService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hangout }) => {
            this.hangout = hangout;
            this.fecha = this.hangout.fecha != null ? this.hangout.fecha.format(DATE_TIME_FORMAT) : null;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.hangout.fecha = this.fecha != null ? moment(this.fecha, DATE_TIME_FORMAT) : null;
        if (this.hangout.id !== undefined) {
            this.subscribeToSaveResponse(this.hangoutService.update(this.hangout));
        } else {
            this.subscribeToSaveResponse(this.hangoutService.create(this.hangout));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHangout>>) {
        result.subscribe((res: HttpResponse<IHangout>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
