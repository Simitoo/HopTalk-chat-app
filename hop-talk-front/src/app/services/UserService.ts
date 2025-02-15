import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { Observable } from "rxjs";
import { UserType } from "../models/user.model";
import { UserResponse } from "../models/user-response";

@Injectable({
    providedIn: "root"
})
export class UserService{

    private httpClient = inject(HttpClient);
    private baseUrl = environment.baseUrl;

    constructor(private http: HttpClient){}

    login(username: string, password: string) {
        const credentials = {username, password};
        return this.http.post<UserResponse >(`${this.baseUrl}/users/login`, credentials);
    }

    register(user: UserType): Observable<any>{
        return this.http.post<any>(`${this.baseUrl}/users/register`, user);
    }

    searchUsers(username: string): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}/users/search?username=${username}`);
    }
}