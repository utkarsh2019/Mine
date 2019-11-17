import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"

export default class DashboardItem extends Component {

    constructor(props) {
        super(props);
    };

    render() {
        
        return (
            <div class="withoutimagecard">
                <a href={this.props.url}>
                    <div class="container">
                        <h4 id="hyperlinksearch"><b>{this.props.name}</b></h4> 
                       
                    </div>
                </a>
            </div>
        );
    }
}