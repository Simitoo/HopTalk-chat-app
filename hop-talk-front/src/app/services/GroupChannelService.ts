import { Injectable, inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';
import { environment } from "../environments/environment";
import { ApiResponse, GroupChannel } from "../models/group-channel.model";

@Injectable({
    providedIn: "root"
})
export class GroupChannelService {

    private httpClient = inject(HttpClient);
    private baseUrl = environment.baseUrl;

    constructor(private http: HttpClient) {}

    getUserGroupChannels(userId: number): Observable<GroupChannel[]> {
        return this.httpClient.get<ApiResponse>(`${this.baseUrl}/groups/${userId}`)
            .pipe(
                map((response: ApiResponse) => response.data)  // Extract the array from "data"
            );
    }
}