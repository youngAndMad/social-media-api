import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { KeycloakGuard } from "./guard/keycloak.guard";
import { RegistrationComponent } from "./components/registration/registration.component";
import { ArticleListComponent } from "./components/article/article-list/article-list.component";

const routes: Routes = [
  { path: "",redirectTo: 'articles',pathMatch:'full'},
  { path: "registration", component: RegistrationComponent },
  { path: "articles", component: ArticleListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
