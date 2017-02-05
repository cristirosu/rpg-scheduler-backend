import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Achievement} from "../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/achievement.model";
import {AchievementService} from "../shared/services/achievement.service";
import {UserService} from "../shared/services/user.service";
import {User} from "../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/user.model";

@Component({
    templateUrl: 'app/friends/friend.details.component.html',
})
export class FriendDetailsComponennt implements OnInit,OnDestroy {
    achievements:Achievement;
    friend:User;
    id:number;
    private sub:any;

    constructor(private route:ActivatedRoute,
                private _achievementService:AchievementService,
                private _userService:UserService) {
        console.log("constrcutct");
    }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            this.id = +params['id']; // (+) converts string 'id' to a number
            this.getAchievements(this.id);
            this.getFriend(this.id);
            // In a real app: dispatch action to load the details here.
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    getAchievements(id:number) {
        this._achievementService.getAchievementsByUserId(id)
            .subscribe(
                achievements => this.achievements = achievements,
                //TODO: toast error
                //error => this.errorMessage = <any>error
            );
    }

    getFriend(id:number) {
        this._userService.getUserById(id)
            .subscribe(
                friend => {
                    this.friend = friend
                    console.log(this.friend);
                }
                //TODO: toast error
                //error => this.errorMessage = <any>error
            );
    }
}
