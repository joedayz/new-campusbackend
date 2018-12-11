import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoCurso } from 'app/shared/model/tipo-curso.model';

@Component({
    selector: 'jhi-tipo-curso-detail',
    templateUrl: './tipo-curso-detail.component.html'
})
export class TipoCursoDetailComponent implements OnInit {
    tipoCurso: ITipoCurso;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoCurso }) => {
            this.tipoCurso = tipoCurso;
        });
    }

    previousState() {
        window.history.back();
    }
}
