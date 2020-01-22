import {Component, OnInit} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {User} from '../tmdb-data/user';
import {AuthService} from '../service/auth.service';
import {StorageService} from '../service/storage.service';
import {CommandeService} from '../service/commande.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
    selector: 'app-user-profil',
    templateUrl: './user-profil.component.html',
    styleUrls: ['./user-profil.component.scss']
})
export class UserProfilComponent implements OnInit {

    constructor(private afAuth: AngularFireAuth, private authService: AuthService, private storageService: StorageService,
             private _snackBar: MatSnackBar, private commandeService: CommandeService) {
    }

    commade: any; // variable qui stock la dernière commande
    panier: any[]; // variable qui stocke le tableaux de plat
    movie: any[]; // variable qui stock les films selectionnées
    afficherDialog = false; // boolean pour ouvrir et fermer le dialogue pop up
    user: User; // l'utilisateur courant
    userPhoto: User = { // je stock la photo de profil recuperer dans firebase
        photo: '',
    };
    modif:any;


    ngOnInit() {
        this.modif=false
        this.init();
    }

    /**
     * recuperation de la dernière commande
     */

    /**
     * recuperation des informations de l'utilisateur
     */
    async init() {
        await this.afAuth.user.subscribe(u => {
            if (u) {
                this.authService.getUser(u.uid).then(res => {
                    this.user = res;
                    this.userPhoto.photo = u.photoURL;
                }, r => {
                    console.log('errr' + r);
                });
            }
        });
    }



    /**
     * bouton qui affiche le dialog pop up
     */
    afficherDialogProfil(): void {
        this.afficherDialog = true;
    }

    /**
     * fermeture du dialog pop up
     */
    onHideProfilDialog(): void {
        this.afficherDialog = false;
    }

    /**
     * effacer l'élément sélectionné(plat)
     * @param index
     */
    deleteMenusSelected(index: number) {
        this.storageService.delete(index);
    }

    /**
     * effacer l'élément selectionné(movie)
     * @param index
     */
    deleteMovieSelected(index: number) {
        this.storageService.deleteMovie(index);
    }
    updatePhoto(){
        
    }
    modifierInfos(){
        this.modif=true;
    }
    confirmerModif(){
        this.modif=false;
        setTimeout(() => {
            this._snackBar.open('Vos coordonnées sont à jour.', 'OK', {
                duration: 5000,
            });
        }, 500);
    }

}
