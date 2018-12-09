import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';
import { ChallengeMySuffixService } from './challenge-my-suffix.service';
import { ChallengeMySuffixComponent } from './challenge-my-suffix.component';
import { ChallengeMySuffixDetailComponent } from './challenge-my-suffix-detail.component';
import { ChallengeMySuffixUpdateComponent } from './challenge-my-suffix-update.component';
import { ChallengeMySuffixDeletePopupComponent } from './challenge-my-suffix-delete-dialog.component';
import { IChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ChallengeMySuffixResolve implements Resolve<IChallengeMySuffix> {
    constructor(private service: ChallengeMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ChallengeMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ChallengeMySuffix>) => response.ok),
                map((challenge: HttpResponse<ChallengeMySuffix>) => challenge.body)
            );
        }
        return of(new ChallengeMySuffix());
    }
}

export const challengeRoute: Routes = [
    {
        path: 'challenge-my-suffix',
        component: ChallengeMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Challenges'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'challenge-my-suffix/:id/view',
        component: ChallengeMySuffixDetailComponent,
        resolve: {
            challenge: ChallengeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Challenges'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'challenge-my-suffix/new',
        component: ChallengeMySuffixUpdateComponent,
        resolve: {
            challenge: ChallengeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Challenges'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'challenge-my-suffix/:id/edit',
        component: ChallengeMySuffixUpdateComponent,
        resolve: {
            challenge: ChallengeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Challenges'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const challengePopupRoute: Routes = [
    {
        path: 'challenge-my-suffix/:id/delete',
        component: ChallengeMySuffixDeletePopupComponent,
        resolve: {
            challenge: ChallengeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Challenges'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
