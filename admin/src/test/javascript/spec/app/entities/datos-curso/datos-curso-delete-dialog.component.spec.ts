/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AdminTestModule } from '../../../test.module';
import { DatosCursoDeleteDialogComponent } from 'app/entities/datos-curso/datos-curso-delete-dialog.component';
import { DatosCursoService } from 'app/entities/datos-curso/datos-curso.service';

describe('Component Tests', () => {
    describe('DatosCurso Management Delete Component', () => {
        let comp: DatosCursoDeleteDialogComponent;
        let fixture: ComponentFixture<DatosCursoDeleteDialogComponent>;
        let service: DatosCursoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [DatosCursoDeleteDialogComponent]
            })
                .overrideTemplate(DatosCursoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DatosCursoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatosCursoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
