import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGeneralTable } from 'app/shared/model/general-table.model';

type EntityResponseType = HttpResponse<IGeneralTable>;
type EntityArrayResponseType = HttpResponse<IGeneralTable[]>;

@Injectable({ providedIn: 'root' })
export class GeneralTableService {
    public resourceUrl = SERVER_API_URL + 'api/general-tables';

    constructor(private http: HttpClient) {}

    create(generalTable: IGeneralTable): Observable<EntityResponseType> {
        return this.http.post<IGeneralTable>(this.resourceUrl, generalTable, { observe: 'response' });
    }

    update(generalTable: IGeneralTable): Observable<EntityResponseType> {
        return this.http.put<IGeneralTable>(this.resourceUrl, generalTable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGeneralTable>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGeneralTable[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
