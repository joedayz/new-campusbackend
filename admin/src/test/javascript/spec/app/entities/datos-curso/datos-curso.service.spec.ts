/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { DatosCursoService } from 'app/entities/datos-curso/datos-curso.service';
import { IDatosCurso, DatosCurso } from 'app/shared/model/datos-curso.model';

describe('Service Tests', () => {
    describe('DatosCurso Service', () => {
        let injector: TestBed;
        let service: DatosCursoService;
        let httpMock: HttpTestingController;
        let elemDefault: IDatosCurso;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DatosCursoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new DatosCurso(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a DatosCurso', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new DatosCurso(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DatosCurso', async () => {
                const returnedFromService = Object.assign(
                    {
                        descripcion: 'BBBBBB',
                        instructor: 'BBBBBB',
                        metodologia: 'BBBBBB',
                        participantes: 'BBBBBB',
                        requisitos: 'BBBBBB',
                        syllabus: 'BBBBBB',
                        costo: 'BBBBBB',
                        duracion: 'BBBBBB',
                        fechaInscripcion: 'BBBBBB',
                        horarios: 'BBBBBB',
                        numeroParticipantes: 'BBBBBB',
                        membresiaAnual: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of DatosCurso', async () => {
                const returnedFromService = Object.assign(
                    {
                        descripcion: 'BBBBBB',
                        instructor: 'BBBBBB',
                        metodologia: 'BBBBBB',
                        participantes: 'BBBBBB',
                        requisitos: 'BBBBBB',
                        syllabus: 'BBBBBB',
                        costo: 'BBBBBB',
                        duracion: 'BBBBBB',
                        fechaInscripcion: 'BBBBBB',
                        horarios: 'BBBBBB',
                        numeroParticipantes: 'BBBBBB',
                        membresiaAnual: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a DatosCurso', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
