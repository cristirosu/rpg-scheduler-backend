import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './auth/login.component';
import { TasksComponent } from './tasks/tasks.component';
import { AuthGuard } from './shared/guards/auth.guard';
import {RegisterComponent} from "./auth/register.component";
import {ActivationComponent} from "./auth/activation.component";
import {AchievementsComponent} from "./achievements/achievements.component";
import {FriendsComponent} from "./friends/friends.component";
import {FriendDetailsComponennt} from "./friends/friend.details.component";


const appRoutes: Routes = [

    { path: 'achievements', component: AchievementsComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'activation/:id', component: ActivationComponent },
    { path: 'register', component: RegisterComponent},
    { path: 'friends', component: FriendsComponent},
    { path: 'friends/:id', component: FriendDetailsComponennt},
    { path: '', component: TasksComponent, canActivate: [AuthGuard] },
    { path: '**', redirectTo: 'login' }

    // otherwise redirect to home
];

export const routing = RouterModule.forRoot(appRoutes);