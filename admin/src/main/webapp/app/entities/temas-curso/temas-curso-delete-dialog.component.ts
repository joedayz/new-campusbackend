import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITemasCurso } from 'app/shared/model/temas-curso.model';
import { TemasCursoService } from './temas-curso.service';

@Component({
    selector: 'jhi-temas-curso-delete-dialog',
    templateUrl: './temas-curso-delete-dialog.component.html'
})
export class TemasCursoDeleteDialogComponent {
    temasCurso: ITemasCurso;

    constructor(private temasCursoService: TemasCursoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.temasCursoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'temasCursoListModification',
                content: 'Deleted an temasCurso'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-temas-curso-delete-popup',
    template: ''
})
export class TemasCursoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ temasCurso }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TemasCursoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.temasCurso = temasCurso;
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
