import { Injectable } from '@angular/core';
import {AppSettings} from "./app.settings";

declare let SockJS : any;
declare let Stomp : any;

@Injectable()
export class WebSocketService {

    stompClient : any;

    subscribe(topic: any, subscribeFn: any){
        var socket = new SockJS(AppSettings.SOCKETG_URL);
        this.stompClient = Stomp.over(socket);
        var self = this;
        this.stompClient.connect({}, function (frame : any) {
            console.log("subscribing to " + topic)
            self.stompClient.subscribe('/topic/' + topic, subscribeFn);
            console.log("subscribed to " + topic);
        });
    }

    sendName(topic: string) {
        this.stompClient.send("/app/hello/" + topic, {}, JSON.stringify({'name': 'MyUsername'}));
    }

    disconnect(){
        if(this.stompClient !== undefined){
            this.stompClient.disconnect();
        }
    }

}