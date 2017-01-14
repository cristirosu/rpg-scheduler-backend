import { Category } from './category.model'

export class Task {
  id: number = 0;
  categoryId: number = 0;
  name: string;
  description: string;
  dueDate: string;
  isFinished: boolean = false;
  category: Category = new Category(undefined,undefined);//Category;// = new Category();
  difficulty: string;
}


