import React, { Component } from "react";
import "../css/verifyaccount.css";
import "../css/bootstrap.css";
import axios from "axios";
import { API_BASE_URL } from "../constants/Constants";
import { getUrlParameter } from "../utils/UrlUtil"

export default class VerifyAccount extends Component {
  
  constructor(props) {
    super(props);

    this.verify = this.verify.bind(this);
  }

  verify(){
    let token = getUrlParameter("token", this.props.location.search);
    axios({
        method: "get",
        url: API_BASE_URL + "/verify/account?token=" + token
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
