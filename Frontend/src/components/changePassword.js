import React, { Component } from "react";
import "../css/forgotpassword.css";
import "../css/bootstrap.css";
import axios from "axios";

export default class EditPassword extends Component {
  update() {
    let cookie = document.cookie.split(";");
    let cookie1 = cookie[0].split("=");
    let cookie2 = cookie[1].split("=");
    let type, token;
    if (cookie1[0] === "tokenType") {
      type = cookie1[1];
      token = cookie2[1];
    } else {
      type = cookie2[1];
      token = cookie1[1];
    }

    let pass = document.getElementById("passwordinput").value;

    axios({
      method: "put",
      url: "http://api.mineapp.tech/user/me/password",
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
                  <h2>Update Password</h2>
                  <hr></hr>

                  <div class="form-group">
                    <label for="passwordinput">New Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="passwordinput"
                      placeholder="Enter Password"
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
                    ></input>
                  </div>

                  <button
                    type="button"
                    class="btn btn-primary"
                    onClick={this.update}
                  >
                    Update
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
