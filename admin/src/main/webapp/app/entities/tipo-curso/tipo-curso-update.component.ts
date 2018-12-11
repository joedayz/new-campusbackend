import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITipoCurso } from 'app/shared/model/tipo-curso.model';
import { TipoCursoService } from './tipo-curso.service';

@Component({
    selector: 'jhi-tipo-curso-update',
    templateUrl: './tipo-curso-update.component.html'
})
export class TipoCursoUpdateComponent implements OnInit {
    tipoCurso: ITipoCurso;
    isSaving: boolean;

    constructor(private tipoCursoService: TipoCursoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoCurso }) => {
            this.tipoCurso = tipoCurso;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoCurso.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoCursoService.update(this.tipoCurso));
        } else {
            this.subscribeToSaveResponse(this.tipoCursoService.create(this.tipoCurso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITipoCurso>>) {
        result.subscribe((res: HttpResponse<ITipoCurso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
