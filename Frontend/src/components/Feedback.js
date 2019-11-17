import React, { Component } from "react";
import "../css/bootstrap.css";
import "../css/Feedback.css"

export default class Feedback extends Component {
  render() {

    return (
      <div>
        <div>
          <nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid fixed-top">
            <a class="navbar-brand" href="#">
              <img
                src={require("./../images/minelogo.png")}
                width="50"
                height="50"
                class="d-inline-block"
                alt=""
              ></img>
              Mine
            </a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a href={"/"} class="nav-item nav-link">
                  Home
                </a>
              </div>
            </div>
            <ul class="nav justify-content-end">
                <li class="nav-item">
                  <a href={"/login"}>
                    <button type="button" class="btn btn-info navsignlog">
                      Login
                    </button>
                  </a>
                </li>
                <li class="nav-item">
                  <a href={"/signup"}>
                    <button type="button" class="btn btn-info navsignlog">
                      Signup
                    </button>
                  </a>
                </li>
              </ul>
          </nav>
        </div>

        <body>
          <div class="container-fluid feedbackBody">
            <div class="text-center">
                <br></br>
                <br></br>
                <br></br>
                <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSe-yga4DzqFhhZVMBBDadstwMHuA2EPelX6js2ipC2bKharDQ/viewform?embedded=true" width="640" height="1130" scrolling="no" frameborder="0" marginheight="0" marginwidth="0">Loading…</iframe>
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
        </body>
      </div>
    );
  }
}
