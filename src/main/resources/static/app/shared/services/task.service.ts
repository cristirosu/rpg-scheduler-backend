import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';

import { Task } from '../../models/task.model';
import { AuthenticationService } from './authentication.service';
import { AppSettings } from '../services/app.settings';

@Injectable()
export class TaskService {
    constructor(private _http: Http, private authService: AuthenticationService) { }

    list(filters: Object) {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });
        return this._http.get(AppSettings.API_URL + '/tasks', options)
            .map((response: Response) => <Task[]>response.json())
            .catch(this.handleError);
    }

    save(task: Task) {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });
        let data = JSON.stringify(task);
        return this._http.put(AppSettings.API_URL + '/tasks', data, options)
            .map((response: Response) => <Task[]>response.json())
            .catch(this.handleError);
    }

    get(task: Task) {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });
        return this._http.get(AppSettings.API_URL + '/tasks/' + task.id, options)
            .map((response: Response) => <Task[]>response.json())
            .catch(this.handleError);
    }

    delete(task: Task) {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });
        return this._http.delete(AppSettings.API_URL + '/tasks/' + task.id, options)
            .map((response: Response) => <Task[]>response.json())
            .catch(this.handleError);
    }

    updateStatus(task: Task) {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.token });
        let options = new RequestOptions({ headers: headers });

        return this._http.put(AppSettings.API_URL + '/tasks/' + task.id + '/status', {}, options)
            .map((response: Response) => <Task[]>response.json())
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        let msg = `Status code ${error.status} on url ${error.url}`;
        console.log(msg);
        return Observable.throw(msg);
    }
}