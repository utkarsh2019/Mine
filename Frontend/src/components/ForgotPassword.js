import React, { Component } from "react";
import logo from "../images/minelogo.png";
import "../css/forgotpassword.css";
import "../css/bootstrap.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";

export default class ForgotPassword extends Component {

  constructor(props) {
    super(props);
  }


  forgot = () => {

    if (document.getElementById("emailinput").classList.contains("error")) {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className.replace(" error", "");
      document.getElementById("emailForm").removeChild(document.getElementById("emailError"));
    }
    let elementEmail = !!document.getElementById("IncorrectAddress");
    let error = !!document.getElementById("error");
    if (error) {
      document.getElementById("main").removeChild(document.getElementById("error"));
    }

    if (elementEmail) {
      document.getElementById("emailForm").removeChild(document.getElementById("IncorrectAddress"));
    }

    let userdata = {};
    userdata.email = document.getElementById("emailinput").value;

    if (userdata.email == "") {
      document.getElementById("emailinput").className = document.getElementById("emailinput").className + " error";
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Enter your Email";
      al.id = "emailError";
      document.getElementById("emailForm").appendChild(al);
    }
    
    this.onInputChange(userdata.email);


    let emailError = !!document.getElementById("IncorrectAddress");

    if (userdata.email != "" && !emailError) {
      axios({
        method: "post",
        url: API_BASE_URL + "/forgotPassword",
        data: {
          email: userdata.email
        }
      })
        .then(function (response) {
          window.location.replace("/");
        })
        .catch(function (error) {
          let al = document.createElement("p");
          al.style.color = "Red";
          al.innerHTML = error.response.data.errorMessage;
          al.id = "error";
          document.getElementById("main").appendChild(al);
        });

    }
  }

  onInputChange = (val) => {
    if (!(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/.test(val))) {
      alert("here");
      let al = document.createElement("p");
      al.style.color = "red";
      al.innerHTML = "Please Enter a valid email address";
      al.id = "IncorrectAddress";
      document.getElementById("emailForm").appendChild(al);
    }
  }


  render() {
    return (
      <div>
        <div class="bodyForgotPassword container-fluid">
          <div class="row regcontain" id="regid">
            <div class="col">
              <img
                class="center-block"
                src={require("../images/minelogo.png")}
              ></img>
            </div>

            <div class="col">
              <div class="forgotpassword">
                <form>
                  <h2>Forgot Password</h2>
                  <hr></hr>

                  <div id="main"></div>

                  <div class="form-group" id="emailForm">
                    <label for="emailinput">Email address</label>
                    <input
                      type="email"
                      class="form-control"
                      id="emailinput"
                      placeholder="name@example.com"
                    ></input>
                  </div>

                  <button
                    type="button"
                    class="btn btn-primary"
                    onClick={this.forgot}
                  >
                    Submit
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
        <footer>
          <div class="forgotpassfooter text-center">
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
