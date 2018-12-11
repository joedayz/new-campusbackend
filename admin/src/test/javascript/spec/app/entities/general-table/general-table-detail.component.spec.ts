/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { GeneralTableDetailComponent } from 'app/entities/general-table/general-table-detail.component';
import { GeneralTable } from 'app/shared/model/general-table.model';

describe('Component Tests', () => {
    describe('GeneralTable Management Detail Component', () => {
        let comp: GeneralTableDetailComponent;
        let fixture: ComponentFixture<GeneralTableDetailComponent>;
        const route = ({ data: of({ generalTable: new GeneralTable(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [GeneralTableDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GeneralTableDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeneralTableDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.generalTable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
