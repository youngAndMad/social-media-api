import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { KeycloakGuard } from "./guard/keycloak.guard";
import { RegistrationComponent } from "./components/registration/registration.component";

const routes: Routes = [
  { path: '', component: AppComponent, canActivate: [KeycloakGuard] },
  { path: 'registration', component: RegistrationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
