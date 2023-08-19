import { KeycloakService } from "keycloak-angular";
import * as env from "../config/config";

export function initializeKeycloak(
  keycloak: KeycloakService
) {
  return () =>
    keycloak.init({
        config: {
          url: env.KEYCLOAK_URL,
          realm: env.KEYCLOAK_REALM,
          clientId: env.KEYCLOAK_CLIENT
        },
        initOptions: {
          redirectUri: "http://localhost:4200",
          checkLoginIframe: false
        },
        bearerPrefix: env.KEYCLOAK_BEARER_PREFIX,
        loadUserProfileAtStartUp: true
      }
    );
}
