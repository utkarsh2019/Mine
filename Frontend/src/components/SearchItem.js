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
                        </a>
                        <div class="container">
                            <a href={this.props.url}>
                                <h6 id="stoponeline"><b>{this.props.name}</b></h6> 
                            </a>
                            Description:
                            <div id="infodescription">{this.props.description}</div> 
                        </div>
                </div>
            );
        }
        if(this.props.displayEvent){
            return (
                <div class="card">
                        <div class="container">
                            <a href={this.props.url}>
                                <h6 id="stoponeline"><b>{this.props.name}</b></h6> 
                            </a>
                            Info:
                            <div id="infodescription">{this.props.description}</div> 
                            Date and Time:
                            <div id="eventdatetime">{this.props.datatime}</div> 
                        </div>
                </div>
            );
        }
        return (
            <div class="withoutimagecard">
                <a href={this.props.url}>
                    <div class="container">
                        <h4 id="hyperlinksearch"><b>{this.props.name}</b></h4> 
                        <p>{this.props.description}</p> 
                    </div>
                </a>
            </div>
        );
    }
}