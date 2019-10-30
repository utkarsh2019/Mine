import React, { Component } from "react";
import "../css/login.css";
import "../css/bootstrap.css";
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, API_BASE_URL } from "./../constants";
import axios from "axios";

export default class Login extends Component {
  constructor(props) {
    super(props);
  }

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
      .then(function(response) {
        document.cookie =
          "accessToken=" + response.data.responseObject.accessToken + ";path=/";
        document.cookie =
          "tokenType=" + response.data.responseObject.tokenType + ";path=/";
        window.location.replace("/dashboard");
      })
      .catch(function(error) {
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
                src={require("../img/minelogo.png")}
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
                    ></input>
                  </div>

                  <div class="form-group">
                    <label for="passwordinput">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
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
                      src={require("../img/google-logo.png")}
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
                      src={require("../img/fb-logo.png")}
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
