import React, { Component } from "react";
import "../css/verifyaccount.css";
import "../css/bootstrap.css";
import axios from "axios";
import { API_BASE_URL } from "../constants";

export default class VerifyAccount extends Component {
  
  constructor(props) {
    super(props);

    this.verify = this.verify.bind(this);
  }

  getUrlParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");

    var results = regex.exec(this.props.location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
  };

  verify(){
    let token = this.getUrlParameter("token");
    axios({
        method: "get",
        url: API_BASE_URL + "/verify/confirm?token="+token
    })
    .then(function (response) {
      window.location.replace("/login");
    })
    .catch(function (error) {
      alert(error);
    });
  };

  render () {
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
              <div class="verifyaccount">
                <form>
                  <h2>Verify Account</h2>
                  <hr></hr>

                  <button
                    type="button"
                    class="btn btn-primary"
                    onClick={this.verify}
                  >
                    Verify
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
    document.body.className = "bodyVerifyAccount";
  }
}
