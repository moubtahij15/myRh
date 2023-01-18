import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginFormComponent} from './Components/loginForm/loginForm.component';
import {RegistrationComponent} from './Components/registration/registration.component';
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './Components/header/header.component';
import {RegisterComponent} from './Pages/register/register.component';
import {LoginComponent} from "./Pages/login/login.component";
import {NgOptimizedImage} from "@angular/common";
import { SearchBarComponent } from './Components/search-bar/search-bar.component';
import { OffreDetailsComponent } from './Components/offre-details/offre-details.component';
import {OffreComponent} from "./Pages/offre/offre.component";
import { ModalOffreDetailsComponent } from './Components/modal-offre-details/modal-offre-details.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";

import { MatDialogModule } from '@angular/material/dialog';
import { AddOffreFormComponent } from './Components/add-offre-form/add-offre-form.component';
import { AddOffreComponent } from './Pages/add-offre/add-offre.component';
import { LoginAgentComponent } from './Pages/login-agent/login-agent.component';
import { DashboardAgentComponent } from './Pages/dashboard-agent/dashboard-agent.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    OffreComponent,
    RegistrationComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    SearchBarComponent,
    OffreDetailsComponent,
    ModalOffreDetailsComponent,
    AddOffreFormComponent,
    AddOffreComponent,
    LoginAgentComponent,
    DashboardAgentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgOptimizedImage,
    FormsModule,
    NoopAnimationsModule,
    MatDialogModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
