import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeneralTable } from 'app/shared/model/general-table.model';
import { GeneralTableService } from './general-table.service';

@Component({
    selector: 'jhi-general-table-delete-dialog',
    templateUrl: './general-table-delete-dialog.component.html'
})
export class GeneralTableDeleteDialogComponent {
    generalTable: IGeneralTable;

    constructor(
        private generalTableService: GeneralTableService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.generalTableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'generalTableListModification',
                content: 'Deleted an generalTable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-general-table-delete-popup',
    template: ''
})
export class GeneralTableDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ generalTable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GeneralTableDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.generalTable = generalTable;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
