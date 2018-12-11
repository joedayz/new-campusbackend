import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITemasCurso } from 'app/shared/model/temas-curso.model';

type EntityResponseType = HttpResponse<ITemasCurso>;
type EntityArrayResponseType = HttpResponse<ITemasCurso[]>;

@Injectable({ providedIn: 'root' })
export class TemasCursoService {
    public resourceUrl = SERVER_API_URL + 'api/temas-cursos';

    constructor(private http: HttpClient) {}

    create(temasCurso: ITemasCurso): Observable<EntityResponseType> {
        return this.http.post<ITemasCurso>(this.resourceUrl, temasCurso, { observe: 'response' });
    }

    update(temasCurso: ITemasCurso): Observable<EntityResponseType> {
        return this.http.put<ITemasCurso>(this.resourceUrl, temasCurso, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITemasCurso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITemasCurso[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
