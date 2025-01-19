import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { enviroment } from "../environments/environment";

@Injectable({
    providedIn: "root"
})
export class UserService{

    private httpClient = inject(HttpClient);
    private baseUrl = `${enviroment.baseUrl}/users`;

    public getAllUsers(){
        return this.httpClient.get(this.baseUrl);
    }

    public getUserByUsername($username: string){
        
    }
}