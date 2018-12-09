import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

type EntityResponseType = HttpResponse<IHuntMySuffix>;
type EntityArrayResponseType = HttpResponse<IHuntMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class HuntMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/hunts';

    constructor(private http: HttpClient) {}

    create(hunt: IHuntMySuffix): Observable<EntityResponseType> {
        return this.http.post<IHuntMySuffix>(this.resourceUrl, hunt, { observe: 'response' });
    }

    update(hunt: IHuntMySuffix): Observable<EntityResponseType> {
        return this.http.put<IHuntMySuffix>(this.resourceUrl, hunt, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IHuntMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHuntMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
