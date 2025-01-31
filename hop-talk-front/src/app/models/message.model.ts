import { MessageType } from "./message-type.enum";

export type Message = {
    id: number;
    senderUsername: string;
    messageContent: string;
    messageType: MessageType;
    attachmentUrl?: string;
    createdAt: Date;
}

export interface MessageResponse {
    data: Message[];
    code: number;
    message: string;
}