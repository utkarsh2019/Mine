import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/dashboard.css";
import { checkUserLoggedIn,getJwtToken } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import StatisticList from "./StatisticList";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";

export default class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      statisticResult: []
    };
    this.searchFreqQuery = this.searchFreqQuery.bind(this);
    this.setSearchResult = this.setSearchResult.bind(this);
    this.setSearchApi = this.setSearchApi.bind(this);
    //this.setCategory = this.setCategory.bind(this);
  }
  searchPrevQuery = (evt) => {
    evt.preventDefault();
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];
    
    axios({
      method: "get",
      url: API_BASE_URL + "/user/me/search/previous",
      headers: {
        Authorization: type + " " + token
      }
    })
      .then(response => {
      console.log(response);
      this.setState({statisticResult: []});
        let search = response.data.responseObject;  
        this.setSearchResult(search);
      })
      .catch(error => {
        alert(error.response.data.errorMessage);
      });
  };


  searchFreqQuery = (evt) => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];
    
    axios({
      method: "get",
      url: API_BASE_URL + "/user/me/search/frequent",
      headers: {
        Authorization: type + " " + token
      }
    })
      .then(response => {
      console.log(response);
      this.setState({statisticResult: []});
        let search = response.data.responseObject;  
        this.setSearchResult(search);
      })
      .catch(error => {
        alert(error);
      });
  };

  setSearchResult = (responseObject) => {
    let responseObjectMap = new Map(Object.entries(responseObject));
    responseObjectMap.forEach((value, key) => {
      this.setSearchApi(value, key);
    });
  };

  setSearchApi = (value, key) => {
    let statistics = this.state.statisticResult;
    
    statistics.push(
      <div>
        <StatisticList statisticItems={value} statisticCategory={key}/>
      </div>
    );
    this.setState({statisticResult: statistics});
  };

  render() {
    if (!checkUserLoggedIn()) {
      return redirectToHome(this.props.location);
    }

    return (
      <div>
        <div className="bodyDashboard">
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

        
            <div class="dashboard container-fluid">
              <div class="row">
                <div class="col-sm" id="dashtabcol">
                  <button type="button" onClick={this.searchPrevQuery.bind(this)} class="btn btn-info dashtab" id="prevtab">Previous Searches</button>
                </div>
                <div class="col-sm" id="dashtabcol">
                  <button type="button" onClick={this.searchFreqQuery} class="btn btn-info dashtab" id="freqtab">Most Frequent Searches</button>
                </div>
              </div>
            </div>
          

        <div class="text-center">
          <div class="container-fluid dashboardresults">
            <div class="text-center"> 
              {this.state.statisticResult}
            </div>
          </div>
        </div>
        <footer>
            <div class="dashboardfooter text-center">
              <p>
                Mine App, 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja
                Tewari
              </p>
            </div>
        </footer>
      </div>
    );
  }
}
