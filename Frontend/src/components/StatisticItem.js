import React, { Component } from "react";
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
}