import {Component, OnInit, HostListener} from '@angular/core';
import {StorageService} from '../service/storage.service';
import {Router} from '@angular/router';
import {CommandeService} from '../service/commande.service';
import {AngularFireAuth} from '@angular/fire/auth';
import {MessageService} from 'primeng/api';
import {User} from '../tmdb-data/user';
import {AuthService} from '../service/auth.service';
import {TmdbService} from '../service/tmdb.service';
import { EventEmitterService } from '../service/event-emitter.service'; 




@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {

    constructor( private tmdbService: TmdbService, private storageService: StorageService, private route: Router, private message: MessageService,
<<<<<<< HEAD:client/bd-project-2020/ClientFestibed/src/app/panier/panier.component.ts
                private commandeService: CommandeService, private afAuth: AngularFireAuth,
                private authService: AuthService, private eventEmitterService: EventEmitterService) {
=======
                 private commandeService: CommandeService, private afAuth: AngularFireAuth,
                 private authService: AuthService, private eventEmitterService: EventEmitterService){
>>>>>>> aa0a0fca56b34451f34f2acc4957852436c2d5a2:client/bd-project-2020/clientAngular/src/app/panier/panier.component.ts

    }

    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    //commande: any;
    isAuth: boolean; // boolean indiquant s'il est connecté
    adresse: any;
    afficherDialog = false;
    user: User; // l'utilisateur courant
    totalMenu: any;
    totalMovie: any;
    total: number = 0;
    prevScrollpos:any;


    ngOnInit() {
        this.init();
        this.afAuth.auth.onAuthStateChanged(
            (user) => {
                if (user) {
                    this.isAuth = true;
                } else {
                    this.isAuth = false;
                }
            }
        );
        this.initDialog();
        this.totalPanier();
        this.prevScrollpos = window.pageYOffset;

    }

    init() {
        this.panier = this.storageService.getMenu();
        this.movie = this.storageService.getMieuNote();
        this.adresse = localStorage.getItem('adresse');
    }

    totalPanier() {
        if(this.movie){
            this.totalMovie = this.movie.length*3.79;
        }else{
            this.totalMovie=0;
        }
        this.totalMovie = Math.round(this.totalMovie*100)/100;
        this.totalMenu = 0;
        let i: number;
        let tabPrixMenu = JSON.parse(localStorage.getItem('totalMenu'));
        if(tabPrixMenu){
            for (i = 0; i < tabPrixMenu.length; i++) {
                this.totalMenu += +tabPrixMenu[i];
            }
        }
        this.total = this.totalMenu+this.totalMovie;
        this.total = Math.round(this.total*100)/100;
    }

    async initDialog() {
        await this.afAuth.user.subscribe(u => {
            if (u) {
                this.authService.getUser(u.uid).then(res => {
                    this.user = res;
                    console.log(this.user)
                }, r => {
                    console.log('errr' + r);
                });
            }
        });
    }


    /**
     * effacer l'élément sélectionné(plat)
     * @param index
     */
    deleteMenusSelected(index) {
        this.storageService.delete(index);
        this.init();
        this.totalPanier();
        this.eventEmitterService.refreshPanier();    
    }

    /**
     * effacer l'élément selectionné(movie)
     * @param index
     */
    deleteMovieSelected(index: number) {
        this.storageService.deleteMovie(index);
        this.init();
        this.totalPanier();
        this.eventEmitterService.refreshPanier();    
    }
    
    @HostListener('window:scroll', ['$event']) 
    scrollHandler(event) {
            let currentScrollPos = window.pageYOffset;
            if ($( window ).width() > 900) {
                if (this.prevScrollpos > currentScrollPos) {
                    if( currentScrollPos >34){
                        $("#infosClients").stop().animate({"marginTop": ($(window).scrollTop()-50) + "px"});
                    }
                } else{
                    if( this.prevScrollpos >80){
                        $("#infosClients").stop().animate({"marginTop": ($(window).scrollTop()-70) + "px"});
                    }
                }
            }
            this.prevScrollpos = currentScrollPos;
    }

}

