import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPermission } from 'app/shared/model/permission.model';
import { PermissionService } from './permission.service';
import { IModule } from 'app/shared/model/module.model';
import { ModuleService } from 'app/entities/module';

@Component({
    selector: 'jhi-permission-update',
    templateUrl: './permission-update.component.html'
})
export class PermissionUpdateComponent implements OnInit {
    permission: IPermission;
    isSaving: boolean;

    modules: IModule[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private permissionService: PermissionService,
        private moduleService: ModuleService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ permission }) => {
            this.permission = permission;
        });
        this.moduleService.query().subscribe(
            (res: HttpResponse<IModule[]>) => {
                this.modules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.permission.id !== undefined) {
            this.subscribeToSaveResponse(this.permissionService.update(this.permission));
        } else {
            this.subscribeToSaveResponse(this.permissionService.create(this.permission));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPermission>>) {
        result.subscribe((res: HttpResponse<IPermission>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackModuleById(index: number, item: IModule) {
        return item.id;
    }
}
