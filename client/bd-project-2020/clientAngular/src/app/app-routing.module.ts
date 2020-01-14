import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SigninComponent} from './authentification/signin/signin.component';
import {SignupComponent} from './authentification/signup/signup.component';
import {AuthGuardService} from './service/auth-guard.service';
import {UserProfilComponent} from './user-profil/user-profil.component';
import {HomeComponent} from './home/home.component';
import {CommandeComponent} from './commande/commande.component';
import {PanierComponent} from './panier/panier.component';


const routes: Routes = [
    {path: 'authentification/signin', component: SigninComponent},
    {path: 'authentification/signup', component: SignupComponent},
    {path: 'user/dashbord', component: UserProfilComponent },
    {path: 'user/commande', component: CommandeComponent },
    {path: 'user/panier', component: PanierComponent },
    {path: '', component: HomeComponent },
    {path: '', redirectTo: 'films', pathMatch: 'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
