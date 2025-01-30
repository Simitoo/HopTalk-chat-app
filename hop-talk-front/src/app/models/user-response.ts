import { UserType } from "./user.model";

export type UserResponse = {
    status: string;
    code: number;
    message: string;
    data: UserType;
  };