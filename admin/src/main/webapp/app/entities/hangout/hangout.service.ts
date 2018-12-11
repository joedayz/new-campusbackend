import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHangout } from 'app/shared/model/hangout.model';

type EntityResponseType = HttpResponse<IHangout>;
type EntityArrayResponseType = HttpResponse<IHangout[]>;

@Injectable({ providedIn: 'root' })
export class HangoutService {
    public resourceUrl = SERVER_API_URL + 'api/hangouts';

    constructor(private http: HttpClient) {}

    create(hangout: IHangout): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(hangout);
        return this.http
            .post<IHangout>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(hangout: IHangout): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(hangout);
        return this.http
            .put<IHangout>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHangout>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHangout[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(hangout: IHangout): IHangout {
        const copy: IHangout = Object.assign({}, hangout, {
            fecha: hangout.fecha != null && hangout.fecha.isValid() ? hangout.fecha.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((hangout: IHangout) => {
                hangout.fecha = hangout.fecha != null ? moment(hangout.fecha) : null;
            });
        }
        return res;
    }
}
