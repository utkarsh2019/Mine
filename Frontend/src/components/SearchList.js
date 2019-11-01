import React, { Component } from "react";
import "../css/bootstrap.css"
import SearchItem from "./SearchItem";
import uniqueid from 'uniqueid';

export default class SearchList extends Component {
    render() {
        let searchItems = this.props.searchItems;
        let items = [];
        if (searchItems != null) {
            searchItems.forEach(item => {
                items.push(<SearchItem 
                    name={item.name} 
                    description={item.description} 
                    url={item.url} 
                    imageUrl={item.imageUrl} 
                    displayImage={(item.imageUrl != null && item.imageUrl != undefined)}/>); 
            });
        }
        return (
            <div>
                {items}
            </div>
        );
    }
}