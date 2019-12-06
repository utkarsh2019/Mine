import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/trending.css";
import { redirectToHome } from "../utils/RedirectUtil";
import { checkUserLoggedIn,getJwtToken } from "../utils/CookieUtil";
import StatisticList from "./StatisticList";
import axios from "axios";
import { API_BASE_URL, CATEGORY_TYPES } from "../constants/Constants";

export default class Trending extends Component {
  constructor(props) {
    super(props);
    this.state = {
      statisticResult: []
    };
    this.setSearchResult = this.setSearchResult.bind(this);
    this.setSearchApi = this.setSearchApi.bind(this);
    this.searchTrendingQuery = this.searchTrendingQuery.bind(this);
  }
  
  searchTrendingQuery = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];
    
    axios({
      method: "get",
      url: API_BASE_URL + "/search/trending",
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


  setSearchResult = (responseObject) => {
    let responseObjectMap = new Map(Object.entries(responseObject));
    responseObjectMap.forEach((value, key) => {
      this.setSearchApi(value, key);
    });
  };

  setSearchApi = (value, key) => {
    let statistics = this.state.statisticResult;
    let statisticsSearchCategory = CATEGORY_TYPES.get(key);
    
    statistics.push(
      <div>
        <h3>{statisticsSearchCategory}</h3>    
        <StatisticList statisticItems={value} statisticCategory={statisticsSearchCategory}/>
      </div>
    );
    this.setState({statisticResult: statistics});
  };

  componentDidMount(){
    this.searchTrendingQuery();
  };

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

            <div class="text-center">
              <div class="container-fluid dashboardresults">
              <br></br>
              <br></br>
                <div class="text-center"> 
                  {this.state.statisticResult}
                </div>
              </div>
            </div>
          
          <footer>
          <div class="editfooter text-center">
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
