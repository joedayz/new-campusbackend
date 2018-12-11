import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeneralTable } from 'app/shared/model/general-table.model';

@Component({
    selector: 'jhi-general-table-detail',
    templateUrl: './general-table-detail.component.html'
})
export class GeneralTableDetailComponent implements OnInit {
    generalTable: IGeneralTable;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ generalTable }) => {
            this.generalTable = generalTable;
        });
    }

    previousState() {
        window.history.back();
    }
}
