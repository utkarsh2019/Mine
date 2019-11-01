import React, { Component } from "react";
import "../css/signup.css";
import "../css/bootstrap.css";
import axios from "axios";
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, API_BASE_URL } from "../constants/Constants";

export default class Signup extends Component {
  constructor(props) {
    super(props);
  }

  checkEnterRegister = (evt) => {
    if(evt.keyCode === 13) {
      evt.preventDefault();
      this.register();
    }
  };

  register = () => {
    let userdata = {};
    userdata.email = document.getElementById("emailinput").value;
    userdata.name = document.getElementById("nameinput").value;
    userdata.password = document.getElementById("passwordinput").value;
    userdata.confirmpassword = document.getElementById("confirmpasswordinput").value;

    if(userdata.email == ""){
      alert("Please Enter your Email");
    }
    if(userdata.name == ""){
      alert("Please Enter your Name");
    }
    if(userdata.password == ""){
      alert("Please Enter a Password");
    }
    if(userdata.confirmpassword == ""){
      alert("Please Confirm your Password");
    }
    if(userdata.password !== userdata.confirmpassword){
      alert("Password and Confirm Password don't match");
    }

    axios({
      method: "post",
      url: API_BASE_URL + "/auth/signup",
      data: {
        email: userdata.email,
        name: userdata.name,
        password: userdata.password
      }
    })
      .then(function(response) {
        window.location.replace("/login");
      })
      .catch(function(error) {
        alert(error);
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
                  <h2>Sign up</h2>
                  <hr></hr>
                  <div class="form-group">
                    <label for="nameinput">Name</label>
                    <input
                      type="text"
                      class="form-control"
                      id="nameinput"
                      placeholder="Enter name"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group">
                    <label for="emailinput">Email address</label>
                    <input
                      type="email"
                      class="form-control"
                      id="emailinput"
                      placeholder="name@example.com"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group">
                    <label for="passwordinput">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group">
                    <label for="confirmpasswordinput">Confirm Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="confirmpasswordinput"
                      placeholder="Re-enter Password"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="text-center">
                    <button
                      type="button"
                      class="btn btn-primary"
                      onClick={this.register}
                    >
                      Sign up
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
                    Sign up with Google
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
                    Sign up with Facebook
                  </a>
                </div>
                <br></br>
                <h6 class="text-center">
                  Already have an account? <a href={"/login"}>Login here</a>
                </h6>
              </div>
            </div>
          </div>
        </div>
        <footer>
          <div class="signupfooter text-center">
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
    document.body.className = "bodySignup";
  }
}

//Regex and checking if the passwords remain along with error handling for this part
