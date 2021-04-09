import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  private url = '/api';

  getUsers(): Observable<any> {
    return this.http.get(this.url + '/users');
  }

  addUser(user: any) {
	  return this.http.post(this.url + '/user', user);
  }

  parseLisp(lisp: any): Observable<any> {
    return this.http.post(this.url + '/lisp', lisp);
  }

  parseCsv(formData: FormData): Observable<any> {
    return this.http.post(this.url + '/csv/upload', formData);
  }

  getCsv(company: String): Observable<any> {
    return this.http.get(this.url + '/csv/download/' + company, {responseType: 'blob'});
  }

}
