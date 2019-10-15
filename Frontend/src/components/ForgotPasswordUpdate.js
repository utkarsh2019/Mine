import React, { Component } from "react";
import "../css/forgotpassword.css";
import "../css/bootstrap.css";
import axios from "axios";

export default class ForgotPasswordUpdate extends Component {
  constructor(props) {
    super(props);

    this.update = this.update.bind(this);
  }
  getUrlParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");

    var results = regex.exec(this.props.location.search);
    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  }

  update() {
    let token = this.getUrlParameter("token");
    let pass = document.getElementById("passwordinput").value;
    axios({
      method: "post",
      url: "http://157.245.252.174:8080/verify/password?token=" + token,
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
                src={require("../img/minelogo.png")}
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
