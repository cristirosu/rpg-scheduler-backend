import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {$WebSocket} from './angular2-websockets';

import { AuthenticationService } from '../shared/services/authentication.service';
import { MdButton } from '@angular2-material/button';
import {WebSocketService} from "../shared/services/websocket.service";

import { ToastService } from '../shared/services/toast.service';
import {OnDestroy} from "../../node_modules/@angular/core/src/metadata/lifecycle_hooks";

@Component({
    templateUrl: 'app/auth/login.component.html',
    providers: [ AuthenticationService ],
})

export class LoginComponent implements OnInit,OnDestroy{
    model: any = {};
    loading = false;
    error = '';
    message = "";
    v1: string;

    constructor(
        private router: Router,
        private authService: AuthenticationService,
        private toastService: ToastService,
        public socketService : WebSocketService){
        console.log(2);
    }

    ngOnDestroy(){
        console.log(3);
    }

    ngOnInit() {
        console.log(1);
        this.authService.logout();
        //this.socketService.connect();
    }

    login() {
        this.loading = true;
        this.authService.login(this.model.username, this.model.password)
            .subscribe( result => {
                if(result === true){
                    //login succesful
                    this.router.navigate(['/']);
                        this.toastService.showSuccess("Succesful login!");
                }
            },
                (error) => {
                    console.log("dis error");
                    if(error.json() && error.json().error){
                        this.toastService.showError(error.json().error);
                    } else {
                        this.toastService.showError("An unexpected error has occured");
                    }
                })

    }



    testSend(topic: string){
        this.socketService.sendName(topic);
    }
}