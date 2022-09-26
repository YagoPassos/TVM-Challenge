import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "../login.service";

@Injectable()

export class AuthInterceptor implements HttpInterceptor {

    constructor(private loginService: LoginService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = this.loginService.getToken()

        console.log(this.loginService.isLoggedIn)
        
        if (token) {

            const authRequest = req.clone({ setHeaders: { 'Authorization': 'Bearer ' + token } });

            return next.handle(authRequest)
        }
        return next.handle(req)
    }

}