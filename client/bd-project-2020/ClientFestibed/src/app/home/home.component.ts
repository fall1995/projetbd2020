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

  slides:any[];
  films: any;
  filmsNoel:any;

  constructor(private tmdbService: TmdbService) { }
  

  ngOnInit() {
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
    this.tmdbService.init( environment.tmdbKey );
    await this.tmdbService.getAllMovie(1, 1990, 2019).then(data =>{( this.films = data.results)});
    await this.tmdbService.searchMovie(1,'noel').then(data =>{( this.filmsNoel = data.results.slice(0, 6)) 
      console.log(this.filmsNoel)});

      this.slides=[{index:0,nom:'Pâtes au fromage',prix:'8,50',image:'https://i.f1g.fr/media/madame/1900x800_crop/sites/default/files/img/2018/04/les-plats-prepares-bios-sont-ils-vraiment-meilleurs-.jpg'},
                  {index:1,nom:'Salade du chef',prix:'9',image:'http://blog-primeal.fr/wordpress/http://blog-primeal.fr/category/nos-produits-en-images//2017/06/Les-cles-d-un-repas-equilibre.jpg.jpg'},
                  {index:2,nom:'Pâtes aux asperges',prix:'11',image:'https://www.dietetiquesportive.com/wp-content/uploads/2008/06/repas-veille-competition-feculents.jpg'},
                  {index:3,nom:'Quiche Lorraine',prix:'9,25',image:'https://www.cookomix.com/wp-content/uploads/2017/06/quiche-lorraine-thermomix-1280x720.jpg'},
                  {index:4,nom:'Lasagne maison',prix:'8',image:'https://assets.afcdn.com/recipe/20180119/76936_w1024h768c1cx2680cy1786cxt0cyt0cxb5361cyb3573.jpg'}];
    // (this.films = this.films.results)

  }
}
