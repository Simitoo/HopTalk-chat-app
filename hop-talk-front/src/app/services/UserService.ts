import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { enviroment } from "../environments/environment";
import { Observable } from "rxjs";
import { UserType } from "../models/user.model";

@Injectable({
    providedIn: "root"
})
export class UserService{

    private httpClient = inject(HttpClient);
    private baseUrl = enviroment.baseUrl;

    constructor(private http: HttpClient){}

    login(username: string, password: string) {
        const credentials = {username, password};
        return this.http.post<UserType>(`${this.baseUrl}/users/login`, credentials);
    }

    register(user: UserType): Observable<any>{
        return this.http.post<any>(`${this.baseUrl}/users/register`, user);
    }
}