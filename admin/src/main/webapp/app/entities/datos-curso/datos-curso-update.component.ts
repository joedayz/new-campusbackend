import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IDatosCurso } from 'app/shared/model/datos-curso.model';
import { DatosCursoService } from './datos-curso.service';
import { ICurso } from 'app/shared/model/curso.model';
import { CursoService } from 'app/entities/curso';

@Component({
    selector: 'jhi-datos-curso-update',
    templateUrl: './datos-curso-update.component.html'
})
export class DatosCursoUpdateComponent implements OnInit {
    datosCurso: IDatosCurso;
    isSaving: boolean;

    cursos: ICurso[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private datosCursoService: DatosCursoService,
        private cursoService: CursoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ datosCurso }) => {
            this.datosCurso = datosCurso;
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
        if (this.datosCurso.id !== undefined) {
            this.subscribeToSaveResponse(this.datosCursoService.update(this.datosCurso));
        } else {
            this.subscribeToSaveResponse(this.datosCursoService.create(this.datosCurso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDatosCurso>>) {
        result.subscribe((res: HttpResponse<IDatosCurso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
