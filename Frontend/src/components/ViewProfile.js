import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/Profile.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";
import { getJwtToken, deleteCookies, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { setCurrentUser, getCurrentUser, clearCurrentUser } from "../utils/StorageUtil";
import Popup from "./Popup";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.setUserFields = this.setUserFields.bind(this);
    this.loadUser = this.loadUser.bind(this);
    this.state = {
      showPopup: false
    };
  }

  togglePopup(){
    this.setState({
      showPopup: !this.state.showPopup
    });
  }

  logout = () => {
    clearCurrentUser();
    deleteCookies();
    window.location.replace("/");
  };

  setUserFields = (user) => {
    document.getElementById("name").innerHTML = "Name: " + user.name;
    document.getElementById("email").innerHTML = "Email: " + user.email;

    document.getElementById("num").innerHTML = "No of Searches Displayed: " + user.noOfSearches;

    if(user.profilePicUrl != null){
      document.getElementById("profileImage").src = user.profilePicUrl;
    }

    let pref = user.categoryPreferences.split(",");

    let i;
    for(i=0; i<pref.length; i++){
      let listItem = document.createElement("li");
      listItem.innerHTML = pref[i];
      listItem.className = "list-group-item";
      listItem.id = "item"+i;
      document.getElementById("categories").appendChild(listItem);
    }

  };

  loadUser = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    axios({
      method: "get",
      url: API_BASE_URL + "/user/me",
      headers: {
        Authorization: type + " " + token
      }
    })
      .then(response => {
        setCurrentUser(
          response.data.responseObject.name,
          response.data.responseObject.email,
          response.data.responseObject.profilePicUrl,
          response.data.responseObject.provider,
          response.data.responseObject.noOfSearches,
          response.data.responseObject.categoryPreferences,
          response.data.responseObject.apiList
        );
        this.setUserFields(getCurrentUser());
      })
      .catch(error => {
        alert(error);
      });
  };

  deleteAccount = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    axios({
      method: "delete",
      url: API_BASE_URL + "/user/me",
      headers: {
        Authorization: type + " " + token
      }
    })
      .then(function(response) {
        deleteCookies();
        window.location.replace("login");
      })
      .catch(function(error) {
        alert(error);
      });
  };

  render() {
    if (!checkUserLoggedIn()) {
      return redirectToHome(this.props.location);
    }
    return (
      <div className="Profile">
        <div>
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                <button
                  type="button"
                  class="btn btn-info navsignlog"
                  onClick={this.logout}
                >
                  Logout
                </button>
              </li>
            </ul>
          </nav>
        </div>

        <body>
          <div className="row">
            <br></br>
            <br></br>
          </div>
          <div className="row">
            <div className="col-sm-2"></div>
            <div className="col-sm-8">
              <h2>Account</h2>
              <hr></hr>
              <div className="row">
                <div className="col-sm-4">
                  <h5>MY PROFILE</h5>
                </div>
                <div className="col-sm-8">
                  <p id="name">Name:</p>
                  <p id="email">Email:</p>
                  <hr></hr>
                  <img
                    src={require("./../images/profile.png")}
                    height="75"
                    width="75"
                    class="rounded-circle"
                    id="profileImage"
                  ></img>
                </div>
              </div>
              <hr></hr>
              <div className="row">
                <div className="col-sm-4">
                  <h5>PREFERENCES</h5>
                </div>
                <div className="col-sm-8">
                  <p>
                    <b>Order of Categories</b>
                  </p>
                  <ul class="list-group" id="categories">
                    
                  </ul>
                  <hr></hr>
                  <p id="num">
                    <b>Number of Searches Displayed: 3</b>
                  </p>
                </div>
              </div>
              <hr></hr>
              <br></br>
              <br></br>
              <div className="row">
                <div className="col-sm-6" align="right">
                  <a href="\edit">
                    <button type="button" class="btn btn-primary">
                      Edit
                    </button>
                  </a>
                </div>
                <div className="col-sm-6" align="left">
                  <button
                    type="button"
                    class="btn btn-danger"
                    onClick = {this.togglePopup.bind(this)}
                  >
                    Delete
                  </button>

                  {this.state.showPopup ?  
                    <Popup  
                          confirm='true'
                          header="Delete Account"
                          text='Do you wish to permanently delete your account?' 
                          action ={this.deleteAccount.bind(this)}
                          closePopup={this.togglePopup.bind(this)}  
                    />   
                    : null  
                  }
                </div>
              </div>
              <br></br>
              <br></br>
            </div>
            <div className="col-sm-2"></div>
          </div>
        </body>

        <footer>
          <div class="profilefooter text-center">
            <p>
              Mine App, 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja
              Tewari
            </p>
          </div>
        </footer>
      </div>
    );
  }

  componentDidMount() {
    this.loadUser()
  }
}
