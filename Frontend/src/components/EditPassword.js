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


    let element = !!document.getElementById("errorDetected");
    let errorElement = !!document.getElementById("errorFound");
    let error = !!document.getElementById("error");

    if (error) {
      document.getElementById("signupMain").removeChild(document.getElementById("error"));
    }
    if (element) {
      document.getElementById("confirmPasswordForm").removeChild(document.getElementById("errorDetected"));
    }
    if (errorElement) {
      document.getElementById("passwordForm").removeChild(document.getElementById("errorFound"));
    }
    if (document.getElementById("passwordinput").classList.contains("error")) {
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className.replace(" error", "");
      document.getElementById("passwordForm").removeChild(document.getElementById("passwordError"));
    }
    if (document.getElementById("confirmpasswordinput").classList.contains("error")) {
      document.getElementById("confirmpasswordinput").className = document.getElementById("confirmpasswordinput").className.replace(" error", "");
      document.getElementById("confirmPasswordForm").removeChild(document.getElementById("confirmPasswordError"));
    }

    let password = document.getElementById("passwordinput").value;
    let confirmpassword = document.getElementById("confirmpasswordinput").value;

    if(password === ""){
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Password";
      al.id = "passwordError";
      document.getElementById("passwordForm").appendChild(al);
    }
    if(confirmpassword === ""){
      document.getElementById("confirmpasswordinput").className = document.getElementById("confirmpasswordinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Confirm your Password";
      al.id = "confirmPasswordError";
      document.getElementById("confirmPasswordForm").appendChild(al);
    }

    if (password != "" && !(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/.test(password))) {
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Your Password should contain:<br>-At least 8 Characters<br>-At least 1 lowercase Character<br>-At least 1 uppercase character<br>-At least 1 numeric character<br>-At least 1 special character";
      al.id = "errorFound";
      document.getElementById("passwordForm").appendChild(al);
    }


    if (password != "" && confirmpassword != "" && password !== confirmpassword) {
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Password and Confirm Password don't Match";
      al.id = "errorDetected";
      document.getElementById("confirmPasswordForm").appendChild(al);
    }

    let errorMatch = !!document.getElementById("errorDetected");
    let errorPassword = !!document.getElementById("errorFound");

    if(!errorMatch && !errorPassword && password !== "" && confirmpassword !== ""){

      axios({
        method: "put",
        url: API_BASE_URL + "/user/me/password",
        headers: {
          Authorization: type + " " + token
        },
        data: {
          password: password
        }
      })
        .then(function(response) {
          console.log(response);
          window.location.replace("/edit");
        })
        .catch(function(error) {
          let al = document.createElement("p");
          al.style.color = "Red";
          al.innerHTML = error.response.data.errorMessage;
          al.id = "error";
          document.getElementById("main").appendChild(al);
        });

    }
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
      <div>
        <div class="container-fluid">
          <div class="row regcontain" id="regid">
            

            <div class="col">
              <div class="forgotpassword">
                <form>
                  <h2>Update Password</h2>
                  <hr></hr>

                  <div id="main"></div>

                  <div class="form-group" id="passwordForm">
                    <label for="passwordinput">New Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
                      onKeyUp={this.checkEnterUpdate}
                    ></input>
                  </div>
                  <div class="form-group" id="confirmPasswordForm">
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
    document.body.className = "";
  }
}
