import { Component } from '@angular/core';

import {AuthenticationService} from "./shared/services/authentication.service";
import {UserService} from "./shared/services/user.service";
import {OnInit} from "../node_modules/@angular/core/src/metadata/lifecycle_hooks";
import {WebSocketService} from "./shared/services/websocket.service"
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import {ViewContainerRef}  from '@angular/core';

@Component({
    selector: 'app',
    templateUrl: 'app/app.component.html'
})
export class AppComponent  {

  constructor(public toastr: ToastsManager, vRef: ViewContainerRef) {
        this.toastr.setRootViewContainerRef(vRef);
    }

 }
