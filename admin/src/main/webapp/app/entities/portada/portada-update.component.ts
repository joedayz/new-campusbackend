import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPortada } from 'app/shared/model/portada.model';
import { PortadaService } from './portada.service';

@Component({
    selector: 'jhi-portada-update',
    templateUrl: './portada-update.component.html'
})
export class PortadaUpdateComponent implements OnInit {
    portada: IPortada;
    isSaving: boolean;

    constructor(private portadaService: PortadaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ portada }) => {
            this.portada = portada;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.portada.id !== undefined) {
            this.subscribeToSaveResponse(this.portadaService.update(this.portada));
        } else {
            this.subscribeToSaveResponse(this.portadaService.create(this.portada));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPortada>>) {
        result.subscribe((res: HttpResponse<IPortada>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
