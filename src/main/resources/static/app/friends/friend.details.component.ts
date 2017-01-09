import { Component, OnInit } from '@angular/core';

import { DialogRef, ModalComponent, CloseGuard } from 'angular2-modal';
import { BSModalContext } from 'angular2-modal/plugins/bootstrap';

import { CategoryService } from '../shared/services/category.service';


import { Task } from '../models/task.model';
import { Category } from '../models/category.model';
import { Friend } from '../models/friend.model';
import { FriendsService } from '../shared/services/friends.service';
import { ToastService } from '../shared/services/toast.service';

export class FriendsModalContext extends BSModalContext {
    public friend: Friend;
}

/**
 * A Sample of how simple it is to create a new window, with its own injects.
 */
@Component({
    selector: 'modal-content',
    templateUrl: 'app/friends/friend.details.component.html'
})
export class FriendDetailsModal implements CloseGuard, ModalComponent<FriendsModalContext>, OnInit {
    context: FriendsModalContext;
    friend: Friend = new Friend();

    constructor(
        public dialog: DialogRef<FriendsModalContext>,
        public friendsService: FriendsService,
        public toastService: ToastService
    ) {
        this.context = dialog.context;
        if (dialog.context.friend) this.friend = dialog.context.friend;

        dialog.setCloseGuard(this);
    }

    ngOnInit() {
        console.log("seeeeeeeeee");
    }

    deleteFriend(friend: Friend){
        if (confirm("Are you sure?")) {
      this.friendsService.deleteFriend(friend)
        .subscribe(
        () => {
            this.toastService.showSuccess("Succesfuly deleted friend");
            this.closeModal();
        },
        error => {
            this.toastService.showError("Error while deleting friend")
            this.closeModal();            
        }
        );
    }
    }

    closeModal() {
        this.dialog.close();
    }
}
