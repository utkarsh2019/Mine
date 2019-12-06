import React, { Component } from "react";
import ReactDOM from "react-dom";
import "../css/bootstrap.css";
import "../css/search.css";
import { checkUserLoggedIn, getJwtToken } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { getCurrentUserField, setSearchCategory, getByValue, setSearchQuery } from "../utils/StorageUtil";
import SearchList from "./SearchList";
import { API_BASE_URL, CATEGORY_TYPES } from "../constants/Constants";
import axios from "axios";

export default class Search extends Component {

  constructor(props) {
    super(props);
    this.state = {
      searchResult: []
    };
    this.searchQuery = this.searchQuery.bind(this);
    this.externalSearchQuery = this.externalSearchQuery.bind(this);
    this.pageOnLoad = this.pageOnLoad.bind(this);
    this.setSearchResult = this.setSearchResult.bind(this);
    this.setSearchApi = this.setSearchApi.bind(this);
    this.setCategory = this.setCategory.bind(this);
    this.checkEnterSearch = this.checkEnterSearch.bind(this);
  }

  checkEnterSearch = (evt) => {
    if (evt.keyCode === 13) {
      evt.preventDefault();
      this.searchQuery();
    }
  };

  externalSearchQuery = (input) => {
    document.getElementById("searchbar").value = input;
    this.searchQuery();
  };

  searchQueryEvent = (evt) => {
    evt.preventDefault();
    this.searchQuery();
  }

  searchQuery = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    let searchQuery = document.getElementById("searchbar").value;
    let category = getByValue(CATEGORY_TYPES,getCurrentUserField("searchCategory"));

    axios({
      method: "post",
      url: API_BASE_URL + "/search/" + category,
      headers: {
        Authorization: type + " " + token
      },
      data: {
        query: searchQuery
      }
    })
      .then(response => {
        this.setState({searchResult: []});
        this.setSearchResult(response.data.responseObject);
        setSearchQuery("");
      })
      .catch(error => {
        alert(error);
      });
  };
  
  setSearchApi = (value, key) => {
    let searches = this.state.searchResult;
    if (Array.isArray(value) && value.length > 0) {
      searches.push(
        <div>
          <img class="apilogo" src={require("./../images/"+key+"logo.png")}></img>
          <SearchList searchItems={value}/>
        </div>
      );
      this.setState({searchResult: searches});
    }
  };

  setSearchResult = (responseObject) => {
    let responseObjectMap = new Map(Object.entries(responseObject));
    responseObjectMap.forEach((value, key) => {
      this.setSearchApi(value, key);
    });
  };

  initializeCategories = () => {
    let userPref = getCurrentUserField("categoryPreferences");
    let i;
    let pref = userPref.split(",");
    for(i=0; i<pref.length; i++){
      let listItem = document.createElement("a");
      listItem.innerHTML = pref[i];
      listItem.className = "dropdown-item";
      listItem.id = "item"+i;
      listItem.onclick = this.setCategory.bind(this);
      document.getElementById("dd").appendChild(listItem);
    }
    let category = getCurrentUserField("searchCategory");
    if (category === null || !pref.includes(category)) {
      setSearchCategory(document.getElementById("item0").innerHTML);
    }
    document.getElementById('categoriesdrop').innerHTML = category;
  };

  setCategory = (evt) => {
    evt.preventDefault();
    let val = evt.target.id;
    setSearchCategory(document.getElementById(val).innerHTML);
    document.getElementById('categoriesdrop').innerHTML = getCurrentUserField("searchCategory");
  }
  
  pageOnLoad = () => {
     if (getCurrentUserField("searchQuery") != "" && getCurrentUserField("searchQuery") != null){
      this.externalSearchQuery(getCurrentUserField("searchQuery")); 
    }
  }

  componentDidMount() {
    //An array of assets
    let scripts = [
      { src: "https://code.jquery.com/jquery-3.3.1.slim.min.js" },
      {
        src:
          "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      },
      {
        src:
          "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      }
    ];
    //Append the script element on each iteration
    scripts.map(item => {
      const script = document.createElement("script");
      script.src = item.src;
      script.async = true;
      document.body.appendChild(script);
    });
    this.initializeCategories();
    this.pageOnLoad();  
  }

  render () {

    if (!checkUserLoggedIn()) {
     return redirectToHome(this.props.location);
    }

    return (
      <div>
        <script
          src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
          integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
          crossorigin="anonymous"
        ></script>
        <script
          src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
          integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
          crossorigin="anonymous"
        ></script>
        <script
          src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
          integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
          crossorigin="anonymous"
        ></script>
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

        <body>
          <div class="dashboard row" id="searchbarrow">
            <div class="col searchclass" >
              <form class="form-inline">
                <input
                  id="searchbar"
                  class="form-control mr-sm-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                  onKeyUp={this.checkEnterSearch}
                ></input>
              </form>
            </div>
            <div class="col-md-auto searchclass" >
              <div class="dropdown">
                <button
                  id="categoriesdrop"
                  class="btn btn-secondary dropdown-toggle"
                  type="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                Categories              
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="dd">
                </div>
              </div>
            </div>
            <div class="col col-lg-2 searchclass" >
              <form class="form-inline">
                <button
                  class="btn btn-outline-success my-2 my-sm-0"
                  onClick={this.searchQueryEvent}
                >
                  Search
                </button>
              </form>
            </div>
          </div>

          <div class="container-fluid results">
            <div class="text-center"> 
              {this.state.searchResult}
            </div>
          </div>
          <footer>
            <div class="text-center" id="searchfooter">
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