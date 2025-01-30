import { ChannelParticipantRole } from "./channel-participant-role.enum";

export type GroupParticipant = {
    id: number;
    username: string;
    iconUrl: string;
    role: ChannelParticipantRole;
}

export type GroupChannel = {
    id?: number;
    title: string;
    iconUrl: string;
    participantsList?: GroupParticipant[];
};

export interface ApiResponse {
    data: GroupChannel[];
    code: number;
    message: string;
}