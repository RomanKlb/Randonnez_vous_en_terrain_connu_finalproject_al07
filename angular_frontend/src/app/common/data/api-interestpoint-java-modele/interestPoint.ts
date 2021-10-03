import { Activity } from "./activity";
import { Equipement } from "./equipement";
import { LocationGeographical } from "./locationGeographical";
import { Thematic } from "./thematic";

export class InterestPoint{
    id?: any;
    idFile?:any
    nomDuLieu?: string;
    text?: any;
    tempsActivite?: string;
    locationGeographical?: LocationGeographical;
    listEquipement?: Array<Equipement> = [];
    listActivity?: Array<Activity> = [];
    listThematic?: Array<Thematic> = [];
}