import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Festival} from '../Festival-DATA/Festival';


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

    async getFestivals(params: { [key: string]: string }): Promise<Festival[]> {
        const url = `${this.serverUrl}/api/platsPrixType`;
        const res = await this.get<Festival[]>(url, params);
        console.log('heyyyyyy')
        return res.body;
    }

}
