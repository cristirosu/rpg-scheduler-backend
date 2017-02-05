import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';

import { Friend } from '../../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/friend.model';
import { AuthenticationService } from './authentication.service';
import { AppSettings } from '../services/app.settings';


@Injectable()
export class FriendsService {
    constructor(private _http: Http, private authService: AuthenticationService) { }
    
    getFriends() {
        let headers = new Headers( { 'Authorization': this.authService.token  });
        let options = new RequestOptions({ headers: headers });

        console.log(AppSettings.API_URL + " ba boule");

        return this._http.get(AppSettings.API_URL + '/friends', options)
            .map((response: Response) => <Friend[]>response.json())
            .catch(this.handleError); 
    }

    deleteFriend(friend: Friend) {
        let headers = new Headers( { 'Authorization': this.authService.token  });
        let options = new RequestOptions({ headers: headers });

        return this._http.delete(AppSettings.API_URL + '/friends/' + friend.id, options)
            .map((response: Response) => response.status)
            .catch(this.handleError); 
    }
    addFriend(email: string){
        let headers = new Headers( { 'Authorization': this.authService.token  });
        let options = new RequestOptions({ headers: headers });

        return this._http.post(AppSettings.API_URL + '/friends/request/' + email, {}, options)
            .map((response: Response) => response.status)
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        let msg = `Status code ${error.status} on url ${error.url}`;
        console.log(msg);
        return Observable.throw(msg);
    }
}