import { Component, OnInit } from '@angular/core';

import { Friend } from '../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/friend.model';

import { Modal, BSModalContext } from 'angular2-modal/plugins/bootstrap';
import { Overlay, overlayConfigFactory } from 'angular2-modal';
import { FriendDetailsModal } from './friend.modal.component';
import { FriendsService } from '../shared/services/friends.service'
import { ToastService } from '../shared/services/toast.service'

@Component({
    templateUrl: 'app/friends/friends.component.html',
    providers: [Modal]
})
export class FriendsComponent implements OnInit{

    friendsEmail : string = "";
    friends: Array<Friend> = [];

    constructor(
        public _modal: Modal,
        public toastService: ToastService,
        public friendsService: FriendsService
    ) { }

    ngOnInit(){
        this.getFriends();
    }

    openFriendDetails(friend: Friend) {
        console.log(friend);
        return this._modal.open(FriendDetailsModal, overlayConfigFactory({ friend: friend }, BSModalContext))
            .then(dialog => { console.log(dialog.result); return dialog.result })
            .then(() => console.log("here"));
    }

    deleteFriend(friend: Friend) {
        if (confirm("Are you sure?")) {
            this.friendsService.deleteFriend(friend)
                .subscribe(
                () => {
                    this.toastService.showSuccess("Succesfuly deleted friend");
                    this.getFriends();
                },
                error => {
                    this.toastService.showError("Error while deleting friend")
                }
                );
        }
    }

    addFriend(){
        console.log(this.friendsEmail);
        if(this.friendsEmail === undefined || this.friendsEmail.trim() === ""){
            this.toastService.showError("Email address is mandatory");
        } else{
            this.friendsService.addFriend(this.friendsEmail)
                .subscribe(
                    () => {
                        this.toastService.showSuccess("Friend request sent!")
                    },
                    error => {
                        this.toastService.showError("Error while adding friend");
                    }
                )
        }
    }

    getFriends(){
        this.friendsService.getFriends()
            .subscribe(
            (response) => {
                this.friends = response;
        },
        error => {
            this.toastService.showError("There was an error while retrieving friends.")
        })
    }


}