import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITemasCurso } from 'app/shared/model/temas-curso.model';

@Component({
    selector: 'jhi-temas-curso-detail',
    templateUrl: './temas-curso-detail.component.html'
})
export class TemasCursoDetailComponent implements OnInit {
    temasCurso: ITemasCurso;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ temasCurso }) => {
            this.temasCurso = temasCurso;
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
