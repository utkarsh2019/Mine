/*import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"

export default class StatisticItem extends Component {

    constructor(props) {
        super(props);
    };

    render() {
        
        return (
            <p>{this.props.name}</p>
        );
    }
}*/
import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"
import {setSearchInput } from "../utils/DTSUtil";

export default class StatisticItem extends Component {

    constructor(props) {
        super(props);
    };
    clickforfun = () => {
        setSearchInput(
            this.props.name,
            this.props.category
        );
        alert(this.props.category);
        window.location.replace("/search");
    };

    render() {
        return (
         <button onClick={this.clickforfun}>{this.props.name}</button>
        );
    }
}