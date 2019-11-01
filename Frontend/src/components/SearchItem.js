import React, { Component } from "react";
import "../css/bootstrap.css"
import "../css/SearchItem.css"

export default class SearchItem extends Component {

    constructor(props) {
        super(props);
    };

    render() {
        if (this.props.displayImage) {      
            return (
                <div class="card">
                    <a href={this.props.url}>
                        <img src={this.props.imageUrl} alt="Avatar" class="imageStyle"></img>
                        <div class="container">
                            <h4><b>{this.props.name}</b></h4> 
                            <p>{this.props.description}</p> 
                        </div>
                    </a>
                </div>
            );
        }
        return (
            <div class="card">
                <a href={this.props.url}>
                    <div class="container">
                        <h4><b>{this.props.name}</b></h4> 
                        <p>{this.props.description}</p> 
                    </div>
                </a>
            </div>
        );
    }
}