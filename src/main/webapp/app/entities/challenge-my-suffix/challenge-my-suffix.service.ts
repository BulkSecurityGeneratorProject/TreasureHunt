import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';

type EntityResponseType = HttpResponse<IChallengeMySuffix>;
type EntityArrayResponseType = HttpResponse<IChallengeMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ChallengeMySuffixService {
    public resourceUrl = SERVER_API_URL + 'api/challenges';

    constructor(private http: HttpClient) {}

    create(challenge: IChallengeMySuffix): Observable<EntityResponseType> {
        return this.http.post<IChallengeMySuffix>(this.resourceUrl, challenge, { observe: 'response' });
    }

    update(challenge: IChallengeMySuffix): Observable<EntityResponseType> {
        return this.http.put<IChallengeMySuffix>(this.resourceUrl, challenge, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IChallengeMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChallengeMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
