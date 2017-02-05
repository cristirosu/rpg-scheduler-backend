import {Headers, RequestOptions} from "@angular/http";

export class AppSettings {
 
 public static get API_OPTIONS(): RequestOptions {
      let headers = new Headers({ 'Content-Type': 'application/json' }),
          options = new RequestOptions({ headers: headers});
       return options;
  }

  public static get API_URL(): string {
     let devMode = true, prodPath = "http://cristi.red:8080/api", apiSecured = false, apiHost = "localhost", apiPort = "8080/api";
     return  (devMode) ? ( "http" +((apiSecured) ? "s" : "") + "://" + apiHost + ":" + apiPort ) : prodPath;
  }

    public static get SOCKETG_URL(): string {
        let devMode = true, prodPath = "http://cristi.red:8080/gs-guide-websocket", local = "http://localhost:8080/gs-guide-websocket";
        return  (devMode) ? local : prodPath;
    }

}
