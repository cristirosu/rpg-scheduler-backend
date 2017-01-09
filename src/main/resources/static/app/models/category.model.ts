import { Task } from './task.model';

export class Category {
  id: number;
  name: string;
  description: string;
  tasks: Task[];

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
  
}