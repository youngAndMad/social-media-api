import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { KeycloakGuard } from "./guard/keycloak.guard";
import { RegistrationComponent } from "./components/registration/registration.component";
import { ArticleListComponent } from "./components/article/article-list/article-list.component";
import { ArticlePageComponent } from "./components/article/article-page/article-page.component";
import { ArticleResolver } from "./resolvers/article.resolver";

const routes: Routes = [
  { path: "", redirectTo: "articles", pathMatch: "full" },
  { path: "registration", component: RegistrationComponent },
  {
    path: "article/:id", component: ArticlePageComponent,
  },
  { path: "articles", component: ArticleListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
