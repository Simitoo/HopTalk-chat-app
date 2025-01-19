import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './components/home/home.component';
import { LoginPage } from './components/login/login.component';

export const routes: Routes = [
    {path: '', component: HomePage},
    {path: 'login', component: LoginPage},
    

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule{ }
