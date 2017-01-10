import { Component, OnInit } from '@angular/core';

import { UserService } from '../shared/services/user.service';
import { TaskService } from '../shared/services/task.service';
import { CategoryService } from '../shared/services/category.service';

import { User } from '../models/user.model';
import { Task } from '../models/task.model';
import { Category } from '../models/category.model';

import { Modal, BSModalContext } from 'angular2-modal/plugins/bootstrap';
import { CustomModalContext, CustomModal } from './task-edit.component';
import { Overlay, overlayConfigFactory } from 'angular2-modal';
import {WebSocketService} from "../shared/services/websocket.service";
import {ToastService} from "../shared/services/toast.service";

@Component({
  templateUrl: 'app/tasks/tasks.component.html',
  providers: [Modal],
})
export class TasksComponent implements OnInit {
  user: User;
  tasks: Task[] = [];
  categories: any[];
  filters: Object = {};
  showFilters: boolean = false;
  errorMessage: string;
  static accountNotifications: boolean = false;

  constructor(
    private _userService: UserService,
    private _taskService: TaskService,
    private _categoryService: CategoryService,
    public _modal: Modal,
    private socketService: WebSocketService,
    private toastService: ToastService
  ) { }

  ngOnInit() {
    this.getUser();
    this.getTasks();
    this.getCategories();
  }

  getUser() {
    this._userService.getUser()
      .subscribe(
      user => {
        this.user = user;
        if(!TasksComponent.accountNotifications){
          this.subscribeForAccountNotifications();
          TasksComponent.accountNotifications = true;
        }
      },
      error => this.errorMessage = <any>error
      );
  }

  getTasks() {
    this._taskService.list(this.filters)
      .subscribe(
      tasks => this.tasks = tasks,
      error => this.errorMessage = <any>error
      );
  }

  subscribeForAccountNotifications(){
    var self = this;
    this.socketService.subscribe(this.user.id, function (greeting : any) {
      console.log("recieved a message");
      console.log(greeting);
      self.toastService.showInfo(greeting.body);
      console.log("-=->>>>>>>>>>>>>>>>>>>>>>")
    });
  }

  getCategories() {
    this._categoryService.list()
      .subscribe(
      categories => {
        this.categories = [];
        // this.categories = categories.map(function(category){ return {id: category.id, text: category.name}});
        for (let i = 0; i < categories.length; i++) {
          let category = categories[i];
          this.categories.push({ id: category.id, text: category.name });
        }
      },
      error => this.errorMessage = <any>error,
    )
  };

  deleteTask(task: Task) {
    if (confirm("Are you sure?")) {
      this._taskService.delete(task)
        .subscribe(
        (tasks) => this.tasks = tasks,
        error => this.errorMessage = <any>error
        );
    }
  }

  updateTaskStatus(task: Task) {
    this._taskService.updateStatus(task)
      .subscribe(
      (tasks) => {
        this.tasks = tasks
        this.getUser();
      },
      error => this.errorMessage = <any>error
      );
  }

  resetFilters() {
    this.filters = {};
  }

  openSaveTaskWindow(task: Task) {
    return this._modal.open(CustomModal, overlayConfigFactory({ task: task }, BSModalContext))
      .then(dialog => {console.log(dialog.result); return dialog.result})
      .then(() => this.getTasks());
  }
}
