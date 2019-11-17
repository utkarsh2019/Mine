import React, { Component } from "react";
import "../css/bootstrap.css"
import StatisticItem from "./StatisticItem";
import "../css/dashboard.css";
//import uniqueid from 'uniqueid';

export default class StatisticList extends Component {
    render() {
        let statisticItems = this.props.statisticItems;
        let items = [];
        if (statisticItems != null) {
            statisticItems.forEach(item => {
                items.push(
                    <li class="list-group-item">

                <StatisticItem 
                    name={item} 
                   // description={item.description} 
                    //url={item.url} 
                   // imageUrl={item.imageUrl} 
                   /* displayImage={(item.imageUrl != null && item.imageUrl != undefined)}*//></li>); 
            });
        }
        return (
            <div id="displayStyle">
                {items}
            </div>
        );
    }
}