import React, { Component } from "react";
import "../css/Edit.css";
import "../css/bootstrap.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";
import { getJwtToken, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";

export default class EditPassword extends Component {

  checkEnterUpdate = (evt) => {
    if(evt.keyCode === 13) {
      evt.preventDefault();
      this.update();
    }
  };
  backtoProfile = () => {
    window.location.replace("/profile");
  }
  update() {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    let pass = document.getElementById("passwordinput").value;

    axios({
      method: "put",
      url: API_BASE_URL + "/user/me/password",
      headers: {
        Authorization: type + " " + token
      },
      data: {
        password: pass
      }
    })
      .then(function(response) {
        console.log(response);
        window.location.replace("/edit");
      })
      .catch(function(error) {
        alert(error);
      });
  }

  render() {
    if (!checkUserLoggedIn()) {
      return redirectToHome(this.props.location);
    }
    
    return (
      
      <div className="">
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">
            <img
              src={require("../images/minelogo.png")}
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
      <div>
        <div class="container-fluid">
          <div class="row regcontain" id="regid">
            

            <div class="col">
              <div class="forgotpassword">
                <form>
                  <h2>Update Password</h2>
                  <hr></hr>

                  <div class="form-group">
                    <label for="passwordinput">New Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
                      onKeyUp={this.checkEnterUpdate}
                    ></input>
                  </div>
                  <div class="form-group">
                    <label for="confirmpasswordinput">
                      Confirm New Password
                    </label>
                    <input
                      type="password"
                      class="form-control"
                      id="confirmpasswordinput"
                      placeholder="Re-enter Password"
                      onKeyUp={this.checkEnterUpdate}
                    ></input>
                  </div>

                  <button
                    type="button"
                    class="btn btn-primary"
                    onClick={this.update}
                  >
                    Update
                  </button>
                  <button
                id="editcancelbutton"
                  type="button"
                  class="btn btn-danger"
                  onClick={this.backtoProfile}
                >
                  Cancel
                </button>
                </form>
              </div>
            </div>
          </div>
        </div>
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
    document.body.className = "bodyForgotPassword";
  }
}
