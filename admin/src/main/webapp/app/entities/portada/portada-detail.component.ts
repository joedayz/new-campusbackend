import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPortada } from 'app/shared/model/portada.model';

@Component({
    selector: 'jhi-portada-detail',
    templateUrl: './portada-detail.component.html'
})
export class PortadaDetailComponent implements OnInit {
    portada: IPortada;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ portada }) => {
            this.portada = portada;
        });
    }

    previousState() {
        window.history.back();
    }
}
