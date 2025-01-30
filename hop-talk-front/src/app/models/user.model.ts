export type UserType = {
    id?: number;
    username: string;
    password?: string;
    iconUrl: string;
    friendsList?: UserType[];
}