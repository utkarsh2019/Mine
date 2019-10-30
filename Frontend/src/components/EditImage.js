import React, { Component } from "react";
import "../css/bootstrap.css";
import axios from "axios";

export default class EditImage extends Component {


render() {
    return (
      <div className="Edit" onLoad={this.load}>
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">
            <img
              src={require("./../img/minelogo.png")}
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
              <a href={"/dashboard"} class="nav-item nav-link">
                Dashboard
              </a>
              <a href={"/trending"} class="nav-item nav-link">
                Trending
              </a>
              <a href={"/search"} class="nav-item nav-link">
                Search
              </a>
            </div>
          </div>
        </nav>
      </div>

      <body>
        <div className="row">
          <br></br>
          <br></br>
        </div>
        <div className="row">
          <div className="col-sm-2"></div>
          <div className="col-sm-8">
            <h2>Change Profile Picture</h2>
            <hr></hr>
            <div className="row">
              <div className="col-sm-2"></div>
              <div className="col-sm-8">
                <div class="form-group" align="center">
                  <img
                    src={require("./../img/profile.png")}
                    height="200"
                    width="200"
                    class="rounded-circle"
                  ></img>
                </div>
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                </div>
                <div class="custom-file">
                  <input type="file" class="custom-file-input" id="inputGroupFile01"
                    aria-describedby="inputGroupFileAddon01" accept=".png, .jpg, .jpeg"></input>
                  <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                </div>
                </div>
              </div>
            </div>
            <hr></hr>
        </div>
        </div>
        </body>
      <footer>
        <div class="footer text-center">
          <p>
            Mine App, 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja
            Tewari
          </p>
        </div>
      </footer>
    </div>
    )
};
}
