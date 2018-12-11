import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDatosCurso } from 'app/shared/model/datos-curso.model';

@Component({
    selector: 'jhi-datos-curso-detail',
    templateUrl: './datos-curso-detail.component.html'
})
export class DatosCursoDetailComponent implements OnInit {
    datosCurso: IDatosCurso;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ datosCurso }) => {
            this.datosCurso = datosCurso;
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
