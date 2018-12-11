import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResetRegistry } from 'app/shared/model/reset-registry.model';

type EntityResponseType = HttpResponse<IResetRegistry>;
type EntityArrayResponseType = HttpResponse<IResetRegistry[]>;

@Injectable({ providedIn: 'root' })
export class ResetRegistryService {
    public resourceUrl = SERVER_API_URL + 'api/reset-registries';

    constructor(private http: HttpClient) {}

    create(resetRegistry: IResetRegistry): Observable<EntityResponseType> {
        return this.http.post<IResetRegistry>(this.resourceUrl, resetRegistry, { observe: 'response' });
    }

    update(resetRegistry: IResetRegistry): Observable<EntityResponseType> {
        return this.http.put<IResetRegistry>(this.resourceUrl, resetRegistry, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResetRegistry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResetRegistry[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
