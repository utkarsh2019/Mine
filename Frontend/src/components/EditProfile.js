import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/Edit.css";
import axios from "axios";
import { API_BASE_URL, CATEGORY_TYPES } from "../constants/Constants";
import { getJwtToken, deleteCookies, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { getCurrentUser, getCurrentUserField, getByValue} from "../utils/UserStorageUtil";

export default class EditProfile extends Component {
  constructor(props) {
    super(props);
    
    this.setUserFields = this.setUserFields.bind(this);
    this.select = this.select.bind(this);
    this.deselect = this.deselect.bind(this);
    this.additemsToList = this.additemsToList.bind(this);
  }

  additemsToList(li, ulID){
    let listItem = document.createElement("li");
    listItem.innerHTML = li;
    listItem.className = "list-group-item";
    listItem.id = li;
    listItem.addEventListener('click', this.select.bind());
    listItem.addEventListener('dblclick', this.deselect.bind());
    document.getElementById(ulID).appendChild(listItem);
}


  checkEnterLogin = (evt) => {
    if(evt.keyCode === 13) {
      evt.preventDefault();
      this.login();
    }
  };

  select(evt) {
    let i =  evt.target.id;
    document.getElementById(i).style.backgroundColor = '#5DBCD2';
  }

  deselect(evt) {
    let i = evt.target.id;
    document.getElementById(i).style.backgroundColor ='white';
  }

  moveLeft() {
    let items = document.getElementById("Needed").getElementsByTagName("li");
    if(items.length == 0){
      alert('Needed Entertainment List is Empty!');
    }

    var compareHex = (hex) => {
      var hexString = document.createElement('div')
      hexString.style.backgroundColor = `${hex}`
      return hexString.style.backgroundColor
    }

    let move= [];  

    let i;
    for(i=0; i<items.length; i++){
      if(items[i].style.backgroundColor === compareHex("#5DBCD2")){
        move.push(items[i].id);
        document.getElementById("Needed").removeChild(items[i]);
      }
    }

    let j; 
    for(j=0; j<move.length; j++){
      this.additemsToList(move[j], "Available");
    }
  }

  moveRight() {
    let items = document.getElementById("Available").getElementsByTagName("li");
    if(items.length == 0){
      alert('Available Entertainment List is Empty!');
    }

    var compareHex = (hex) => {
      var hexString = document.createElement('div')
      hexString.style.backgroundColor = `${hex}`
      return hexString.style.backgroundColor
    }

    let move= [];  

    let i;
    for(i=0; i<items.length; i++){
      if(items[i].style.backgroundColor === compareHex("#5DBCD2")){
        move.push(items[i].id);
        document.getElementById("Available").removeChild(items[i]);
      }
    }

    let j; 
    for(j=0; j<move.length; j++){
      this.additemsToList(move[j], "Needed");
    }
  }

  setUserFields = (user) => {
    document.getElementById("name").value = user.name;
    document.getElementById("email").value = user.email;

    if (user.noOfSearches == 1) {
      document.getElementById("inlineRadio1").checked = true;
    } else if (user.noOfSearches == 3) {
      document.getElementById("inlineRadio3").checked = true;
    } else if (user.noOfSearches == 5) {
      document.getElementById("inlineRadio3").checked = true;
    } else if (user.noOfSearches == 7) {
      document.getElementById("inlineRadio4").checked = true;
    }
       
    if(user.profilePicUrl != null){
      document.getElementById("profileImage").src = user.profilePicUrl;
    }

    let pref = user.categoryPreferences.split(",");
    let len = pref.length;
    let i;
    
    for(i=0; i< len; i++){
      this.additemsToList(pref[i], "Needed");
    }

    CATEGORY_TYPES.forEach((value, key) => {
      if(!pref.includes(value)){
        this.additemsToList(value, "Available");
      }
    });
    if(user.provider === 'google' || user.provider === 'facebook'){
      document.getElementById("name").disabled = true;
      document.getElementById("email").disabled = true;
      document.getElementById("changePasswordRedirect").href = "";
      document.getElementById("changeImageRedirect").href = "";
    }

  };

  checkRedirect = () => {

    let provider = getCurrentUserField("provider");

    if(document.getElementById("changePasswordRedirect").href === "" && document.getElementById("changeImageRedirect").href === ""){
      alert("You are logged in through your "+provider+ "account");
    }
  }

  cancelEdit = () => {
    window.location.replace("/profile");
  }
  
  updateInfo = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];


    let items = document.getElementById("Needed").getElementsByTagName("li");
    let i;

    if(items.length === 0){
        alert('Please select at least one Category!');
        return;
    }

    let pref = "";
    for(i=0; i < items.length; i++){
      pref += getByValue(CATEGORY_TYPES, items[i].id) + ",";
    }

    let num;
    if (document.getElementById("inlineRadio1").checked == true) {
      num = 1;
    } else if (document.getElementById("inlineRadio2").checked == true) {
      num = 3;
    } else if (document.getElementById("inlineRadio3").checked == true) {
      num = 5;
    } else if (document.getElementById("inlineRadio4").checked == true) {
      num = 7;
    }

      axios({
        method: "put",
        url: API_BASE_URL + "/user/me",
        headers: {
          Authorization: type + " " + token
        },
        data: {
          email: document.getElementById("email").value,
          name: document.getElementById("name").value,
          profilePicUrl: null,
          categoryPreferences: pref,
          noOfSearches: num
        }
      })
        .then(function(response) {
          window.location.replace("/profile");
        })
        .catch(function(error) {
          alert(error);
        });
  };

  render() {
    if (! checkUserLoggedIn()) {
      return redirectToHome(this.props.location);
    }
    
    return (
      <div className="Edit">
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
              <h2>Edit Profile</h2>
              <hr></hr>
              <div className="row">
                <div className="col-sm-4">
                  <h5>PROFILE</h5>
                </div>
                <div className="col-sm-8">
                  <div class="form-group">
                    <label for="nameinput">Name</label>
                    <input type="text" class="form-control" id="name" onKeyUp={this.checkEnterUpdate}></input>
                  </div>
                  <div class="form-group">
                    <label for="emailinput">Email address</label>
                    <input type="email" class="form-control" id="email" onKeyUp={this.checkEnterUpdate}></input>
                  </div>
                  <div class="form-group">
                    <label for="passwordinput" align="right">
                      Password
                    </label>
                    <a class="align-right" href="/editpassword" id="changePasswordRedirect" onClick={this.checkRedirect}>
                      Change Password
                    </a>
                  </div>
                  <hr></hr>
                  <div class="form-group">
                    <img
                      src={require("./../images/profile.png")}
                      height="75"
                      width="75"
                      class="rounded-circle"
                      id = "profileImage"
                    ></img>
                    <a class="align-right" href="/editimage" id="changeImageRedirect" onClick={this.checkRedirect}>
                      Edit
                    </a>
                  </div>
                </div>
              </div>
              <hr></hr>
              <div className="row">
                <div className="col-sm-4">
                  <h5>PREFERENCES</h5>
                </div>
                <div className="col-sm-8">
                  <p>
                    <b>Categories:</b> 
                  </p>
                  <p>Please arrange in the order of preference needed.</p>

                  <div class="row">

                    <div class="dual-list list-left col-md-5">
                        <div class="well text-right">
                            <p>Available Entertainment</p>
                            <ul class="list-group" id="Available">
                            </ul>
                        </div>
                    </div>

                    <div class="list-arrows col-md-1 text-center">
                        <button class="btn btn-default btn-sm move-right" onClick={()=>{this.moveRight()}}><span>&#62;</span></button>
                        <button class="btn btn-default btn-sm move-left" onClick={()=>{this.moveLeft()}}><span>&#60;</span></button>
                    </div>

                    <div class="dual-list list-right col-md-5">
                        <div class="well">
                        <p>Needed Entertainment</p>
                            <ul class="list-group" id="Needed">
                            </ul>
                        </div>
                    </div>

                    </div>


                  <hr></hr>
                  <div class="row">
                    <p>
                      <b>Number of Searches Displayed</b>
                    </p>
                    <br></br>
                    <br></br>
                    <br></br>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="inlineRadioOptions"
                        id="inlineRadio1"
                        value="option1"
                      ></input>
                      <label class="form-check-label" for="inlineRadio1">
                        1
                      </label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="inlineRadioOptions"
                        id="inlineRadio2"
                        value="option2"
                      ></input>
                      <label class="form-check-label" for="inlineRadio2">
                        3
                      </label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="inlineRadioOptions"
                        id="inlineRadio3"
                        value="option3"
                      ></input>
                      <label class="form-check-label" for="inlineRadio3">
                        5
                      </label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input
                        class="form-check-input"
                        type="radio"
                        name="inlineRadioOptions"
                        id="inlineRadio4"
                        value="option3"
                      ></input>
                      <label class="form-check-label" for="inlineRadio3">
                        7
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <hr></hr>
              <br></br>
              <br></br>

              <div className="row" id="buttoncontain">
                <button
                  type="button"
                  class="btn btn-primary"
                  onClick={this.updateInfo}
                >
                  Update
                </button>
                <button
                id="editcancelbutton"
                  type="button"
                  class="btn btn-danger"
                  onClick={this.cancelEdit}
                >
                  Cancel
                </button>
              </div>

              <br></br>
              <br></br>
            </div>
            <div className="col-sm-2"></div>
          </div>
        </body>

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


  componentDidMount() {
    this.setUserFields(getCurrentUser());
  }
}
