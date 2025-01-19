export type UserType = {
    id?: number;
    username: string;
    iconUrl: string;
    friendsList?: UserType[];
    // direct and group channels 
}