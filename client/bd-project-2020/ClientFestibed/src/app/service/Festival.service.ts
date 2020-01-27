import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Festival} from '../Festival-DATA/Festival';
import {Place} from "../Place-data/Place";


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

export class FestivalService {
    serverUrl = 'http://localhost:8090/api/festivals';

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }


    constructor(private http: HttpClient) {
    }

    async getFestivals(params: { dateDebut: string; domaine: string; dateFin: string; Ville: string }): Promise<Festival[]> {
        const url = `${this.serverUrl}`;
        const res = await this.get<Festival[]>(url, params);
        console.log('heyyyyyy')
        return res.body;
    }

    async addFestival(params:  {[key: string]: number}) {
        this.serverUrl= "http://localhost:8090/api/addFestivals";
        const url = `${this.serverUrl}`;
        return this.http.post( `${this.serverUrl}`, params, {
            observe: 'response',
            responseType: 'text',
            headers: {'content-type': 'application/x-www-form-urlencoded'}
        }).toPromise();
    }

    async getPlaces(params:  {[key: string]: number}) : Promise<Place[]>  {
        this.serverUrl= "http://localhost:8090/api/addPlace";
        const url = this.serverUrl;
        const res = await this.get<Place[]>(url, params);
        console.log('recuperation places festivals.service')
        return res.body;
    }




}
