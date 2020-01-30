import {Component, OnInit, HostListener} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {AuthService} from '../service/auth.service';
import * as firebase from 'firebase';
import {MessageService} from 'primeng/api';
import {Router} from '@angular/router';
import {StorageService} from '../service/storage.service';
import { EventEmitterService } from '../service/event-emitter.service';    



@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    nbArticlesPanier:number;
    user: any;
    prevScrollpos:any;
    isAuth: boolean;



    constructor(private eventEmitterService: EventEmitterService, private storageService: StorageService, private afAuth: AngularFireAuth, private authService: AuthService,
    private message: MessageService, private route: Router) {
    }


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
        this.init()

        this.afAuth.auth.onAuthStateChanged(
            (user) => {
                if(user)
                    this.user=user.displayName
            }
        );
        this.prevScrollpos = window.pageYOffset;
    }

    init() {
        this.afAuth.user.subscribe(u => {
            if (u) {
                this.user = u.displayName;
            }
        });
    }

    async initnbArticles(){
        let nbArticlesFilms=0
        let nbArticlesRepas=0
        let ArticlesRepas = await this.storageService.getMenu();
        if(ArticlesRepas){
            nbArticlesRepas=ArticlesRepas.length
        }
        let ArticlesFilms = this.storageService.getMieuNote();
        if(ArticlesFilms){
            nbArticlesFilms=ArticlesFilms.length
        }

        this.nbArticlesPanier = nbArticlesFilms+nbArticlesRepas;
    }



    onSignOut() {
        this.afAuth.auth.signOut();
        this.message.add({severity:'info',
            summary:'A très bientot',
            detail:'Vous êtes deconneté !'});
        this.route.navigate(['/']);
        this.init();
        this.user=null;

    }

    @HostListener('window:scroll', ['$event']) 
    scrollHandler(event) {
        let currentScrollPos = window.pageYOffset;
        if (this.prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
            $('#applicationLogo').css("padding-top","1em")
        } else{
                if( this.prevScrollpos >100){
                    document.getElementById("navbar").style.top = "-2.8em";
                    $('#applicationLogo').css("padding-top","2em")
                }
            }
        this.prevScrollpos = currentScrollPos;
    }

}
