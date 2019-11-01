import React, { Component } from "react";
import "../css/forgotpassword.css";
import "../css/bootstrap.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";
import { getUrlParameter } from "../utils/UrlUtil"

export default class VerifyPassword extends Component {
  constructor(props) {
    super(props);

    this.update = this.update.bind(this);
  }

  checkEnterUpdate = (evt) => {
    if(evt.keyCode === 13) {
      evt.preventDefault();
      this.update();
    }
  };

  update() {
    let token = getUrlParameter("token", this.props.location.search);
    let pass = document.getElementById("passwordinput").value;
    axios({
      method: "post",
      url: API_BASE_URL + "/verify/password?token=" + token,
      data: {
        password: pass
      }
    })
      .then(function(response) {
        window.location.replace("/login");
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
                src={require("../images/minelogo.png")}
              ></img>
            </div>

            <div class="col">
              <div class="forgotpassword">
                <form>
                  <h2>Forgot Password</h2>
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
