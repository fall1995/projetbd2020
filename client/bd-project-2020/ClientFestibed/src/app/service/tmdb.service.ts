import {Injectable} from '@angular/core';
import {MovieQuery, MovieResponse} from '../tmdb-data/Movie';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {PersonQuery, PersonResponse} from '../tmdb-data/Person';
import {SearchMovieQuery, SearchMovieResponse} from '../tmdb-data/searchMovie';
import {SearchPeopleQuery, SearchPeopleResponse} from '../tmdb-data/SearchPeople';
import {TVQuery, TVResponse} from '../tmdb-data/TV';
import {SearchTVQuery, SearchTVResponse} from '../tmdb-data/SearchTV';
import {Observable} from 'rxjs';
import { stringify } from '@angular/compiler/src/util';

const tmdbApi = 'https://api.themoviedb.org/3';
type HTTP_METHOD = 'GET' | 'POST' | 'DELETE' | 'PUT';

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
export class TmdbService {
    // image url
    public host = 'https://image.tmdb.org/t/p/original/';
    private apiKey: string;
    private API_KEY = 'da83143edd5234541968f1568c9bcc11';

    private async get<T>(url: string, data: object): Promise<HttpResponse<T>> {
        return this.http.get<T>(url, {
            observe: 'response',
            params: {...AlxToObjectString(data), api_key: this.apiKey}
        }).toPromise();
    }

    constructor(private http: HttpClient) {
    }

    init(key: string): this {
        this.apiKey = key;
        return this;
    }

    // _______________________________________________________________________________________________________________________________________
    // Movies ________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    /**
     * recuperation de film ar id
     * @param id
     * @param options
     */
    async getMovie(id: number, options?: MovieQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/movie/${id}`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    /**
     * Ici on recupère tous les meilleurs films de 2010
     * @param options
     */
    async getAllMovie(noPage:any, anneeMin: number, anneeMax: number, options?: MovieQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/discover/movie?sort_by=popularity.desc&language=fr-FR&primary_release_date.gte=${anneeMin}-01-01&primary_release_date.lte=${anneeMax}-12-30&page=${noPage}`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }



    async searchMovie(noPage:any, query: String): Promise<MovieResponse> {
        const url = `${tmdbApi}/search/movie?api_key=${this.API_KEY}&language=fr-FR&query=${query}&page=${noPage}`;
        const res = await this.get<MovieResponse>(url, query);
        return res.body;
    }

    async getSimilarMovies(movieID: number, options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/movie/${movieID}/similar?api_key=${this.API_KEY}&language=fr-FR`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    async getMovieVideo(movieID: number, options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/movie/${movieID}/videos?api_key=${this.API_KEY}&language=fr-FR`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    async getCastMovie(movieID: number, options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/movie/${movieID}/credits?api_key=${this.API_KEY}`;
        const res = await this.get<MovieResponse>(url, options);
        return res.body;
    }

    async moviesWithGenres(noPage: number, query: string[] ,anneeMin: number, anneeMax: number, noteMin:number): Promise<MovieResponse> {
        let query2='';
        if(query && query.length>0){
            query2+='&with_genres='
            query2+=query[0]
            for (let index = 1; index < query.length; index++) {
                query2 += '|' + query[index];
            }
        } 
        let noteFilm=''
        if(noteMin){
            noteFilm='&vote_average.lte='+noteMin
        } 
        let anneeQuery='';
        if(anneeMin>1900||anneeMax<2019){
            anneeQuery='&primary_release_date.gte='+anneeMin+'-01-01&primary_release_date.lte='+anneeMax+'-12-30'
        } 
        const url = `${tmdbApi}/discover/movie?page=${noPage}${anneeQuery}&language=fr-FR${query2}${noteFilm}`;
        const res = await this.get<MovieResponse>(url, query);
        return res.body;
    }
    


    async getAllGenres(options?: TVQuery): Promise<MovieResponse> {
        const url = `${tmdbApi}/genre/movie/list?api_key=da83143edd5234541968f1568c9bcc11&language=fr-FR`;
        const res = await this.get<MovieResponse>(url,options);
        return res.body;
    }

    // _______________________________________________________________________________________________________________________________________
    // Person / People _______________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    async getPerson(id: number, options?: PersonQuery): Promise<PersonResponse> {
        const url = `${tmdbApi}/person/${id}`;
        const res = await this.get<PersonResponse>(url, options);
        return res.body;
    }

    async searchPerson(query: SearchPeopleQuery): Promise<SearchPeopleResponse> {
        const url = `${tmdbApi}/search/person`;
        const res = await this.get<SearchPeopleResponse>(url, query);
        return res.body;
    }

    // _______________________________________________________________________________________________________________________________________
    // TV ____________________________________________________________________________________________________________________________________
    // _______________________________________________________________________________________________________________________________________
    async getTV(id: number, options?: TVQuery): Promise<TVResponse> {
        const url = `${tmdbApi}/tv/${id}`;
        const res = await this.get<TVResponse>(url, options);
        return res.body;
    }



    async searchTV(query: SearchTVQuery): Promise<SearchTVResponse> {
        const url = `${tmdbApi}/search/tv`;
        const res = await this.get<SearchTVResponse>(url, query);
        return res.body;
    }

}
