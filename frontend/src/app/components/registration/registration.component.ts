import { Component, inject } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Gender } from "../../domain/enums/Gender";
import { UserService } from "../../service/user.service";

@Component({
  selector: "app-registration",
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.scss"]
})
export class RegistrationComponent {
  hide = true;

  registrationFrom: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {
    this.registrationFrom = formBuilder.group({
      firstName: ["", [Validators.required,
        Validators.minLength(2)
      ]],
      lastName: ["", [Validators.required,
        Validators.minLength(2)
      ]],
      gender: [Gender.MALE, Validators.required],
      status: [""],
      isPrivateAccount: [false],
      address: formBuilder.group({
        country: ["", Validators.required],
        city: ["", Validators.required],
        street: ["", Validators.required],
        houseNumber: ["", Validators.required]
      })
    });
  }

  registration() {
    this.userService
      .register(this.registrationFrom.value)
      .subscribe(console.log);
  }

  get addressForm() {
    return this.registrationFrom.get("address") as FormGroup;
  }

  get form() {
    return this.registrationFrom;
  }

  canContinue(): boolean {
    return this.registrationFrom.get("firstName")!.valid &&
      this.registrationFrom.get("lastName")!.valid;
  }

}
