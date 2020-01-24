import { Component, OnInit } from '@angular/core';
import {TmdbService} from '../service/tmdb.service';
import {environment} from '../../environments/environment';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  domaine:any[];
  domaineSlectionne: any;
  VilleSelect:any;
  Datedebut : any;
  DateFin : any;

  constructor(private tmdbService: TmdbService) { }
  

  ngOnInit() {
    this.domaine = ['Aly', 'Demba', 'Houleye', 'Allasane', 'Demba'];
    this.init() 
  }
  // plats: Plats[];
  // responsiveOptions;
  // selectedPlat: Plats; // plat selectionné
  // displayDialog: boolean;

  // listeMovie: MovieResponse; // movie de 2019
  // listeMovieDram : MovieResponse; // movie les dramatique
  // listeMovieHightRatet : MovieResponse; // movie les mieux notés
  // selectedMovie: MovieResponse; // movie selectionner
  //displayDialog: boolean; // Dialog pour les movie


  // constructor(private menuService: MenuService, private storageService: StorageService,
  //             private message: MessageService, private tmdbService: TmdbService, ) {

  // }

  

  // async ngOnInit() {
  //   this.plats = await this.menuService.getAllMenu();
  //   this.listeMovie = await  this.tmdbService.getAllMovie();
  // }

  // selectCar(plat: Plats) {
  //   this.selectedPlat = plat;
  //   this.displayDialog = true;
  //   event.preventDefault();
  // }

  /**
  //  * ajout des films recent dans le panier
  //  * @param id
  //  */
  // ajoutFilmRecent(id: number){
  //   let film2019 = this.listeMovie.results.find(film => film.id === id);
  //   this.storageService.addMovieNote(film2019);
  //   this.message.add({severity:'success',
  //     summary:`Ajout du Film ${film2019.title} `,
  //     detail:'Votre choix a bien été ajouter dans votre panier'});
  // }

  /**
   * méthode d'ajout dans le panier
  //  * @param id
  //  */
  // addFilmNote(id: number) {
  //   let film = this.listeMovieHightRatet.results.find(films => films.id === id);
  //   this.storageService.addMovieNote(film);
  //   this.message.add({severity:'success',
  //     summary:`Ajout du Film ${film.title} `,
  //     detail:'Votre choix a bien été ajouter dans votre panier'});

  // }

  // /**
  //  * methode d'ajout du plat dans le panier après selection
  //  * @param id
  //  */
  // addMenus(id: string) {
  //   let plat = this.plats.find(plats => plats.id === id);
  //   this.storageService.addMenu(plat);
  //   this.message.add({
  //     severity: 'success',
  //     summary: `Ajout de ${plat.id} `,
  //     detail: 'Votre choix a bien été ajouter dans votre panier'
  //   });

  // }

  async init(){




  }
}
