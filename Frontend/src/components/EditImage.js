import React, { Component } from "react";
import "../css/bootstrap.css";
import axios from "axios";
import "../css/Edit.css";
import Popup from "./Popup";
import { getJwtToken, checkUserLoggedIn } from "../utils/CookieUtil";
import { redirectToHome } from "../utils/RedirectUtil";
import { API_BASE_URL } from "../constants/Constants";
import { getCurrentUserField, setCurrentUser } from "../utils/StorageUtil";

export default class EditImage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      file: null,
      showPopup: false
    };
    this.imageState = this.imageState.bind(this);
    this.upload = this.upload.bind(this);
    this.deletePic = this.deletePic.bind(this);
    this.setUserFields = this.setUserFields.bind(this);
  }

  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup,
    });
  }


  setUserFields = (profilePicUrl) => {
    if (profilePicUrl != null) {
      document.getElementById("profileImage").src = profilePicUrl;
    }
    else {
      document.getElementById("profileImage").src = require("../images/profile.png");
    }
  };
  backtoProfile = () => {
    window.location.replace("/profile");
  }
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

  imageState(evt) {
    evt.preventDefault();

    let fileName = document.getElementById("inputGroupFile01").value;
    document.getElementById("File01").innerHTML = fileName;

    let file = evt.target.files[0];
    this.setState({ file: file });

  }

  deletePic() {
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
        this.togglePopup();
      })
      .catch(error => {
        this.togglePopup();
      });
  };

  upload() {
    let jwt = getJwtToken();
    let type = jwt[0];
    let token = jwt[1];

    let errorDetected = !!document.getElementById("errorDetected");
    let fileMissing = !!document.getElementById("FileMissingError");

    if (errorDetected) {
      document.getElementById("profileForm").removeChild(document.getElementById("errorDetected"));
    }
    if (fileMissing) {
      document.getElementById("profileForm").removeChild(document.getElementById("FileMissingError"));
    }


    if (document.getElementById("inputGroupFile01").files.length == 0) {
      let al = document.createElement("p");
      al.style.color = "Red";
      al.innerHTML = "Please Choose a File";
      al.id = "FileMissingError";
      document.getElementById("profileForm").appendChild(al);
    }

    const formData = new FormData();
    formData.append("file", this.state.file);

    let error = !!document.getElementById("FileMissingError");

    if (!error) {
      let configData = {
        headers: {
          "Authorization": type + " " + token,
          "Content-Type": "multipart/form-data; boundary=--------------------------170163929665791275533836"
        }
      };


      axios.put(API_BASE_URL + "/user/me/pic", formData, configData)
        .then(response => {
          this.reloadUser();
        })
        .catch(error => {
          let al = document.createElement("p");
          al.style.color = "Red";
          al.innerHTML = error.response.data.message;
          al.id = "errorDetected";
          document.getElementById("profileForm").appendChild(al);
        });
    }
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
                <div className="col-sm-8" id="profileForm">
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
                onClick={this.togglePopup.bind(this)}
              >
                Delete
                  </button>

              {this.state.showPopup ?
                <Popup
                  confirm='true'
                  header="Delete Profile Picture"
                  text='Do you wish to delete your profile picture?'
                  action={this.deletePic.bind(this)}
                  closePopup={this.togglePopup.bind(this)}
                />
                : null
              }

              <button
                id="editcancelbutton"
                type="button"
                class="btn btn-primary"
                onClick={this.backtoProfile}
              >
                Back
                </button>
            </div>
            <br></br>
            <br></br>
          </div>
        </body>
        <footer>
          <div class="editfooter text-center">
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
