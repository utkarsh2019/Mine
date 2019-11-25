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
    if (evt.keyCode === 13) {
      evt.preventDefault();
      this.register();
    }
  };

  onInputChange = (val) => {
    if (!(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(val))) {
      let al = document.createElement("p");
      al.style.color = "red";
      al.innerHTML = "Please Enter a valid email address";
      al.id = "IncorrectAddress";
      document.getElementById("emailForm").appendChild(al);
    }
  }

  register = () => {
    let userdata = {};

    if (document.getElementById("emailinput").classList.contains("error")) {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className.replace(" error", "");
      document.getElementById("emailForm").removeChild(document.getElementById("emailError"));
    }
    if (document.getElementById("nameinput").classList.contains("error")) {
      document.getElementById("nameinput").className = document.getElementById("nameinput").className.replace(" error", "");
      document.getElementById("nameForm").removeChild(document.getElementById("nameError"));
    }
    if (document.getElementById("passwordinput").classList.contains("error")) {
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className.replace(" error", "");
      document.getElementById("passwordForm").removeChild(document.getElementById("passwordError"));
    }
    if (document.getElementById("confirmpasswordinput").classList.contains("error")) {
      document.getElementById("confirmpasswordinput").className = document.getElementById("confirmpasswordinput").className.replace(" error", "");
      document.getElementById("confirmPasswordForm").removeChild(document.getElementById("confirmPasswordError"));
    }

    let element = !!document.getElementById("errorDetected");
    let elementEmail = !!document.getElementById("IncorrectAddress");
    let errorElement = !!document.getElementById("errorFound");
    let error = !!document.getElementById("error");

    if (element) {
      document.getElementById("confirmPasswordForm").removeChild(document.getElementById("errorDetected"));
    }
    if (errorElement) {
      document.getElementById("passwordForm").removeChild(document.getElementById("errorFound"));
    }
    if (elementEmail) {
      document.getElementById("emailForm").removeChild(document.getElementById("IncorrectAddress"));
    }
    if (error) {
      document.getElementById("signupMain").removeChild(document.getElementById("error"));

    }

    userdata.email = document.getElementById("emailinput").value;
    userdata.name = document.getElementById("nameinput").value;
    userdata.password = document.getElementById("passwordinput").value;
    userdata.confirmpassword = document.getElementById("confirmpasswordinput").value;

    if (userdata.email == "") {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Email";
      al.id = "emailError";
      document.getElementById("emailForm").appendChild(al);
    }
    else {
      let email = document.getElementById("emailinput").value;
      this.onInputChange(email);
    }
    if (userdata.name == "") {
      document.getElementById("nameinput").className = document.getElementById("nameinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Name";
      al.id = "nameError";
      document.getElementById("nameForm").appendChild(al);
    }
    if (userdata.password == "") {
      document.getElementById("passwordinput").className = document.getElementById("passwordinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Password";
      al.id = "passwordError";
      document.getElementById("passwordForm").appendChild(al);
    }
    if (userdata.confirmpassword == "") {
      document.getElementById("confirmpasswordinput").className = document.getElementById("confirmpasswordinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Confirm your Password";
      al.id = "confirmPasswordError";
      document.getElementById("confirmPasswordForm").appendChild(al);
    }

    if (userdata.password != "" && !(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/.test(userdata.password))) {
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Your Password should contain:<br>-At least 8 Characters<br>-At least 1 lowercase Character<br>-At least 1 uppercase character<br>-At least 1 numeric character<br>-At least 1 special character";
      al.id = "errorFound";
      document.getElementById("passwordForm").appendChild(al);
    }


    if (userdata.password != "" && userdata.confirmpassword != "" && userdata.password !== userdata.confirmpassword) {
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Password and Confirm Password don't Match";
      al.id = "errorDetected";
      document.getElementById("confirmPasswordForm").appendChild(al);
    }

    let errorMatch = !!document.getElementById("errorDetected");
    let email = !!document.getElementById("IncorrectAddress");
    let errorPassword = !!document.getElementById("errorFound");

    if (userdata.email != "" && userdata.name != "" && userdata.password != "" && userdata.confirmpassword != "" && !email && !errorMatch && !errorPassword) {
      axios({
        method: "post",
        url: API_BASE_URL + "/auth/signup",
        data: {
          email: userdata.email,
          name: userdata.name,
          password: userdata.password
        }
      })
        .then(function (response) {
          window.location.replace("/login");
        })
        .catch(function (error) {
          let al = document.createElement("p");
          al.style.color = "Red";
          al.innerHTML = error.response.data.errorMessage;
          al.id = "error";
          document.getElementById("signupMain").appendChild(al);
        });

    }
  };

  render() {
    return (
      <div>
        <div class="bodySignup container-fluid">
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

                  <div id="signupMain"></div>

                  <div class="form-group" id="nameForm">
                    <label for="nameinput">Name</label>
                    <input
                      type="text"
                      class="form-control"
                      id="nameinput"
                      placeholder="Enter name"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group" id="emailForm">
                    <label for="emailinput">Email address</label>
                    <input
                      type="email"
                      class="form-control"
                      id="emailinput"
                      placeholder="name@example.com"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group" id="passwordForm">
                    <label for="passwordinput">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
                      onKeyUp={this.checkEnterRegister}
                    ></input>
                  </div>
                  <div class="form-group" id="confirmPasswordForm">
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
}
