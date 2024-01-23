import {Component, Input} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrl: './menu-item.component.scss'
})
export class MenuItemComponent {

  @Input()
  menuItem: MenuItem = {};

}
