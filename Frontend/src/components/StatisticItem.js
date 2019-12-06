import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"
import { setSearchCategory, setSearchQuery } from "../utils/StorageUtil";

export default class StatisticItem extends Component {

    constructor(props) {
        super(props);
    };
    
    redirectSearch = () => {
        setSearchCategory(this.props.category);
        setSearchQuery(this.props.name);
        setSearchCategory(this.props.category);
        
        window.location.replace("/search");
    };

    render() {
        return (
            <button id="statButton"onClick={this.redirectSearch}>{this.props.name}</button>
        );
    }
}