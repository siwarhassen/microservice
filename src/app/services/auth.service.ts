import { Injectable } from '@angular/core';
import {Http, Headers, Response, URLSearchParams} from '@angular/http';
import { map, tap, switchMap } from "rxjs/operators";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { Observable, of } from 'rxjs';
//import { JwtHelperService } from "@auth0/angular-jwt";
import 'rxjs/Rx';
import { isNull } from '@angular/compiler/src/output/output_ast';
import { User } from '../models/User';
export const JWT_NAME = 'blog-token';

@Injectable()
export class AuthService {

  public token: any ;

  constructor(private http: Http, ) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser')|| '{}');
    this.token = currentUser && currentUser.token;
  }



  login(username: string, password: string): Observable<boolean> {
    let headers = new Headers();
    headers.append('content-type', 'application/x-www-form-urlencoded');
    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);


    return this.http.post('http://127.0.0.1:8000/api/login_check', body ,{headers : headers}).pipe(
       map((response: Response) => {
        // login successful if there's a jwt token in the response
        const token = response.json() && response.json().token;
        if (token) {
          // set token property
          this.token = token;

          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      }));
  }




  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  private handelError(error: Response) {

    return Observable.throw(error.json() || 'server error');

  }


/*
  isAuthenticated(): boolean {
    const token = localStorage.getItem(JWT_NAME);
    return !this.jwtHelper.isTokenExpired(token?.toString());
  }

  getUserIdConnected(): Observable<any>{
    return of(localStorage.getItem(JWT_NAME)).pipe(
      switchMap((jwt: any ) => of(this.jwtHelper.decodeToken(jwt)).pipe(
        map((jwt:any) =>jwt.doctor.id))
      )
    )
  }
  getUserIdConnectedEmploye(): Observable<any>{
    return of(localStorage.getItem(JWT_NAME)).pipe(
      switchMap((jwt: any ) => of(this.jwtHelper.decodeToken(jwt)).pipe(
        map((jwt:any) =>jwt.employee.id))
      )
    )
  }
*/

  register(user: any) {
   /* return this.http.post<any>("localhost", user).pipe(
      tap(doc => console.log(doc)),
      map(doc => doc)
    )*/
  }


}