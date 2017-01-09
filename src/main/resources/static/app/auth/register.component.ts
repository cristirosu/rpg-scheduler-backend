import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { AuthenticationService } from '../shared/services/authentication.service';
import { RegisterRequest } from "../models/register.request";

@Component({
    templateUrl: "app/auth/register.component.html",
    providers: [
        AuthenticationService
    ]
})

export class RegisterComponent {

    form: FormGroup;
    message: string;

    constructor(fb: FormBuilder, private authService: AuthenticationService, private router: Router, ) {
        this.form = fb.group({
            "firstName": ["", [Validators.required, Validators.minLength(3)]],
            "lastName": ["", [Validators.required, Validators.minLength(3)]],
            "email": ["", [Validators.required, this.validEmail]],
            "phoneNumber": ["", [Validators.minLength(10), Validators.maxLength(10), this.validPhone]],
            'passwords': fb.group({
                "password": ["", [Validators.required, Validators.minLength(5)]],
                "matchPassword": ["", [Validators.required, Validators.minLength(5)]]
            }, { validator: this.areEqual('password', 'matchPassword') })

        });
    }

    onSubmitModelBased(model: RegisterRequest) {
        console.log(model);
        this.authService.register({ firstName: model.firstName, lastName: model.lastName, email: model.email, phoneNumber: model.phoneNumber, password: model.passwords.password })
            .subscribe(
            (msg) => {
                console.log(msg);
                console.log("hihihi");
                this.message = "Succesfully registered!";
                let link = ['/login'];
                this.router.navigate(link);
            },
            (error) => {
                console.log("dis error");
                if (error.json() && error.json().errorMessage) {
                    this.message = error.json().errorMessage;
                } else {
                    this.message = "An unexpected error has occured";
                }
            }
            );
        console.log(3);
        console.log("model-based form submitted");
        console.log(this.form);
    }



    validEmail(c: FormControl) {
        let EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
        console.log(c.value);
        return EMAIL_REGEXP.test(c.value) ? null : {
            validateEmail: {
                valid: false
            }
        };
    }

    validPhone(c: FormControl) {
        let NUMBERS_REGEXP = /^\d+$/;
        if (!NUMBERS_REGEXP.test(c.value) || !(c.value.indexOf("07") === 0)) {
            return {
                validatePhone: {
                    valid: false
                }
            }
        }

        return null;
    }

    areEqual(passwordKey: string, confirmPasswordKey: string) {

        return (group: FormGroup): { [key: string]: any } => {
            let password = group.controls[passwordKey];
            let confirmPassword = group.controls[confirmPasswordKey];

            if (password.value !== confirmPassword.value) {
                return {
                    mismatchedPasswords: {
                        valid: false
                    }
                };
            }
        }

    }

}