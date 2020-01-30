import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Hotel } from '../Hotel/Hotel';




function AlxToObjectString(data?: object): { [key: string]: string } {
    const res = {};

    for (const k of Object.keys(data || {})) {
        const v = data[k];
        res[k] = typeof v === 'string' ? v : JSON.stringify(v);
    }
    return res;
}

@Injectable({
    providedIn: 'root'
})


export class panierService {
    serverUrl = 'http://localhost:8090/api/hebergements';

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }


    constructor(private http: HttpClient) {

    }

    async getResFestivales(params:  {[idUtilisateur: string]: any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/panierP";
        console.log('recuperation reservations en cours');
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation reservations en cours')
        return res.body;
    }

    async validerPanier(params:  {[key: string]: any}) {
        this.serverUrl= "http://localhost:8090/api/";
        const P = new HttpParams( {fromObject: params} );
        return this.http.post( `${this.serverUrl}`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
    
        }).toPromise();
    }

    async getResLogement(params:  {[idUtilisateur: string]: any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/panierLog";
        console.log('recuperation reservations en cours');
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation reservations en cours')
        return res.body;
    }



}