import { Gender } from "../enums/Gender";
import { Address } from "./Address";

export interface User {
  id: number
  firstName:string
  lastName:string
  email:string
  isPrivateAccount:boolean
  gender:Gender
  address:Address
  status:string

}
