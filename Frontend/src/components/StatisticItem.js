import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"
import { setSearchInput } from "../utils/DTSUtil";
import { setSearchCategory } from "../utils/UserStorageUtil";
export default class StatisticItem extends Component {

    constructor(props) {
        super(props);
    };
    clickforfun = () => {
        setSearchInput(
            this.props.name
        );
        alert(this.props.category);
        setSearchCategory(this.props.category);
        
        window.location.replace("/search");
    };

    render() {
        return (
         <button onClick={this.clickforfun}>{this.props.name}</button>
        );
    }
}