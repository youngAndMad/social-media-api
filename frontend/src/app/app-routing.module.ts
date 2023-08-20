import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegistrationComponent } from "./components/registration/registration.component";
import { ArticleListComponent } from "./components/article/article-list/article-list.component";
import { ArticlePageComponent } from "./components/article/article-page/article-page.component";
import { ProfileComponent } from "./components/user/profile/profile.component";
import { EditProfileComponent } from "./components/user/edit-profile/edit-profile.component";

const routes: Routes = [
  { path: "", redirectTo: "articles", pathMatch: "full" },
  { path: "registration", component: RegistrationComponent },
  { path: "article/:id", component: ArticlePageComponent },
  { path: "articles", component: ArticleListComponent },
  { path: "profile", component: ProfileComponent },
  { path: "edit-profile", component: EditProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
