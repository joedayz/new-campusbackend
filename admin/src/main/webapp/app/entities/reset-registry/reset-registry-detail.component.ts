import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResetRegistry } from 'app/shared/model/reset-registry.model';

@Component({
    selector: 'jhi-reset-registry-detail',
    templateUrl: './reset-registry-detail.component.html'
})
export class ResetRegistryDetailComponent implements OnInit {
    resetRegistry: IResetRegistry;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resetRegistry }) => {
            this.resetRegistry = resetRegistry;
        });
    }

    previousState() {
        window.history.back();
    }
}
