export class LoginResponse {
    constructor(
        public email: any,
        public password: any,
        public role: any,
        public token: any,
        public message: any
    ) { }
}