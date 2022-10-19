import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JerseysComponent } from './jerseys/jerseys.component';
import { CustDashboardComponent } from './cust-dashboard/cust-dashboard.component';
import { JerseyDetailComponent } from './jersey-detail/jersey-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: CustDashboardComponent },
  { path: 'detail/:id', component: JerseyDetailComponent },
  { path: 'jerseys', component: JerseysComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
