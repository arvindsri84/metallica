import { Component, OnInit, Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import {MatSnackBar} from '@angular/material';
import { Trade } from '../models/trade';
import { RefData } from '../models/refdata';
import { SearchCriteria } from '../models/search-criteria';
import { UserManagementService } from '../services/user-management.service';
import { TradeService } from '../services/trade.service';
import { RefdataService } from '../services/refdata-service';
import { TradeEditMode } from '../models/trade-edit-mode';
import { CommonUtilities } from '../models/common-utilities';

@Component({
  selector: 'app-trade-search',
  templateUrl: './trade-search.component.html',
  styleUrls: ['./trade-search.component.scss']
})
export class TradeSearchComponent implements OnInit {

  displayedColumns = ['tradeDate', 'comodity', 'side', 'quantity', 'price', 'counterparty', 'location'];
  
  dataSource: MatTableDataSource<Trade>;

  tradeElement: Trade;

  tradeSearchCrieria: SearchCriteria;

  editMode: TradeEditMode;

  supportedModes = TradeEditMode;

  selectedRowIndex: number = -1;

  constructor(
    public snackBar: MatSnackBar,
    private usermanagementService: UserManagementService,
    private tradeService: TradeService,
    public refdataService: RefdataService
  ) { }

  ngOnInit() {
    this.tradeSearchCrieria = new SearchCriteria();
    this.dataSource = new MatTableDataSource<Trade>();
    this.editMode = TradeEditMode.NONE;
  }

  onTradeSearch(search: SearchCriteria){
    let user = this.usermanagementService.getUser();
    if(user == null){
      this.showMessage("You are not logged in !. Please log in.")
      return;
    }

    this.tradeService.searchTrades(user,search).subscribe(
      result=> {
        result.forEach( r => {
          r.tradeDateAsDate = new Date(r.tradeDate);
        });
        this.dataSource = new MatTableDataSource<Trade>(result);
      },
      error => {
        this.dataSource = new MatTableDataSource<Trade>();
        this.showMessage("Error occured while searching for trades !")
      }
    );
  }

  onTradeSearchClear(search: SearchCriteria){
    this.tradeSearchCrieria = new SearchCriteria();
    
  }

  onTradeAdd(){
    console.log("Trade Add");

    this.tradeElement = <Trade>{
      tradeDate: null,
      comodity: null,
      side: null,
      quatity: null,
      price: null,
      counterparty: null,
      location: null,
      tradeDateAsDate: new Date()
    }

    this.editMode = TradeEditMode.ADD;
  }


  onTradeEditCancel(){
    console.log("Trade Edit Cancel");
    this.tradeElement = null;
    this.editMode = TradeEditMode.NONE;
  }

  onTradeSave(trade: Trade){
    console.log(trade);
    console.log(this.tradeElement);
    let user = this.usermanagementService.getUser();
    if(user == null){
      this.showMessage("You are not logged in !. Please log in.")
      return;
    }    
    this.tradeElement.tradeDate = CommonUtilities.formatDate(this.tradeElement.tradeDateAsDate);
    if(this.editMode == TradeEditMode.ADD){
      this.tradeService.createTrade(user,this.tradeElement).subscribe(
        result => {
          this.tradeElement = result;
          this.tradeElement.tradeDateAsDate = new Date(this.tradeElement.tradeDate);
          this.editMode = TradeEditMode.EDITABLE;
          this.dataSource.data.push(this.tradeElement);
          this.selectedRowIndex = this.tradeElement.tradeId;
          this.showMessage("New trade created successfully !");
        },
        error => {
          this.showMessage("Error occured while adding trade !")
        }        
      );

    }else if(this.editMode == TradeEditMode.EDIT){

      this.tradeService.updateTrade(user,this.tradeElement).subscribe(
        result => {
          this.tradeElement.tradeDateAsDate = new Date(this.tradeElement.tradeDate);
          this.editMode = TradeEditMode.EDITABLE;
          this.showMessage("Trade updated successfully !");
        },
        error => {
          this.showMessage("Error occured while updating the trade !")
        }
      );      
    };

  }


  onSelection(row){
      this.selectedRowIndex = row.tradeId;
      this.tradeElement = this.dataSource.data.find(
        t => {
          return t.tradeId == this.selectedRowIndex;
        }
      );
      this.editMode = TradeEditMode.EDITABLE;
      console.log(this.tradeElement);
  }


  onTradeEdit(){
    this.editMode = TradeEditMode.EDIT;
  }

  onTradeDelete(){
    let user = this.usermanagementService.getUser();
    if(user == null){
      this.showMessage("You are not logged in !. Please log in.")
      return;
    }      
    this.editMode = TradeEditMode.NONE;

    this.tradeService.deleteTrade(user,this.tradeElement).subscribe(
      result => {
        let index = this.dataSource.data.findIndex( t => {
          return t.tradeId == this.tradeElement.tradeId;
        });
        this.dataSource.data.splice(index,1);
        this.tradeElement = null;

        this.showMessage("Trade deleted successfully !");
      },
      error => {
        this.showMessage("Error occured while deleting the trade !")
      }
    );      
  
  }


  showMessage(message: string){
    this.snackBar.open(message,null, {
      duration: 2000,
      horizontalPosition: "right"
    });
  }

}


