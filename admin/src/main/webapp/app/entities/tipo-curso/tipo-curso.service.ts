import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoCurso } from 'app/shared/model/tipo-curso.model';

type EntityResponseType = HttpResponse<ITipoCurso>;
type EntityArrayResponseType = HttpResponse<ITipoCurso[]>;

@Injectable({ providedIn: 'root' })
export class TipoCursoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-cursos';

    constructor(private http: HttpClient) {}

    create(tipoCurso: ITipoCurso): Observable<EntityResponseType> {
        return this.http.post<ITipoCurso>(this.resourceUrl, tipoCurso, { observe: 'response' });
    }

    update(tipoCurso: ITipoCurso): Observable<EntityResponseType> {
        return this.http.put<ITipoCurso>(this.resourceUrl, tipoCurso, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoCurso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoCurso[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
