export interface CustomerDTO {

  id?: number;
  name?: string;
  email?: string;
  gender?: 'Male' | 'Female';
  age?: number;
  roles?: string[],
  username?: string;
}
