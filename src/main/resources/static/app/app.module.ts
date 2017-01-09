import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { TasksComponent } from './tasks/tasks.component';
import { AchievementsComponent } from './achievements/achievements.component';

import { RouterModule, Routes } from '@angular/router';

import { UserService } from './shared/services/user.service';
import { CategoryService } from './shared/services/category.service';
import { TaskService } from './shared/services/task.service';
import { AchievementService } from './shared/services/achievement.service';

import { Angular2FontawesomeModule } from 'angular2-fontawesome/angular2-fontawesome';

import { ModalModule } from 'angular2-modal';
import { BootstrapModalModule } from 'angular2-modal/plugins/bootstrap';
import { CustomModal } from './tasks/task-edit.component';
import { FriendDetailsModal } from './friends/friend.details.component';
import { SelectModule } from 'ng2-select/ng2-select';
import { Ng2DatetimePickerModule } from 'ng2-datetime-picker';

import { MdInputModule } from '@angular2-material/input';
import { MdSidenavModule } from '@angular2-material/sidenav';
import { MdButtonModule} from '@angular2-material/button';
import { LoginComponent } from "./auth/login.component";
import { RegisterComponent } from "./auth/register.component";
import { ActivationComponent } from "./auth/activation.component";
import { routing } from './app.routing';

import { AuthGuard } from './shared/guards/auth.guard';

import {ReactiveFormsModule} from "@angular/forms";

import { AuthenticationService } from "./shared/services/authentication.service";
import { WebSocketService } from "./shared/services/websocket.service"
import {ToastModule} from 'ng2-toastr/ng2-toastr';
import {ToastService} from './shared/services/toast.service';
import {FriendsService} from './shared/services/friends.service';
import {FriendsComponent} from './friends/friends.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

const appRoutes:Routes = [
    {path: '', component: TasksComponent},
    {path: 'achievements', component: AchievementsComponent}
];

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        MdInputModule,
        MdSidenavModule,
        MdButtonModule,
        Angular2FontawesomeModule,
        Ng2DatetimePickerModule,
        ReactiveFormsModule,

    ModalModule.forRoot(),
    BootstrapModalModule,
    SelectModule,
    ToastModule,
    routing

        // RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        AppComponent,

    TasksComponent,
    AchievementsComponent,
    CustomModal,
    FriendDetailsModal,
    LoginComponent,
    RegisterComponent,
    ActivationComponent,
    FriendsComponent
  ],
  providers: [
    UserService,
    CategoryService,
    TaskService,
    AchievementService,
    AuthGuard,
    AuthenticationService,
    ToastService,
    FriendsService,
      WebSocketService,
      {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent],
  entryComponents: [CustomModal, FriendDetailsModal]
})
export class AppModule { }
