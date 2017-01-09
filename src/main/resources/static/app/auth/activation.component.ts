import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Location }                 from '@angular/common';

import { AuthenticationService } from '../shared/services/authentication.service';


@Component({
    template: ' :))))))))))) ',
    providers: [ AuthenticationService ],
})

export class ActivationComponent implements OnInit{

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private authService: AuthenticationService){
    }

    ngOnInit() {
        console.log("idgaf");
        this.route.params.forEach( (params: Params) => {
            let id = +params['id'];
            console.log(id);
            this.authService.activate(id)
                .subscribe( () => {
                    console.log("hereee");
                    let link = ['/login'];
                    this.router.navigate(link);
                });
        } )
    }


}