import React, { Component } from "react";
import "../css/bootstrap.css"
import SearchItem from "./SearchItem";
import "../css/search.css";
//import uniqueid from 'uniqueid';

export default class SearchList extends Component {
    render() {
        let searchItems = this.props.searchItems;
        let items = [];
        if (searchItems != null) {
            searchItems.forEach(item => {
                items.push(
                    <li class="list-group-item" id="displaycard">

                <SearchItem 
                    name={item.name} 
                    description={item.description} 
                    venue={item.venue}
                    unediteddatetime={item.datetime}
                    url={item.url} 
                    imageUrl={item.imageUrl} 
                    displayImage={(item.imageUrl != null && item.imageUrl != undefined)}/></li>); 
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