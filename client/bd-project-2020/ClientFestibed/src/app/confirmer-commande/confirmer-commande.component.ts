import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { User } from '../tmdb-data/user';
import {AuthService} from '../service/auth.service';
import {AngularFireAuth} from '@angular/fire/auth';
import {StorageService} from '../service/storage.service';
import {CommandeService} from '../service/commande.service';
import {MessageService} from 'primeng/api';
import {Router} from '@angular/router';
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
import { EventEmitterService } from '../service/event-emitter.service';



@Component({
    selector: 'app-confirmer-commande',
    templateUrl: './confirmer-commande.component.html',
    styleUrls: ['./confirmer-commande.component.scss']
})
export class ConfirmerCommandeComponent implements OnInit {
    secondFormGroup: FormGroup;

    isAuth: boolean; // boolean indiquant s'il est connecté
    user: User; // l'utilisateur courant
    totalMenu: any;
    totalMovie: any;
    total: number = 0;
    nombreFilms:number;

    factureObjet:any;
  
    constructor(private eventEmitterService: EventEmitterService, private _formBuilder: FormBuilder, private authService: AuthService,
        private afAuth: AngularFireAuth, private storageService: StorageService,private commandeService: CommandeService
        ,private route: Router, private message : MessageService) {}
  
    ngOnInit() {

      this.secondFormGroup = this._formBuilder.group({
        secondCtrl: ['', Validators.required]
      });
        this.user={};
        this.afAuth.auth.onAuthStateChanged(
            (user) => {
                if (user) {
                    this.authService.getUser(user.uid).then(res => {
                        this.user = res;
                        setTimeout(() => {
                            $('#nomInput').focus();
                        }, 100);
                        
                    }, r => {
                        console.log('errr' + r);
                    });
                    this.isAuth = true;
                } else {
                    this.isAuth = false;
                }
            }
        );
        let movie = this.storageService.getMieuNote();
        if(movie){
            this.nombreFilms=movie.length;
        }else{
            this.nombreFilms=0;
        }
        this.totalPanier();
    }


    totalPanier() {
        this.totalMovie = this.nombreFilms*3.79;
        this.totalMovie = Math.round(this.totalMovie*100)/100;
        this.totalMenu = 0;
        let i: number;
        let tabPrixMenu = JSON.parse(localStorage.getItem('totalMenu'));
        if(tabPrixMenu){
            for (i = 0; i < tabPrixMenu.length; i++) {
                this.totalMenu += +tabPrixMenu[i];
            }
        }else{
            this.totalMenu=0;
        }
        this.total = this.totalMenu+this.totalMovie;
        this.total = Math.round(this.total*100)/100;
    }

    async imprimerFacture() {
        pdfMake.vfs = pdfFonts.pdfMake.vfs;
        pdfMake.createPdf(this.factureObjet).download('Ma facture.pdf');
    }

    async resetPanier(){
        let idPlat = localStorage.getItem('platId');
        let idFilm = localStorage.getItem('movieId');
        let adresse = localStorage.getItem('adresse');
        await this.afAuth.user.subscribe(u => {
            if (this.isAuth) {
                this.commandeService.sendCommande({
                    idClient: u.uid,
                    idPlats: idPlat,
                    idFilms: idFilm,
                    adresseLivraison: adresse,
                }).then(data => {
                    // this.route.navigate(['user/commande']);
                    localStorage.removeItem('plat');
                    localStorage.removeItem('filmNote');
                    localStorage.removeItem('totalMovie');
                    localStorage.removeItem('totalMenu');
                    localStorage.removeItem('movieId');
                    localStorage.removeItem('platId');
                    this.message.add({
                        severity: 'success',
                        summary: `Commande Confirmer avec succes `,
                        detail: 'Merci d\'avoir commandé sur MenuCinema à bientot'
                    });
                });
            } else {
                // this.route.navigate(['/authentification/signin']);
            }

        });
    }

    createFacture(){
        let nomPrenom = this.user.prenom + ' ' + this.user.nom
        let ladate=new Date()
        let dateJour = ladate.getDate()+"/"+(ladate.getMonth()+1)+"/"+ladate.getFullYear()

        let movies = this.storageService.getMieuNote();
        let prixTotalFilms=0
        let filmsTable =  []
        filmsTable.push(['Num', 'Nom du film', 'Prix'])
        if(movies){
            for (let index = 0; index < movies.length; index++) {
                prixTotalFilms+=3.79
                let filmTmp =
                [{text: index+1, italics: true, color: 'gray'}, 
                {text: movies[index].title, bold:true}, 
                {text: '3.75€', italics: true, color: 'gray'}]
                filmsTable.push(filmTmp)
            }
        }
        let filmTmp =
        [{text: '', italics: true, color: 'gray'}, 
        {text: 'TOTAL', bold:true}, 
        {text: prixTotalFilms+'€', italics: true, color: 'gray'}]
        filmsTable.push(filmTmp)

        let panierPlats = this.storageService.getMenu();
        let platsTable =  []
        platsTable.push(['Num', 'Nom Du plat', 'Prix'])
        if(panierPlats){
            for (let y = 0; y < panierPlats.length; y++) {
                let platTmp =
                [{text: y+1, italics: true, color: 'gray'}, 
                {text: panierPlats[y].id, bold:true}, 
                {text: panierPlats[y].prix, italics: true, color: 'gray'}]
                platsTable.push(platTmp)
            }
        }
  
        this.factureObjet = {
            content: [
                {text: 'AppCinema', style: 'header', fontSize: 15, color:'blue', margin: [0, 0, 0, 0]},
          
          
                {
                    table: {
                        widths: [150, 120],
                        body: [
                            [
                                {text: nomPrenom, border: [true, true, true, false]}
                            ],    
                            [
                                {text: this.user.adresse, border: [true, false, true , true]}
                            ]
                        ]
                    }, margin: [330, 20, 0, 0] 
                },


                
              // if you set the value of text to an array instead of a string, you'll be able
              // to style any part individually
              {
                text: [
                  'Facture du: ',
                  { text: dateJour, fontSize: 12, color:'grey',  italics: true }, 
                ], margin: [0, 20, 0, 0] 
              },     // basic usage
              
              {
                text: [
                  'Achats films',
                ], margin: [200, 20, 0, 0], fontSize: 17
              },  

              {
                style: 'tableExample',
                table: {
                    widths: [40, 200, 40],
                    body: filmsTable
                }, margin: [110, 8, 0, 0] 
            },

            {
                text: [
                  'Achats plats',
                ], margin: [200, 20, 0, 0], fontSize: 17
              },  

              {
                style: 'tableExample',
                table: {
                    widths: [40, 200, 40],
                    body: platsTable
                }, margin: [110, 8, 0, 0] 
            },
            {
                text: [
                  'Total Facture : 400',
                ], margin: [350, 20, 0, 0], fontSize: 17
              },  
            ]
        };
        this.resetPanier()
        setTimeout(() => {
            this.eventEmitterService.refreshPanier();
        }, 1000);
         
    }
}
