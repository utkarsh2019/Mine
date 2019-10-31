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
    if(evt.keyCode === 13) {
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
      .then(function(response) {
        setCurrentUser(
          response.data.responseObject.name,
          response.data.responseObject.email,
          response.data.responseObject.profilePicUrl,
          response.data.responseObject.provider,
          response.data.responseObject.noOfSearches,
          response.data.responseObject.categoryPreferences
        );
      })
      .catch(function(error) {
        alert(error);
      });
  };

  login = () => {
    let userdata = {};
    if(document.getElementById("emailinput").value != ""){
     userdata.email = document.getElementById("emailinput").value;
    }
    else{
      alert("Please Enter your EmailId");
    }
    if(document.getElementById("passwordinput").value != ""){
      userdata.password = document.getElementById("passwordinput").value;
    }
    else{
      alert("Please Enter the your Password");
    }
  
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
        if(document.getElementById("passwordinput").value != "" && document.getElementById("emailinput").value != ""){
          alert("Please Check your EmailId or Password");
        }
      });
  };

  render() {
    return (
      <div>
        <div class="container-fluid">
          <div class="row regcontain" id="regid">
            <div class="col">
              <img
                class="center-block"
                src={require("../images/minelogo.png")}
              ></img>
            </div>

            <div class="col">
              <div class="signup">
                <form>
                  <h2>Login</h2>
                  <hr></hr>

                  <div class="form-group">
                    <label for="usernameinput">Email</label>
                    <input
                      type="email"
                      class="form-control"
                      id="emailinput"
                      placeholder="name@example.com"
                      onKeyUp={this.checkEnterLogin}
                    ></input>
                  </div>

                  <div class="form-group">
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

  componentDidMount() {
    document.body.className = "bodyLogin";
  }
}
