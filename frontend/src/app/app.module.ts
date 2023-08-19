import { APP_INITIALIZER, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { initializeKeycloak } from "./init/keycloak-init.factory";
import { HttpClientModule } from "@angular/common/http";
import { SidenavComponent } from './components/navigation/sidenav/sidenav.component';
import { ReactiveFormsModule } from "@angular/forms";
import { RegistrationComponent } from './components/registration/registration.component';
import { HeaderComponent } from './components/navigation/header/header.component';
import { MaterialModule } from "./material/material.module";
import { FlexModule } from "./flex/flex.module";
import { ArticleListComponent } from './components/article/article-list/article-list.component';
import { ArticlePageComponent } from './components/article/article-page/article-page.component';
import { FooterComponent } from './components/navigation/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HeaderComponent,
    SidenavComponent,
    ArticleListComponent,
    ArticlePageComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    HttpClientModule,
    ReactiveFormsModule,
    FlexModule,
    MaterialModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
