import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AdminCursoModule } from './curso/curso.module';
import { AdminDatosCursoModule } from './datos-curso/datos-curso.module';
import { AdminTemasCursoModule } from './temas-curso/temas-curso.module';
import { AdminBlogModule } from './blog/blog.module';
import { AdminHangoutModule } from './hangout/hangout.module';
import { AdminGeneralTableModule } from './general-table/general-table.module';
import { AdminModuleModule } from './module/module.module';
import { AdminPortadaModule } from './portada/portada.module';
import { AdminResetRegistryModule } from './reset-registry/reset-registry.module';
import { AdminTipoCursoModule } from './tipo-curso/tipo-curso.module';
import { AdminPermissionModule } from './permission/permission.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AdminCursoModule,
        AdminDatosCursoModule,
        AdminTemasCursoModule,
        AdminBlogModule,
        AdminHangoutModule,
        AdminGeneralTableModule,
        AdminModuleModule,
        AdminPortadaModule,
        AdminResetRegistryModule,
        AdminTipoCursoModule,
        AdminPermissionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminEntityModule {}
