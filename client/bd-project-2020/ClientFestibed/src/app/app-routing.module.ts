import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SigninComponent} from './authentification/signin/signin.component';
import {SignupComponent} from './authentification/signup/signup.component';
import {MenusComponent} from './menus/menus.component';
import {AuthGuardService} from './service/auth-guard.service';
import {UserProfilComponent} from './user-profil/user-profil.component';
import {HomeComponent} from './home/home.component';
import {CommandeComponent} from './commande/commande.component';
import {PanierComponent} from './panier/panier.component';
import {FactureDetailComponent} from './commande/facture-detail/facture-detail.component';
import {ConfirmerCommandeComponent} from './confirmer-commande/confirmer-commande.component';
import { AjouterfestivalComponent } from './ajouterfestival/ajouterfestival.component';
import {SelectFestivalComponent} from "./home/select-festival/select-festival.component";
import {HebergementComponent} from './hebergement/hebergement.component' 
import { from } from 'rxjs';
import { ChambreHotelComponent } from './hebergement/chambre-hotel/chambre-hotel.component';
import { ListeChambreComponent } from './hebergement/liste-chambre/liste-chambre.component';


const routes: Routes = [
    {path: 'authentification/signin', component: SigninComponent},
    {path: 'authentification/signup', component: SignupComponent},
    {path: 'menus', component: MenusComponent},
    {path: 'AjoutFestival', component: AjouterfestivalComponent},
    {path: 'home/AjoutPlaceFestivale/:id/:dated/:datef', component: SelectFestivalComponent},
    {path: 'Hebergement/:id/:dated/:datef', component: HebergementComponent},
    {path: 'ChambreHotel/:id/:idheber/:type/:dated/:datef', component: ChambreHotelComponent},
    
    {path: 'ajoutfestival', component: AjouterfestivalComponent },
    {path: 'user/commande', component: CommandeComponent },
    {path: 'user/panier', component: PanierComponent },
    {path: 'chambre-liste/:numloge', component: ListeChambreComponent },
    {path: '', component: SigninComponent },
    {path: 'home', component: HomeComponent },
    {path: 'commande/facture/:idCommande', component: FactureDetailComponent},
    {path: '', redirectTo: 'films', pathMatch: 'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
