import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICurso } from 'app/shared/model/curso.model';
import { CursoService } from './curso.service';
import { ITipoCurso } from 'app/shared/model/tipo-curso.model';
import { TipoCursoService } from 'app/entities/tipo-curso';

@Component({
    selector: 'jhi-curso-update',
    templateUrl: './curso-update.component.html'
})
export class CursoUpdateComponent implements OnInit {
    curso: ICurso;
    isSaving: boolean;

    tipocursos: ITipoCurso[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private cursoService: CursoService,
        private tipoCursoService: TipoCursoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ curso }) => {
            this.curso = curso;
        });
        this.tipoCursoService.query().subscribe(
            (res: HttpResponse<ITipoCurso[]>) => {
                this.tipocursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.curso.id !== undefined) {
            this.subscribeToSaveResponse(this.cursoService.update(this.curso));
        } else {
            this.subscribeToSaveResponse(this.cursoService.create(this.curso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICurso>>) {
        result.subscribe((res: HttpResponse<ICurso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTipoCursoById(index: number, item: ITipoCurso) {
        return item.id;
    }
}
