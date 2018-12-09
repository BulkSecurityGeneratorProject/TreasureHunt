import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';
import { HuntMySuffixService } from './hunt-my-suffix.service';
import { HuntMySuffixComponent } from './hunt-my-suffix.component';
import { HuntMySuffixDetailComponent } from './hunt-my-suffix-detail.component';
import { HuntMySuffixUpdateComponent } from './hunt-my-suffix-update.component';
import { HuntMySuffixDeletePopupComponent } from './hunt-my-suffix-delete-dialog.component';
import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class HuntMySuffixResolve implements Resolve<IHuntMySuffix> {
    constructor(private service: HuntMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<HuntMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HuntMySuffix>) => response.ok),
                map((hunt: HttpResponse<HuntMySuffix>) => hunt.body)
            );
        }
        return of(new HuntMySuffix());
    }
}

export const huntRoute: Routes = [
    {
        path: 'hunt-my-suffix',
        component: HuntMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hunts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hunt-my-suffix/:id/view',
        component: HuntMySuffixDetailComponent,
        resolve: {
            hunt: HuntMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hunts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hunt-my-suffix/new',
        component: HuntMySuffixUpdateComponent,
        resolve: {
            hunt: HuntMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hunts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hunt-my-suffix/:id/edit',
        component: HuntMySuffixUpdateComponent,
        resolve: {
            hunt: HuntMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hunts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const huntPopupRoute: Routes = [
    {
        path: 'hunt-my-suffix/:id/delete',
        component: HuntMySuffixDeletePopupComponent,
        resolve: {
            hunt: HuntMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hunts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
