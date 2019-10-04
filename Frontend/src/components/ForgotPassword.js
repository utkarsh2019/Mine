import React, { Component } from "react";
import logo from "../img/minelogo.png";
import "../css/forgotpassword.css";
import "../css/bootstrap.css";
import axios from "axios";

export default class ForgotPassword extends Component {
  forgot() {
    let userdata = {};
    userdata.email = document.getElementById("emailinput").value;

    axios({
      method: "post",
      url: "http://localhost:8080/forgotPassword",
      data: {
        email: userdata.email
      }
    })
      .then(function(response) {
        console.log(response);
      })
      .catch(function(error) {
        alert(error);
      });
  }

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
              <div class="forgotpassword">
                <form>
                  <h2>Forgot Password</h2>
                  <hr></hr>

                  <div class="form-group">
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
    document.body.className = "bodyForgotPassword";
  }
}
