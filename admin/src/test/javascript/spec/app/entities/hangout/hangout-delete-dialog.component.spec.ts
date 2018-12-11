/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AdminTestModule } from '../../../test.module';
import { HangoutDeleteDialogComponent } from 'app/entities/hangout/hangout-delete-dialog.component';
import { HangoutService } from 'app/entities/hangout/hangout.service';

describe('Component Tests', () => {
    describe('Hangout Management Delete Component', () => {
        let comp: HangoutDeleteDialogComponent;
        let fixture: ComponentFixture<HangoutDeleteDialogComponent>;
        let service: HangoutService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [HangoutDeleteDialogComponent]
            })
                .overrideTemplate(HangoutDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HangoutDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HangoutService);
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
