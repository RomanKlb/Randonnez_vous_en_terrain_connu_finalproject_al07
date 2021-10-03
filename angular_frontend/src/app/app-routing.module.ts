import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WeatherListComponent } from './components/api-weather-nodejs/weather-list/weather-list.component';
import { AddUserComponent } from './components/api-user-java/add-user/add-user.component';
import { UserDetailsComponent } from './components/api-user-java/user-details/user-details.component';
import { UsersListComponent } from './components/api-user-java/users-list/users-list.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardUserComponent } from './components/dashboard-user/dashboard-user.component';
import { DashboardAdminComponent } from './components/dashboard-admin/dashboard-admin.component';
import { AddUserAdminComponent } from './components/api-user-java/add-user-admin/add-user-admin.component';
import { NotAdminComponent } from './components/error/not-admin/not-admin.component';
import { HomeComponent } from './components/home/home.component';
import { NotConnectingComponent } from './components/error/not-connecting/not-connecting.component';
import { AddInterestpointComponent } from './components/api-interestpoint-java/add-interestpoint/add-interestpoint.component';
import { ListInterestpointComponent } from './components/api-interestpoint-java/list-interestpoint/list-interestpoint.component';
import { CarteHomeComponent } from './components/cartes/carte-home/carte-home.component';
import { AdminListcontrolIpComponent } from './components/api-interestpoint-java/admin-listcontrol-ip/admin-listcontrol-ip.component';


const routes: Routes = [
  // { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'dashboard-admin/users', component: UsersListComponent },
  { path: 'dashboard-admin/interestpoints', component: AdminListcontrolIpComponent},
  { path: 'dashboard-user/:id', component: UserDetailsComponent },
  { path: 'dashboard-user/:email', component: UserDetailsComponent },
  { path: 'login/add-user', component: AddUserComponent },
  { path: 'dashboard-admin/add-user-admin', component: AddUserAdminComponent},
  { path: 'weathers', component: WeatherListComponent },
  { path: 'login', component: LoginComponent},
  { path: 'dashboard-user', component: DashboardUserComponent},
  { path: 'dashboard-admin', component: DashboardAdminComponent},
  { path: 'error-not-admin', component: NotAdminComponent},
  { path: 'error-not-connecting', component: NotConnectingComponent},
  { path: '', component: HomeComponent},
  { path: 'dashboard-admin/add-interestpoint', component: AddInterestpointComponent},
  { path: 'excursion', component: ListInterestpointComponent},
  { path: 'carte', component: CarteHomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
