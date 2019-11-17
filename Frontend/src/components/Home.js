import React, { Component } from "react";
import "../css/home.css";
import "../css/bootstrap.css";
import Loader from "react-loader-spinner";
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css";
import { checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToDashboard } from "../utils/RedirectUtil";

export default class Home extends Component {
  render() {
    if (checkUserLoggedIn()) {
      return redirectToDashboard(this.props.location);
    }

    return (
      <div className="App">
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top container-fluid">
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
            data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class=" navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
              <a class="nav-item nav-link" href="#aboutnav">
                About
              </a>
              <a class="nav-item nav-link" href="#teamnav">
                Team
              </a>
              <a class="nav-item nav-link" href="#contactnav">
                Contact Us
              </a>
            </div>
            <div className="ml-auto">
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
            </div>
          </div>
        </nav>

        <div class="main parallax">
          <Loader
            type="Rings"
            color="#00BFFF"
            height={700}
            width={700}
            margin-top={100}
            timeout={1000} //3 secs
          ></Loader>
          <img
            id="logoimg"
            class="center-block"
            src={require("../images/minelogo.png")}
          ></img>
        </div>

        <div class="about text-center" id="aboutnav">
          <h1>About</h1>
          <p1>
            Online content is easily accessible today by millions of people
            worldwide. Streaming companies have made vast troves of media
            available at your fingertips. This rapid proliferation of online
            content has paradoxically created another problem for people -
            indecisiveness. It can be hard to decide what content to consume in
            a world with increasingly short attention spans and tighter
            schedules. Furthermore, it can be a very tedious process to login
            and search for various different platforms in order to find
            something that fits. Mine aims to solve this problem by aggregating
            popular online content stores in order to present the user with the
            most relevant media available online - all in a single location. A
            user can simply specify a search term and obtain relevant results -
            aggregated neatly by category so that they can easily pick and
            choose what they want.
          </p1>
        </div>

        <div class="team text-center" id="teamnav">
          <h1>Team</h1>
          <div class="container">
            <div class="row">
              <div class="col-6">
                <div class="card" id="teamcard">
                  <img
                    src={require("../images/pooja.jpg")}
                    class="card-img-top"
                    id="teamimg"
                    alt="..."
                  ></img>
                  <div class="card-body">
                    <h5 class="card-title">Pooja Tewari</h5>
                    <p class="card-text">Front-end developer</p>
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="card" id="teamcard">
                  <img
                    src={require("../images/shivangi.jpg")}
                    class="card-img-top"
                    id="teamimg"
                    alt="..."
                  ></img>
                  <div class="card-body">
                    <h5 class="card-title">Shivangi Chand</h5>
                    <p class="card-text">Front-end developer</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-6">
                <div class="card" id="teamcard">
                  <img
                    src={require("../images/amol.jpg")}
                    class="card-img-top"
                    id="teamimg"
                    alt="..."
                  ></img>
                  <div class="card-body">
                    <h5 class="card-title">Amol Jha</h5>
                    <p class="card-text">Back-end developer</p>
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="card" id="teamcard">
                  <img
                    src={require("../images/utkarsh.jpg")}
                    class="card-img-top"
                    id="teamimg"
                    alt="..."
                  ></img>
                  <div class="card-body">
                    <h5 class="card-title">Utkarsh Agarwal</h5>
                    <p class="card-text">Back-end developer</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="contact text-center" id="contactnav">
          <h1>Contact Us</h1>
          <div class="card-group">
            <div class="card">
              <img
                src={require("../images/phone.png")}
                class="card-img-top"
                id="conimg"
                alt="..."
              ></img>
              <div class="card-body">
                <h5 class="card-title">+1 734-846-6033</h5>
              </div>
            </div>
            <div class="card">
              <img
                src={require("../images/mail.png")}
                class="card-img-top"
                id="conimg"
                alt="..."
              ></img>
              <div class="card-body">
                <h5 class="card-title">mineapp.tech@gmail.com</h5>
              </div>
            </div>
            <div class="card">
              <img
                src={require("../images/location.png")}
                class="card-img-top"
                id="conimg"
                alt="..."
              ></img>
              <div class="card-body">
                <h5 class="card-title">
                  340 Centennial Dr, West Lafayette, IN 94506
                </h5>
              </div>
            </div>
          </div>
          <br></br>
          <br></br>
          <h5><a href={"/feedback"}>Click Here to Submit your Feedback!</a></h5>
        </div>
        <footer>
          <div class="text-center" id="homefooter">
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
    document.body.className = "appBody";
  }
}
