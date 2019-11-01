import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/Edit.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";
import { getJwtToken, deleteCookies, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { getCurrentUser, setCurrentUser } from "../utils/UserStorageUtil";

export default class EditProfile extends Component {
  constructor(props) {
    super(props);
    
    this.setUserFields = this.setUserFields.bind(this);
  }

  checkEnterLogin = (evt) => {
    if(evt.keyCode === 13) {
      evt.preventDefault();
      this.login();
    }
  };

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
    document.getElementById("preferenceInput1").value = pref[0];
    document.getElementById("preferenceInput2").value = pref[1];
    document.getElementById("preferenceInput3").value = pref[2];
    document.getElementById("preferenceInput4").value = pref[3];
    document.getElementById("preferenceInput5").value = pref[4];
  };

  cancelEdit = () => {
    window.location.replace("/profile");
  }
  updateInfo = () => {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    let categoryPref =
      document.getElementById("preferenceInput1").value +
      "," +
      document.getElementById("preferenceInput2").value +
      "," +
      document.getElementById("preferenceInput3").value +
      "," +
      document.getElementById("preferenceInput4").value +
      "," +
      document.getElementById("preferenceInput5").value;

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

    if (this.updateInfoRender()) {
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
          categoryPreferences: categoryPref,
          noOfSearches: num
        }
      })
        .then(function(response) {
          window.location.replace("/profile");
        })
        .catch(function(error) {
          alert(error);
        });
    }
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
                    <a class="align-right" href="/editpassword">
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
                    <a class="align-right" href="/editimage">
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
                    <b>Option of the following categories:</b> Movies, Music,
                    Social, Text, Audio
                  </p>
                  <p>Please input each category as a preference only once.</p>
                  <form>
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control"
                        id="preferenceInput1"
                        placeholder="First Preference"
                      ></input>
                    </div>
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control"
                        id="preferenceInput2"
                        placeholder="Second Preference"
                      ></input>
                    </div>
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control"
                        id="preferenceInput3"
                        placeholder="Third Preference"
                      ></input>
                    </div>
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control"
                        id="preferenceInput4"
                        placeholder="Fourth Preference"
                      ></input>
                    </div>
                    <div class="form-group">
                      <input
                        type="text"
                        class="form-control"
                        id="preferenceInput5"
                        placeholder="Fifth Preference"
                      ></input>
                    </div>
                  </form>

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
          <div class="footer text-center">
            <p>
              Mine App, 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja
              Tewari
            </p>
          </div>
        </footer>
      </div>
    );
  }

  updateInfoRender = () => {
    const prefcategories = ["movies", "music", "social", "text", "audio"];

    let firstPref = document.getElementById("preferenceInput1").value;
    firstPref = firstPref.toLowerCase();
    let secondPref = document.getElementById("preferenceInput2").value;
    secondPref = secondPref.toLowerCase();
    let thirdPref = document.getElementById("preferenceInput3").value;
    thirdPref = thirdPref.toLowerCase();
    let fourthPref = document.getElementById("preferenceInput4").value;
    fourthPref = fourthPref.toLowerCase();
    let fifthPref = document.getElementById("preferenceInput5").value;
    fifthPref = fifthPref.toLowerCase();

    let prefinputs = [firstPref, secondPref, thirdPref, fourthPref, fifthPref];
    let prefset = new Set(prefinputs);
    let flag = 0;
    prefinputs.forEach(input => {
      if (!prefcategories.includes(input)) {
        flag = 1;
      }
    });
    if (flag == 1) {
      alert("Not a valid category, please input valid category inputs");
    }
    if (prefset.size != prefinputs.length) {
      flag = 2;
    }
    if (flag == 2) {
      alert("Error! Only one category per preference please!");
    }
    return flag == 0;
  };

  componentDidMount() {
    this.setUserFields(getCurrentUser());
  }
}
