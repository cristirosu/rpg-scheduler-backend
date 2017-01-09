import { Character } from './character.model';

export class User {
  id: number;
  first_name: string;
  last_name: string;
  email: string;
  phone_number: string;
  birth_date: Date;
  password: string;
  created_at: Date;
  last_login: Date;
  character: Character;
}
