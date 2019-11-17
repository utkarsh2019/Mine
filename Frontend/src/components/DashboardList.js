import React, { Component } from "react";
import "../css/bootstrap.css"
import DashboardItem from "./DashboardItem";
import "../css/search.css";
//import uniqueid from 'uniqueid';

export default class DashboardList extends Component {
    render() {
        let dashboardItems = this.props.dashboardItems;
        let items = [];
        if (dashboardItems != null) {
            dashboardItems.forEach(item => {
                items.push(
                    <li class="list-group-item" id="displaycard">

                <SearchItem 
                    name={item.name} 
                   // description={item.description} 
                    url={item.url} 
                   // imageUrl={item.imageUrl} 
                   /* displayImage={(item.imageUrl != null && item.imageUrl != undefined)}*//></li>); 
            });
        }
        return (
            <div id="searchitemdisplayunit">
                <ul class="list-group list-group-horizontal">
                    {items}
                </ul>
            </div>
            
        );
    }
}