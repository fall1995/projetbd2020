import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Plats} from '../menu-commade-data/Menu';
import {MovieQuery, MovieResponse} from '../tmdb-data/Movie';


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
export class MenuService {
    serverUrl='http://172.20.0.1:8090';  /*monPC/*http://172.20.0.1:8090*/   /*server 'http://51.178.28.170:8090'  */

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data)}
        }).toPromise();
    }

    constructor(private http: HttpClient) {
    }

    async getAllMenu( options?: MovieQuery): Promise<Plats[]> {
        const url = `${this.serverUrl}/api/plats`;
        const res = await this.get<Plats[]>(url, options);
        return res.body;
    }

    async getMenuParTypes( type?: MovieQuery): Promise<Plats[]> {
        const url = `${this.serverUrl}/api/PlatsType`;
        const res = await this.get<Plats[]>(url, {'type':type});
        return res.body;
    }

    async getPlatParPrix( params: {[key: string]: number}): Promise<Plats[]> {
        const url = `${this.serverUrl}/api/platsPrix`;
        const res = await this.get<Plats[]>(url, params);
        return res.body;
    }

    async getPlatParPrixEtType( params: {[key: string]: number}): Promise<Plats[]> {
        const url = `${this.serverUrl}/api/platsPrixType`;
        const res = await this.get<Plats[]>(url, params);
        console.log('heyyyyyy')
        return res.body;
    }





}
