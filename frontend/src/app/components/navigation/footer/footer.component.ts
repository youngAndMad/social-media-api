import { AfterViewInit, Component, ElementRef, ViewChild } from "@angular/core";
import { MatToolbar } from "@angular/material/toolbar";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements AfterViewInit {
  @ViewChild("toolbar") toolbar: MatToolbar;
  @ViewChild("main") main: ElementRef;
  @ViewChild("footer") footer: ElementRef;
  constructor(){
  }
  ngOnInit(){
  }
  ngAfterViewInit(){
  }
}
