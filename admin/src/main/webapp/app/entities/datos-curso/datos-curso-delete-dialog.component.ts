import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDatosCurso } from 'app/shared/model/datos-curso.model';
import { DatosCursoService } from './datos-curso.service';

@Component({
    selector: 'jhi-datos-curso-delete-dialog',
    templateUrl: './datos-curso-delete-dialog.component.html'
})
export class DatosCursoDeleteDialogComponent {
    datosCurso: IDatosCurso;

    constructor(private datosCursoService: DatosCursoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.datosCursoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'datosCursoListModification',
                content: 'Deleted an datosCurso'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-datos-curso-delete-popup',
    template: ''
})
export class DatosCursoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ datosCurso }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DatosCursoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.datosCurso = datosCurso;
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
