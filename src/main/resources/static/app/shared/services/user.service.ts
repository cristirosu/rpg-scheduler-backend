import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';

import { AuthenticationService } from './authentication.service';
import { User } from '../../models/user.model';
import { AppSettings } from '../services/app.settings';  

@Injectable()
export class UserService {
    // TODO: ADD OBSERVABLE, SAVE USER, SAVE TOKEN
    
    constructor(private _http: Http, private authService: AuthenticationService) { }
    
    getUser() {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });

        return <Observable<User>>this._http
            .get(AppSettings.API_URL + '/user', options)
            .map((response: Response) => <User>response.json())
            .catch(this.handleError); 
    }

    private handleError(error: Response) {
        let msg = `Status code ${error.status} on url ${error.url}`;
        console.log(msg);
        return Observable.throw(msg);
    }
}