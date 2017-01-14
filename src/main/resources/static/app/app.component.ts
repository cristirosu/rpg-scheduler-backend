import { Component } from '@angular/core';

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
