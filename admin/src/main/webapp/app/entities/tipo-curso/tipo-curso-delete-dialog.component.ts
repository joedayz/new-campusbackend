import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoCurso } from 'app/shared/model/tipo-curso.model';
import { TipoCursoService } from './tipo-curso.service';

@Component({
    selector: 'jhi-tipo-curso-delete-dialog',
    templateUrl: './tipo-curso-delete-dialog.component.html'
})
export class TipoCursoDeleteDialogComponent {
    tipoCurso: ITipoCurso;

    constructor(private tipoCursoService: TipoCursoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoCursoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoCursoListModification',
                content: 'Deleted an tipoCurso'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-curso-delete-popup',
    template: ''
})
export class TipoCursoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoCurso }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoCursoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tipoCurso = tipoCurso;
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
