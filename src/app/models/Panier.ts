import { PanierItem } from "./PanierItem";

export class Panier
{
  id?: number;
  totalPrice?: number;
  itemsNumber?: number;
  client?: number;
  Date?: Date;
  items?:PanierItem[];


}
