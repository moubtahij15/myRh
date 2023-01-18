import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./Pages/login/login.component";
import {RegisterComponent} from "./Pages/register/register.component";
import {OffreComponent} from "./Pages/offre/offre.component";
import {AddOffreComponent} from "./Pages/add-offre/add-offre.component";
import {LoginAgentComponent} from "./Pages/login-agent/login-agent.component";
import {DashboardAgentComponent} from "./Pages/dashboard-agent/dashboard-agent.component";

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "", component: OffreComponent},
  {path: "register", component: RegisterComponent},
  {path: "offer", component: OffreComponent},
  {path: "addOffre", component: AddOffreComponent},
  {path: "dashboard", component: DashboardAgentComponent},
  {path: "login/agent", component: LoginAgentComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
