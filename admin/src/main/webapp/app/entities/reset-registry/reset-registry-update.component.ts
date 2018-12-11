import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IResetRegistry } from 'app/shared/model/reset-registry.model';
import { ResetRegistryService } from './reset-registry.service';

@Component({
    selector: 'jhi-reset-registry-update',
    templateUrl: './reset-registry-update.component.html'
})
export class ResetRegistryUpdateComponent implements OnInit {
    resetRegistry: IResetRegistry;
    isSaving: boolean;

    constructor(private resetRegistryService: ResetRegistryService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resetRegistry }) => {
            this.resetRegistry = resetRegistry;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resetRegistry.id !== undefined) {
            this.subscribeToSaveResponse(this.resetRegistryService.update(this.resetRegistry));
        } else {
            this.subscribeToSaveResponse(this.resetRegistryService.create(this.resetRegistry));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResetRegistry>>) {
        result.subscribe((res: HttpResponse<IResetRegistry>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
