import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/dashboard.css";
import { checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import StatisticList from "./StatisticList";
export default class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      statisticResult: {"video": ["example1", "example2", "example3"], 
      "movies":["mexample1", "mexample2", "mexample3"]}
    };
    
  }
  render() {
    if (!checkUserLoggedIn()) {
      return redirectToHome(this.props.location);
    }

    return (
      <div>
        <div>
          <nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid fixed-top">
            <a class="navbar-brand" href="#">
              <img
                src={require("./../images/minelogo.png")}
                width="50"
                height="50"
                class="d-inline-block"
                alt=""
              ></img>
              Mine
            </a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a href={"/dashboard"} class="nav-item nav-link">
                  Dashboard
                </a>
                <a href={"/trending"} class="nav-item nav-link">
                  Trending
                </a>
                <a href={"/search"} class="nav-item nav-link">
                  Search
                </a>
              </div>
            </div>
            <ul class="nav justify-content-end">
              <li class="nav-item">
                <a href={"/profile"}>
                  <button type="button" class="btn btn-info navsignlog">
                    Account
                  </button>
                </a>
              </li>
            </ul>
          </nav>
        </div>

        <body className="dashboard">
        
          <div class="container-fluid trending">
            <div class="container">
              <div class="row">
                <div class="col-sm" id="dashtabcol">
                  <button type="button" class="btn btn-info" id="dashtab">Previous Searches</button>
                </div>
                <div class="col-sm" id="dashtabcol">
                  <button type="button" class="btn btn-info" id="dashtab">Most Frequent Searches</button>
                </div>
              </div>
            </div>
            <div class="text-center">
              
              <div class="container-fluid results">
                <div class="text-center"> 
                  {/*{this.state.dashboardResult}*/}
                  <h6>Videos</h6>
                  <StatisticList statisticItems={this.state.statisticResult.video}/>

                </div>
              </div>
            </div>
          </div>
          <footer>
            <div class="footer text-center">
              <p>
                Mine App, 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja
                Tewari
              </p>
            </div>
          </footer>
        </body>
      </div>
    );
  }
}
