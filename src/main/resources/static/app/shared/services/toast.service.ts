import { Injectable } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Injectable()
export class ToastService {
    constructor(public toastr: ToastsManager){}

    showSuccess(message: string) {
        this.toastr.success(message);
      }

    showError(message: string){
        this.toastr.error(message);
    }

    showInfo(message: string){
        this.toastr.info(message);
    }

    showCustom(message: string){
        this.toastr.custom('<span style="color: red">Message in red.</span>', null, {enableHTML: true});
    }

}