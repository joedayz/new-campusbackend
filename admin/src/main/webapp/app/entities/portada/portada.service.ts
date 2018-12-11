import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPortada } from 'app/shared/model/portada.model';

type EntityResponseType = HttpResponse<IPortada>;
type EntityArrayResponseType = HttpResponse<IPortada[]>;

@Injectable({ providedIn: 'root' })
export class PortadaService {
    public resourceUrl = SERVER_API_URL + 'api/portadas';

    constructor(private http: HttpClient) {}

    create(portada: IPortada): Observable<EntityResponseType> {
        return this.http.post<IPortada>(this.resourceUrl, portada, { observe: 'response' });
    }

    update(portada: IPortada): Observable<EntityResponseType> {
        return this.http.put<IPortada>(this.resourceUrl, portada, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPortada>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPortada[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
