import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITemasCurso } from 'app/shared/model/temas-curso.model';
import { TemasCursoService } from './temas-curso.service';
import { ICurso } from 'app/shared/model/curso.model';
import { CursoService } from 'app/entities/curso';

@Component({
    selector: 'jhi-temas-curso-update',
    templateUrl: './temas-curso-update.component.html'
})
export class TemasCursoUpdateComponent implements OnInit {
    temasCurso: ITemasCurso;
    isSaving: boolean;

    cursos: ICurso[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private temasCursoService: TemasCursoService,
        private cursoService: CursoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ temasCurso }) => {
            this.temasCurso = temasCurso;
        });
        this.cursoService.query().subscribe(
            (res: HttpResponse<ICurso[]>) => {
                this.cursos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.temasCurso.id !== undefined) {
            this.subscribeToSaveResponse(this.temasCursoService.update(this.temasCurso));
        } else {
            this.subscribeToSaveResponse(this.temasCursoService.create(this.temasCurso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITemasCurso>>) {
        result.subscribe((res: HttpResponse<ITemasCurso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCursoById(index: number, item: ICurso) {
        return item.id;
    }
}
