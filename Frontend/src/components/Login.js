import React, { Component } from "react";
import "../css/login.css";
import "../css/bootstrap.css";
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, API_BASE_URL } from "../constants/Constants";
import axios from "axios";
import { getJwtToken, setCookies } from "../utils/CookieUtil";
import { setCurrentUser } from "../utils/UserStorageUtil";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.setUser = this.setUser.bind(this);
    
  }

  checkEnterLogin = (evt) => {
    if (evt.keyCode === 13) {
      evt.preventDefault();
      this.login();
    }
  };

  setUser = () => {
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
      .then(function (response) {
        setCurrentUser(
          response.data.responseObject.name,
          response.data.responseObject.email,
          response.data.responseObject.profilePicUrl,
          response.data.responseObject.provider,
          response.data.responseObject.noOfSearches,
          response.data.responseObject.categoryPreferences
        );
      })
      .catch(function (error) {
        alert(error);
      });
  };

  onInputChange = (val) =>{
    if(!(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(val))){
      let al = document.createElement("p");
      al.style.color = "red";
      al.innerHTML = "Please Enter a Email address";
      al.id = "IncorrectAddress";
      document.getElementById("emailForm").appendChild(al);
    }
  }

  login = () => {
    let userdata = {};

    //Checks if there was any error previously 
    if (document.getElementById("emailinput").classList.contains("error")) {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className.replace(" error", "");
      document.getElementById("emailForm").removeChild(document.getElementById("emailError"));
    }
    if (document.getElementById("passwordinput").classList.contains("error")) {
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className.replace(" error", "");
      document.getElementById("passwordForm").removeChild(document.getElementById("passwordError"));
    }

    let element = !!document.getElementById("errorDetected");
    let elementEmail = !!document.getElementById("IncorrectAddress");

    if(element){
      document.getElementById("loginMain").removeChild(document.getElementById("errorDetected"));
    }

    if(elementEmail){
      document.getElementById("emailForm").removeChild(document.getElementById("IncorrectAddress"));
    }

    // Takes the input if exists else promts an error 
    if (document.getElementById("emailinput").value != "") {
      let email =  document.getElementById("emailinput").value;
      this.onInputChange(email);
      userdata.email = email;
    }
    else {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Email";
      al.id = "emailError";
      document.getElementById("emailForm").appendChild(al);
    }


    if (document.getElementById("passwordinput").value != "") {
      userdata.password = document.getElementById("passwordinput").value;
    }
    else {
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Password";
      al.id = "passwordError";
      document.getElementById("passwordForm").appendChild(al);
    }

    let email = !!document.getElementById("IncorrectAddress");

    if (document.getElementById("passwordinput").value != "" && document.getElementById("emailinput").value != "" && !email) {
      axios({
        method: "post",
        url: API_BASE_URL + "/auth/login",
        data: {
          email: userdata.email,
          password: userdata.password
        }
      })
        .then(response => {
          setCookies(response.data.responseObject.accessToken, response.data.responseObject.tokenType);
          this.setUser();
          window.location.replace("/dashboard");
        })
        .catch(error => {
          let al = document.createElement("p");
          al.style.color = "red";
          al.innerHTML = error.response.data.errorMessage;
          al.id = "errorDetected";
          document.getElementById("loginMain").appendChild(al);
        });
    }
  };

  render() {  
    return (
      <div>
        <div class="bodyLogin container-fluid" id="main>
          <div class="row regcontain" id="regid">
            <div class="col">
              <img
                class="center-block"
                src={require("../images/minelogo.png")}
              ></img>
            </div>

            <div class="col" >
              <div class="signup">
                <form>
                  <h2>Login</h2>
                  <hr></hr>

                  <div id="loginMain"></div>

                  <div class="form-group" id="emailForm" >
                    <label for="usernameinput">Email</label>
                    <input
                      type="email"
                      class="form-control"
                      id="emailinput"
                      placeholder="name@example.com"
                      onKeyUp={this.checkEnterLogin}
                    ></input>
                  </div>

                  <div class="form-group" id="passwordForm">
                    <label for="passwordinput">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
                      onKeyUp={this.checkEnterLogin}
                    ></input>
                  </div>
                  <a href={"/forgotpassword"}>Forgot password?</a>
                  <br></br>
                  <br></br>
                  
                  <div class="text-center">
                    <button
                      type="button"
                      class="btn btn-primary"
                      onClick={this.login}
                    >
                      Login
                    </button>
                  </div>
                </form>

                <hr></hr>
                <h5 class="text-center">OR</h5>
                <div className="social-login">
                  <a
                    className="btn btn-block social-btn google"
                    href={GOOGLE_AUTH_URL}
                  >
                    <img
                      src={require("../images/google-logo.png")}
                      width="25px"
                      height="25px"
                      alt="Google"
                    />{" "}
                    Log in with Google
                  </a>
                  <a
                    className="btn btn-block social-btn facebook"
                    href={FACEBOOK_AUTH_URL}
                  >
                    <img
                      src={require("../images/fb-logo.png")}
                      width="25px"
                      height="25px"
                      alt="Facebook"
                    />{" "}
                    Log in with Facebook
                  </a>
                </div>
                <br></br>
                <h6 class="text-center">
                  Don't have an account? <a href={"/signup"}>Signup here</a>
                </h6>
              </div>
            </div>
          </div>
        </div>
        <footer>
          <div class="loginfooter text-center">
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
