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
        if(this.props.venue){
            let datetime=this.props.unediteddatetime
            let editeddatetime= datetime.split("T", 2)
            let date = editeddatetime[0].split("-",3)
            let year = date[0]
            let month = date[1]
            let day = date[2]
            let time = editeddatetime[1]
            time = time.split(':'); // convert to array

            // fetch
            var hours = Number(time[0]);
            var minutes = Number(time[1]);
            var seconds = Number(time[2]);

            // calculate
            var timeValue;

            if (hours > 0 && hours <= 12) {
            timeValue= "" + hours;
            } else if (hours > 12) {
            timeValue= "" + (hours - 12);
            } else if (hours == 0) {
            timeValue= "12";
            }
            
            timeValue += (minutes < 10) ? ":0" + minutes : ":" + minutes;  // get minutes
            timeValue += (seconds < 10) ? ":0" + seconds : ":" + seconds;  // get seconds
            timeValue += (hours >= 12) ? " P.M." : " A.M.";  // get AM/PM

            switch(month) {
                case "01":
                  month = "January";
                  break;
                case "02":
                  month = "February";
                  break;
                case "03":
                  month = "March";
                  break;
                case "04":
                  month = "April";
                  break;
                case "05":
                  month = "May";
                  break;
                case "06":
                  month = "June";
                  break;
                case "07":
                  month = "July";
                  break;
                case "08":
                  month = "August";
                  break;
                case "09":
                  month = "September";
                  break;
                case "10":
                  month = "October";
                  break;
                case "11":
                  month = "November";
                  break;
                case "12":
                  month = "December";
                  break;
                default:
                  // code block
              }
            return (
                <div class="withoutimagecard">
                        <div class="container">
                            <br></br>
                            <a href={this.props.url}>
                                <h6 id="stoponeline"><b>{this.props.name}</b></h6> 
                            </a>
                            <div id="infovenue">Venue:<br></br>{this.props.venue}</div> 
                            <br></br>
                            <div>Date and Time:<br></br>{month + " " + day + ", " + year
                        + " at " + timeValue}</div> 
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