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


export class hebergementService {
    serverUrl = 'http://localhost:8090/api/hebergements';

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }


    constructor(private http: HttpClient) {

    }

    async getHebergement(params:  {[key: string]: string}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/hebergements";
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation hebergements')
        return res.body;
    }

    async getchambres(params:  {[key: string]: any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/logements";
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation chambres')
        return res.body;
    }

    async getListeChambre(params:  {[key: string]: any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/logements";
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation Liste Chambre')
        return res.body;
    }

   async getDisponibilite(params:  {[key: string]: any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/logements";
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation Liste disponibilité')
        return res.body;
    }

    async addReservationLogement(params:  {[key: string]: any}) {
        this.serverUrl= "http://localhost:8090/api/reservationLogement";
        const P = new HttpParams( {fromObject: params} );
        return this.http.post( `${this.serverUrl}`, P, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
    
        }).toPromise();
    }

    async tabDispo(params:  {numLogement : any}) : Promise<any[]>  {
        this.serverUrl= "http://localhost:8090/api/disponibilitesLogement";
        const url = this.serverUrl;
        const res = await this.get<any[]>(url, params);
        console.log('recuperation places heberg-dispo.service')
        return res.body;
    }


}
