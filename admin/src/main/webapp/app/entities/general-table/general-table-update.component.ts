import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGeneralTable } from 'app/shared/model/general-table.model';
import { GeneralTableService } from './general-table.service';

@Component({
    selector: 'jhi-general-table-update',
    templateUrl: './general-table-update.component.html'
})
export class GeneralTableUpdateComponent implements OnInit {
    generalTable: IGeneralTable;
    isSaving: boolean;

    constructor(private generalTableService: GeneralTableService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ generalTable }) => {
            this.generalTable = generalTable;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.generalTable.id !== undefined) {
            this.subscribeToSaveResponse(this.generalTableService.update(this.generalTable));
        } else {
            this.subscribeToSaveResponse(this.generalTableService.create(this.generalTable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGeneralTable>>) {
        result.subscribe((res: HttpResponse<IGeneralTable>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
