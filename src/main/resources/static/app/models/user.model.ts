import { Character } from './character.model';

export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  birthDate: Date;
  password: string;
  createdAt: Date;
  lastLogin: Date;
  character: Character;
}
