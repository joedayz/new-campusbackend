import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDatosCurso } from 'app/shared/model/datos-curso.model';

type EntityResponseType = HttpResponse<IDatosCurso>;
type EntityArrayResponseType = HttpResponse<IDatosCurso[]>;

@Injectable({ providedIn: 'root' })
export class DatosCursoService {
    public resourceUrl = SERVER_API_URL + 'api/datos-cursos';

    constructor(private http: HttpClient) {}

    create(datosCurso: IDatosCurso): Observable<EntityResponseType> {
        return this.http.post<IDatosCurso>(this.resourceUrl, datosCurso, { observe: 'response' });
    }

    update(datosCurso: IDatosCurso): Observable<EntityResponseType> {
        return this.http.put<IDatosCurso>(this.resourceUrl, datosCurso, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDatosCurso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDatosCurso[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
