import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { map, Observable } from "rxjs";
import { Message, MessageResponse } from "../models/message.model";
import { MessageType } from "../models/message-type.enum";

@Injectable({
    providedIn: "root"
})
export class MessageService {

    private httpClient = inject(HttpClient);
    private baseUrl = environment.baseUrl;

    constructor(private http: HttpClient) {}

    createMessage(receiverId: number, isGroup: boolean, messageContent: string, messageType: MessageType): Observable<Message[]> {
        const messagePayload = {
            receiverId: receiverId,
            receiverType: isGroup ? 'CHANNEL' : 'USER',
            sender: { id: JSON.parse(localStorage.getItem('currentUser') || '{}').id },
            message: messageContent,
            messageType: messageType,
        };
    
    
        return this.httpClient.post<Message[]>(`${this.baseUrl}/messages`, messagePayload);
    }

    getMessagesDirectUser(userId: number): Observable<Message[]> {
        return this.http.get<MessageResponse>(`${this.baseUrl}/messages/direct/${userId}`).pipe(
            map(response => response.data)
        );
    }

    getMessagesGroupChannel(channelId: number): Observable<Message[]> {
        return this.http.get<MessageResponse>(`${this.baseUrl}/messages/channel/${channelId}`).pipe(
            map(response => response.data) 
        );
    }
}