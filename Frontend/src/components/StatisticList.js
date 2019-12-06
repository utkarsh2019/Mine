
import React, { Component } from "react";
import "../css/bootstrap.css"
import StatisticItem from "./StatisticItem";
import "../css/dashboard.css";

export default class StatisticList extends Component {
    render() {
        let statisticItems = this.props.statisticItems;
        let statisticCat = this.props.statisticCategory;
        let items = [];
        if (statisticItems != null) {
            statisticItems.forEach(item => {
                items.push(
                    <li class="list-group-item">
                        <StatisticItem name={item} category={statisticCat} />
                    </li>); 
            });
        }
        return (
            
            <div id="displayStyle">
                {items}
            </div>
        );
    }
}