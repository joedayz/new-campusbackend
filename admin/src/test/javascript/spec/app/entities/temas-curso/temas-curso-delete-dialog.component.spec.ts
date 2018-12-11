/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AdminTestModule } from '../../../test.module';
import { TemasCursoDeleteDialogComponent } from 'app/entities/temas-curso/temas-curso-delete-dialog.component';
import { TemasCursoService } from 'app/entities/temas-curso/temas-curso.service';

describe('Component Tests', () => {
    describe('TemasCurso Management Delete Component', () => {
        let comp: TemasCursoDeleteDialogComponent;
        let fixture: ComponentFixture<TemasCursoDeleteDialogComponent>;
        let service: TemasCursoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TemasCursoDeleteDialogComponent]
            })
                .overrideTemplate(TemasCursoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TemasCursoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TemasCursoService);
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
