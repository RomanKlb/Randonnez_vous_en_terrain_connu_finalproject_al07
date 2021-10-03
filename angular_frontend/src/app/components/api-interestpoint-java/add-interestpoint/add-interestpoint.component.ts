import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Activity } from 'src/app/common/data/api-interestpoint-java-modele/activity';
import { Equipement } from 'src/app/common/data/api-interestpoint-java-modele/equipement';
import { InterestPoint } from 'src/app/common/data/api-interestpoint-java-modele/interestPoint';
import { Thematic } from 'src/app/common/data/api-interestpoint-java-modele/thematic';
import { InterestPointService } from 'src/app/common/_services/interest-point.service';
import { UploadFilesService } from 'src/app/common/_services/upload-files.service';

@Component({
  selector: 'app-add-interestpoint',
  templateUrl: './add-interestpoint.component.html',
  styleUrls: ['./add-interestpoint.component.css']
})
export class AddInterestpointComponent implements OnInit {

  private thematiclist = Array<Thematic>();
  private activitylist = Array<Activity>();
  private equipementlist = Array<Equipement>();

  public interestPointDTO = {
    nomDuLieu: '',
    text: '',
    tempsActivite: '',
    latitude: '',
    longitude: '',
  }

  errorMessage = '';
  submitted = false;

  dropdownListT: any;
  dropdownListA: any;
  dropdownListE: any;

  selectedItemsT: any;
  selectedItemsA: any;
  selectedItemsE: any;

  dropdownSettingsT = {};
  dropdownSettingsA = {};
  dropdownSettingsE = {};
  // listThematic: any;

  selectedFiles: any;
  imageSelected: any;
  currentFile?: File;
  progress = 0;
  message = '';
  interestPointRecup: InterestPoint = new InterestPoint();

  fileInfos?: Observable<any>;

  constructor(private interestPointService: InterestPointService,
    private uploadService: UploadFilesService,
    private router:Router) { }

  ngOnInit(): void {
    this.dropdownListT = this.getDataThematic();
    this.dropdownSettingsT = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'nomThematic',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: false
    };

    this.dropdownListA = this.getActivity();
    this.dropdownSettingsA = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'activity',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: false
    };

    this.dropdownListE = this.getEquipement();
    this.dropdownSettingsE = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'equipement',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 10,
      allowSearchFilter: false
    };
  }

  saveInterestPoint(): void {

    console.log(this.equipementlist + " *** " + this.activitylist + " *** " + this.thematiclist + " *** " + JSON.stringify(this.interestPointDTO))

    
      const file: File | null = this.selectedFiles.item(0);
    this.imageSelected = file

    let interestpoint = {
      nomDuLieu: this.interestPointDTO.nomDuLieu,
      text: this.interestPointDTO.text,
      tempsActivite: this.interestPointDTO.tempsActivite,
      latitude: this.interestPointDTO.latitude,
      longitude: this.interestPointDTO.longitude,
      listThematic: this.thematiclist,
      listActivity: this.activitylist,
      listEquipement: this.equipementlist,
      idImage:''
    //  file : file
    };

    // console.log("methode saveInterestPoint angular : " + JSON.stringify(data));
    var formData = new FormData()
    formData.append('file',   this.imageSelected)
   // formData.append('interestpoint',JSON.stringify(interestpoint))


   // this.interestPointService.addInterestPoint$(formData)
    this.interestPointService.addImage(formData)
      .subscribe(
        response => {
          interestpoint.idImage = response.idFile
         console.log(interestpoint.idImage)

       this.interestPointService.addInterestPoint$(interestpoint).subscribe(
        response => {
          console.log(response)
        }
       )

         console.log(response);
          console.log(response.idFile);
          console.log(interestpoint);
          this.submitted = true;
        },
        (err: { error: { message: string; }; }) => {
          this.errorMessage = err.error.message;
          console.log(err);
        });
  }

  upload(): void {
    this.progress = 0;
    
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.uploadService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles();
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          });
      }

      this.selectedFiles = undefined;
    }
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  onSelectAll(items: any) {
    console.log(items);
  }

  onItemSelectT(item: any) {
    console.log(item + " " + item.nomThematic);
    this.thematiclist.push(item);
    console.log(JSON.stringify(this.thematiclist));
  }

  onItemSelectA(item: any) {
    console.log(item + " " + item.activity);
    this.activitylist.push(item);
    console.log(JSON.stringify(this.activitylist));
  }

  onItemSelectE(item: any) {
    console.log(item + " " + item.equipement);
    this.equipementlist.push(item);
    console.log(JSON.stringify(this.equipementlist));
  }


  getDataThematic() {
    return [
      { item_id: 1, nomThematic: 'CASCADE' },
      { item_id: 2, nomThematic: 'LAC' },
      { item_id: 3, nomThematic: 'PANORAMA' },
      { item_id: 4, nomThematic: 'PATRIMOINE' },
      { item_id: 5, nomThematic: 'SPORT'},
      { item_id: 6, nomThematic: 'STATION' }
    ];
  }

  getActivity() {
    return [
      { item_id: 1, activity: 'ALPINISME' },
      { item_id: 2, activity: 'BAIGNADE' },
      { item_id: 3, activity: 'CANOE_KAYAK' },
      { item_id: 4, activity: 'ESCALADE' },
      { item_id: 5, activity: 'LUGE' },
      { item_id: 6, activity: 'RANDONNEE' },
      { item_id: 7, activity: 'SUP_PADDLE' },
      { item_id: 8, activity: 'SKI' },
      { item_id: 9, activity: 'SNOWBOARD' },
      { item_id: 10, activity: 'TOURISME' },
      { item_id: 11, activity: 'VISITE' },
    ];
  }


  getEquipement() {
    return [
      { item_id: 1, equipement: 'EQUIPEMENT_ALPINISTE' },
      { item_id: 2, equipement: 'EQUIPEMENT_DE_RANDONNEE' },
      { item_id: 3, equipement: 'EQUIPEMENT_DE_SKI' },
      { item_id: 4, equipement: 'EQUIPEMENT_NAUTIQUE' },
      { item_id: 5, equipement: 'EQUIPEMENT_PHOTOGRAPHIE'},
     
    ];
  }


  goToAdminListIP(){
    this.router.navigate(['/dashboard-admin/interestpoints'])
  }


}
