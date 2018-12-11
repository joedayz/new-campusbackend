import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResetRegistry } from 'app/shared/model/reset-registry.model';
import { ResetRegistryService } from './reset-registry.service';

@Component({
    selector: 'jhi-reset-registry-delete-dialog',
    templateUrl: './reset-registry-delete-dialog.component.html'
})
export class ResetRegistryDeleteDialogComponent {
    resetRegistry: IResetRegistry;

    constructor(
        private resetRegistryService: ResetRegistryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resetRegistryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resetRegistryListModification',
                content: 'Deleted an resetRegistry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-reset-registry-delete-popup',
    template: ''
})
export class ResetRegistryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resetRegistry }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResetRegistryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resetRegistry = resetRegistry;
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
