import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Message } from "../../models/message.model";
import { CommonModule } from "@angular/common";
import { MessageService } from "../../services/MessageService";
import { MessageType } from "../../models/message-type.enum";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-message',
    standalone: true,
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.css'],
    imports: [CommonModule, FormsModule]
})
export class MessageComponent implements OnInit {
    receiverId!: number;
    chatType!: 'CHANNEL' | 'USER';
    chatTitle: string = 'chat';
    messages: Message[] = [];
    newMessage: string = '';
    currentUser: any;
    pollingInterval: any;

    constructor(private messageService: MessageService) {}

    ngOnInit(): void {
        this.loadSelectedChat();
        this.loadCurrentUser(); 

        if (this.receiverId) {
            this.startPollingMessages();
        }
    }

    @Input() channel: any;
    @Output() closeChat = new EventEmitter<void>();

    closeChatHandler() {
        this.closeChat.emit();
    }

    ngOnDestroy(): void {
        clearInterval(this.pollingInterval); 
    }

    loadSelectedChat(): void {
        const storedChat = localStorage.getItem('selectedChannel');
        if (storedChat) {
            const parsedChat = JSON.parse(storedChat);
            this.receiverId = parsedChat.id;
            this.chatType = parsedChat.type;
            this.chatTitle = parsedChat.title;
         
            console.log('Selected chat:', parsedChat);
        } else {
            console.warn('No selected chat.');
        }
    }

    loadCurrentUser(): void {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    }

    startPollingMessages(): void {
        clearInterval(this.pollingInterval);

        this.pollingInterval = setInterval(() => {
            this.loadMessages();
        }, 3000);
    }

    loadMessages(): void {
        if (!this.receiverId) return;

        if (this.chatType === 'CHANNEL') {
            this.messageService.getMessagesGroupChannel(this.receiverId).subscribe(response => {
                this.messages = Array.isArray(response) ? response : response;
                console.log('Messages for group channel:', response);
            });
        } else if (this.chatType === 'USER') {
            this.messageService.getMessagesDirectUser(this.receiverId).subscribe(response => {
                this.messages = Array.isArray(response) ? response : response;
                console.log('Messages for user:', response);
            });
        }
    }

    sendMessage(): void {
        if (!this.newMessage.trim()) return;

        const message: Message = {
            id: 0,
            senderUsername: this.currentUser.username,
            messageContent: this.newMessage,
            messageType: MessageType.TEXT,
            attachmentUrl: undefined,
            createdAt: new Date(),
        };

        const messagePayload = {
            receiverId: this.receiverId,
            receiverType: this.chatType === 'CHANNEL' ? 'CHANNEL' : 'USER',
            sender: { id: this.currentUser.id },
            message: this.newMessage,
            messageType: MessageType.TEXT,
        };

        console.log("Sending message:", messagePayload);

        if (this.chatType === 'CHANNEL') {
            this.messageService.createMessage(this.receiverId, true, message.messageContent, MessageType.TEXT).subscribe(() => {
                this.messages.push(message);
                this.newMessage = '';
            });
        } else if (this.chatType === 'USER') {
           
            this.messageService.createMessage(this.receiverId, false, message.messageContent, MessageType.TEXT).subscribe(() => {
                this.messages.push(message); 
                this.newMessage = ''; 
            });
        }
    }
    
}