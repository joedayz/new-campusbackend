/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { ResetRegistryDetailComponent } from 'app/entities/reset-registry/reset-registry-detail.component';
import { ResetRegistry } from 'app/shared/model/reset-registry.model';

describe('Component Tests', () => {
    describe('ResetRegistry Management Detail Component', () => {
        let comp: ResetRegistryDetailComponent;
        let fixture: ComponentFixture<ResetRegistryDetailComponent>;
        const route = ({ data: of({ resetRegistry: new ResetRegistry(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [ResetRegistryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ResetRegistryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResetRegistryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.resetRegistry).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
