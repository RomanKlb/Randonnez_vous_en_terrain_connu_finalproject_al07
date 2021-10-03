import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UsersListComponent } from './components/api-user-java/users-list/users-list.component';
import { UserDetailsComponent } from './components/api-user-java/user-details/user-details.component';
import { AddUserComponent } from './components/api-user-java/add-user/add-user.component'
import { WeatherListComponent } from './components/api-weather-nodejs/weather-list/weather-list.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { DashboardUserComponent } from './components/dashboard-user/dashboard-user.component';
import { DashboardAdminComponent } from './components/dashboard-admin/dashboard-admin.component';
import { AddUserAdminComponent } from './components/api-user-java/add-user-admin/add-user-admin.component';
import { NotAdminComponent } from './components/error/not-admin/not-admin.component';
import { HomeComponent } from './components/home/home.component';
import { NotConnectingComponent } from './components/error/not-connecting/not-connecting.component';
import { CarteHomeComponent } from './components/cartes/carte-home/carte-home.component';
import { AddInterestpointComponent } from './components/api-interestpoint-java/add-interestpoint/add-interestpoint.component';
import { ListInterestpointComponent } from './components/api-interestpoint-java/list-interestpoint/list-interestpoint.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatSliderModule } from '@angular/material/slider';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { NgBootstrapFormValidationModule } from 'ng-bootstrap-form-validation';
import { LogoGestAdminComponent } from './components/logo-gest-admin/logo-gest-admin.component';
import { LogoGestIpComponent } from './components/logo-gest-ip/logo-gest-ip.component';
import { AdminListcontrolIpComponent } from './components/api-interestpoint-java/admin-listcontrol-ip/admin-listcontrol-ip.component';
import { AboutmeComponent } from './components/aboutme/aboutme.component';


@NgModule({
  declarations: [
    AppComponent,
    UsersListComponent,
    UserDetailsComponent,
    AddUserComponent,
    WeatherListComponent,
    LoginComponent,
    HeaderComponent,
    DashboardUserComponent,
    DashboardAdminComponent,
    AddUserAdminComponent,
    NotAdminComponent,
    HomeComponent,
    NotConnectingComponent,
    CarteHomeComponent,
    AddInterestpointComponent,
    ListInterestpointComponent,
    LogoGestAdminComponent,
    LogoGestIpComponent,
    AdminListcontrolIpComponent,
    AboutmeComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    MatSliderModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    NgBootstrapFormValidationModule.forRoot(),
    NgBootstrapFormValidationModule
    ],
  providers: [  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
