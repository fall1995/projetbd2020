import {Component, OnInit} from '@angular/core';
import {CommandeService} from '../service/commande.service';
import {CommandeData} from '../menu-commade-data/commande';
import {AngularFireAuth} from '@angular/fire/auth';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
    selector: 'app-commande',
    templateUrl: './commande.component.html',
    styleUrls: ['./commande.component.scss']
})
export class CommandeComponent implements OnInit {

    commande:any; //CommandeData; // variable qui va stocker les commande

    toutesCommandes:any[];
    displayedColumns: string[] = ['titre', 'prix'];
    displayedColumns2: string[] = ['titre', 'type', 'prix'];

    constructor(private _snackBar: MatSnackBar, private commandeService: CommandeService, private afAuth: AngularFireAuth) {
    }

    ngOnInit() {
        //this.init();
        this.initialiserToutesCommandes();
        this.commande=this.toutesCommandes[0]
        setTimeout(() => {
            this._snackBar.open('Voici votre dernière facture', 'OK', {
                duration: 5000,
            });
        }, 1000);
    }

    /**
     * je recupere ici les données de la commande
     */
    async init() {
        await this.afAuth.user.subscribe(u => {
            this.commandeService.getCommande(u.uid).then(res => {
                this.commande = res;
                console.log(res);
            }, r => {
                console.log('errr' + r);
            });

        });

    }

    getTotalCostFilms() {
        let tot =  this.commande.films.map(t => parseFloat(t.prix)).reduce((acc, value) => acc + value, 0);
        tot = Math.round(tot*100)/100;
        return tot;
    }
    getTotalCostPlats() {
        let tot =  this.commande.plats.map(t => parseFloat(t.prix)).reduce((acc, value) => acc + value, 0);
        tot = Math.round(tot*100)/100;
        return tot;
    }
    getTotal(){
        let tot= (this.commande.films.map(t => parseFloat(t.prix)).reduce((acc, value) => acc + value, 0))+(this.commande.plats.map(t => parseFloat(t.prix)).reduce((acc, value) => acc + value, 0));
        tot = Math.round(tot*100)/100;
        return tot;
    }

    initialiserToutesCommandes() {
        this.toutesCommandes=[
            {
                date:'25/03/2019',
                films:[
                    {titre: 'Usual suspects', prix: 3.79},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'20:47'
            },
            {
                'date':'15/06/2019',
                films:[
                    {titre: 'The Irishman', prix: 3.79},
                    {titre: 'The Town', prix: 3.79},
                ],
                plats:[
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Thé noir', prix: 1, type:'Boisson'},
                    {titre: 'Thé noir', prix: 1, type:'Boisson'},
                ],
                heure:'20:47'
            },
            {
                'date':'20/06/2019',
                films:[
                ],
                plats:[
                    {titre: 'Pâtes au persto', prix: 8, type:'Plat'},
                    {titre: 'Pizza au pepperoni', prix: '10.50', type:'Plat'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'20:40'
            },
            {
                'date':'07/10/2019',
                films:[
                    {titre: 'American gangster', prix: 3.79},
                    {titre: 'Il était une fois dans l\'oued', prix: 3.79},
                ],
                plats:[
                ],
                heure:'20:02'
            },
            {
                'date':'18/10/2019',
                films:[
                ],
                plats:[
                    {titre: 'Burger', prix: 6, type:'Plat'},
                    {titre: 'Burger', prix: 6, type:'Plat'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Sandwich Bio', prix: 15, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'21:02'
            },
            {
                'date':'28/01/2018',
                films:[
                    {titre: 'Le seigneur des Anneaux I', prix: 3.79},
                    {titre: 'Psychose', prix: 3.79},
                ],
                plats:[
                ],
                heure:'01:02'
            },
            {
                'date':'15/03/2018',
                films:[
                    {titre: 'Taxi driver', prix: 3.79},
                    {titre: 'Heat', prix: 3.79},
                ],
                plats:[
                    {titre: 'Frites', prix: 7, type:'Hors d\'oeuvre'},
                    {titre: 'Jus d\'orange', prix: 3, type:'Boisson'},
                ],
                heure:'22:42'
            },
            {
                'date':'20/03/2018',
                films:[
                    {titre: 'Le dîner de con', prix: 3.79},
                    {titre: 'La chèvre', prix: 3.79},
                    {titre: 'Hancock', prix: 3.79},
                ],
                plats:[
                    {titre: 'Frites', prix: 7, type:'Hors d\'oeuvre'},
                    {titre: 'Jus de Framboise', prix: 5, type:'Boisson'},
                ],
                heure:'14:08'
            },
            {
                'date':'21/03/2018',
                films:[
                    {titre: 'L\'effet Papillon', prix: 3.79},
                    {titre: 'La vague', prix: 3.79},
                ],
                plats:[
                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},
                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},                    {titre: 'Jus de fraise', prix: '5.50', type:'Boisson'},
                ],
                heure:'03:52'
            },
            {
                'date':'18/04/2018',
                films:[
                    {titre: 'Joker', prix: 3.79},
                    {titre: 'Go Fast', prix: 3.79},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'20:10'
            },
            {
                'date':'01/04/2016',
                films:[
                    {titre: 'Les princes de la ville', prix: 3.79},
                    {titre: 'Douze hommes en colère', prix: 3.79},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'21:18'
            },
            {
                'date':'13/01/2015',
                films:[
                    {titre: 'Into the wild', prix: 3.79},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'20:40'
            },
            {
                'date':'09/06/2015',
                films:[
                    {titre: 'Seven', prix: 3.79},
                    {titre: 'I am a legend', prix: 5},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'12:30'
            },
            {
                'date':'05/12/2015',
                films:[
                    {titre: '7 vies', prix: 3.79},
                ],
                plats:[
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Joie printanière', prix: '10.50', type:'Entrée'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                    {titre: 'Salade César', prix: 5, type:'Entrée'},
                    {titre: 'Jus de Mangue', prix: 5, type:'Boisson'},
                ],
                heure:'21:51'
            },
        ];
    }

    commandeChoisie(numCom){
        this.commande=this.toutesCommandes[numCom-1];
    }

    deselectCommande(){
        this.commande=null;
    }


}
