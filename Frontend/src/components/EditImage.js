import React, { Component } from "react";
import "../css/bootstrap.css";
import axios from "axios";
import { file } from "@babel/types";
import { exact } from "prop-types";
import { getJwtToken, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { API_BASE_URL } from "../constants/Constants";
import { getCurrentUserField, setCurrentUser } from "../utils/UserStorageUtil";

export default class EditImage extends Component {

constructor(props){
  super(props);
  this.state = {
    file: null
  };


  this.imageState = this.imageState.bind(this);
  this.upload = this.upload.bind(this);
  this.deletePic = this.deletePic.bind(this);
  this.setUserFields = this.setUserFields.bind(this);
}

setUserFields = (profilePicUrl) => {
  if (profilePicUrl != null) {
    document.getElementById("profileImage").src = profilePicUrl;
  }
  else {
    document.getElementById("profileImage").src = require("../images/profile.png");
  }
};

reloadUser = () => {
  let jwt = getJwtToken();
  let type = jwt[0];
  let token = jwt[1];

  axios({
    method: "get",
    url: API_BASE_URL + "/user/me",
    headers: {
      Authorization: type + " " + token
    }
  })
    .then(response => {
      setCurrentUser(
        response.data.responseObject.name,
        response.data.responseObject.email,
        response.data.responseObject.profilePicUrl,
        response.data.responseObject.provider,
        response.data.responseObject.noOfSearches,
        response.data.responseObject.categoryPreferences
      );
      this.setUserFields(getCurrentUserField("profilePicUrl"));
    })
    .catch(error => {
      alert(error);
    });
};

imageState(evt){
  evt.preventDefault();

  let fileName = document.getElementById("inputGroupFile01").value;
  document.getElementById("File01").innerHTML = fileName;

  let file = evt.target.files[0];
  this.setState({file: file});

}

deletePic(){
  let jwt = getJwtToken();
  let type = jwt[0];
  let token = jwt[1];

    axios({
      method: "delete",
      url: API_BASE_URL + "/user/me/pic",
      headers: {
        Authorization: type + " " + token
      }
    })
      .then(response => {
        this.reloadUser();
      })
      .catch(error => {
        alert(error);
      });
};

upload(){
  let jwt = getJwtToken();
  let type = jwt[0];
  let token = jwt[1];

  const formData = new FormData();
  formData.append("file", this.state.file);

  let configData = {
    headers: {
      "Authorization": type + " " + token,
      "Content-Type": "multipart/form-data; boundary=--------------------------170163929665791275533836"
    }
  };


  axios.put(API_BASE_URL + "/user/me/pic", formData, configData )
  .then(response => {
    this.reloadUser();
  })
  .catch(error => {
    alert(error);
  });
}


render() {
  if (!checkUserLoggedIn()) {
    return redirectToHome(this.props.location);
  } 
    return (
      <div className="Edit">
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">
            <img
              src={require("../images/minelogo.png")}
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
                    src={require("../images/profile.png")}
                    height="200"
                    width="200"
                    class="rounded-circle"
                    id="profileImage"
                  ></img>
                </div>
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="inputGroupFileAddon01" onClick={this.upload}>Upload</span>
                </div>
                <div class="custom-file">
                  <input type="file" class="custom-file-input" id="inputGroupFile01"
                    aria-describedby="inputGroupFileAddon01" accept=".png, .jpg, .jpeg" onChange={this.imageState}></input>
                  <label class="custom-file-label" for="inputGroupFile01" id="File01">Choose file</label>
                </div>
                </div>
              </div>
            </div>
            <hr></hr>
        </div>
        </div>
        <div className="row">
          <div className="col-sm-12" align="center">
          <button
                    type="button"
                    class="btn btn-danger"
                    onClick={() => {
                      if (
                        window.confirm(
                          "Are you sure you wish to delete this picture?"
                        )
                      )
                        this.deletePic();
                    }}
                  >
                    Delete
                  </button>
          </div>
          <br></br>
          <br></br>
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

  componentDidMount() {
    this.setUserFields(getCurrentUserField("profilePicUrl"));
  };
}
