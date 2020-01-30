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
import { Reservation } from '../Reservation-DATA/Reservation';
import { panierService } from '../service/panier.service';
import {IpServiceService} from "./../ip-service.service";



@Component({
    selector: 'app-panier',
    templateUrl: './panier.component.html',
    styleUrls: ['./panier.component.scss']
})
export class PanierComponent implements OnInit {


    isAuth: boolean; // boolean indiquant s'il est connecté
    
    afficherDialog = false;
    user: User; // l'utilisateur courant
    tabResFest : Reservation[];
    tabResLogement : any[];

    prevScrollpos:any;
    ipAddress: any;
    idUtilisateur: any;

    // tslint:disable-next-line:max-line-length
    constructor( private route: Router, private message: MessageService,
                private commandeService: CommandeService, private afAuth: AngularFireAuth,
                private authService: AuthService, private eventEmitterService: EventEmitterService, private panierServ : panierService,  private ip:IpServiceService) {

    }


    getIP() {
        this.ip.getIPAddress().subscribe((res: any) => {
            this.ipAddress = res.ip;
            this.idUtilisateur = this.ipAddress;
        });
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
       
    }

    init() {
      
    }

    async getReservation(){
        this.tabResFest = await this.panierServ.getResFestivales({
            idFestival : this.idUtilisateur
           
          })
    }

}

   
    


   

   


