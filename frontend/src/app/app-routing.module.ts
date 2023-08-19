import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegistrationComponent } from "./components/registration/registration.component";
import { ArticleListComponent } from "./components/article/article-list/article-list.component";
import { ArticlePageComponent } from "./components/article/article-page/article-page.component";

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
