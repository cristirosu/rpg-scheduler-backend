import { Component, OnInit } from '@angular/core';

import { DialogRef, ModalComponent, CloseGuard } from 'angular2-modal';
import { BSModalContext } from 'angular2-modal/plugins/bootstrap';

import { CategoryService } from '../shared/services/category.service';
import { TaskService } from '../shared/services/task.service';

import { Task } from '../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/task.model';
import { Category } from '../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/category.model';

export class CustomModalContext extends BSModalContext {
    public task: Task;
}

/**
 * A Sample of how simple it is to create a new window, with its own injects.
 */
@Component({
    selector: 'modal-content',
    styleUrls: ['app/tasks/task-edit.component.css'],
    templateUrl: 'app/tasks/task-edit.component.html'
})
export class CustomModal implements CloseGuard, ModalComponent<CustomModalContext>, OnInit {
    context: CustomModalContext;
    task: Task = new Task();
    categories: Array<any>;
    errorMessage: string;
    selectedCategory: boolean = false;

    constructor(
        public dialog: DialogRef<CustomModalContext>,
        private _categoryService: CategoryService,
        private _taskService: TaskService
    ) {
        this.context = dialog.context;
        if (dialog.context.task) this.task = dialog.context.task;

        dialog.setCloseGuard(this);
    }

    ngOnInit() {
        this.getCategories();
    }

    getCategories() {
        this._categoryService.list()
            .subscribe(
            categories => {
                this.categories = [];

                for (let i = 0; i < categories.length; i++) {
                    let category = categories[i];
                    this.categories.push({ id: category.id, text: category.name });
                }
            },
            error => this.errorMessage = <any>error,
        )
    };

    selected(value: any): void {
        this.selectedCategory = true;

        this.task.category = new Category(value.id, value.text);
        this.task.categoryId = value.id;
    }

    removed(value: any): void {
        this.selectedCategory = false;
    }

    save() {
        this._taskService.save(this.task).subscribe(
            () => this.closeModal()
        );
    }

    closeModal() {
        this.dialog.close();
    }
}
