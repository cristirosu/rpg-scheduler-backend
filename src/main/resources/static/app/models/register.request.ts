export class RegisterRequest {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    passwords: {
        password: string,
        matchPassword: string
    }
}