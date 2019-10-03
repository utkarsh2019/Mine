import React, {Component} from 'react';
import '../css/bootstrap.css';
import '../css/Edit.css';

export default class Edit extends Component {
 render (){
  return (
    <div className="Edit">  
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"><img src={require("./../img/minelogo.png")} width="50" height="50" class="d-inline-block" alt=""></img>Mine</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link" href="#aboutnav">Home</a>
            <a class="nav-item nav-link" href="#teamnav">Trending</a>
            <a class="nav-item nav-link" href="#teamnav">Search</a>

          </div>
        </div>
        <ul class="nav justify-content-end">
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog">Account</button>
          </li>
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog">Logout</button>
          </li>
        </ul>
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
          <h2>Edit Profile</h2>
          <hr></hr>
          <div className="row">
            <div className="col-sm-4">
              <h5>PROFILE</h5>
            </div>
            <div className="col-sm-8">
              <p><b>Names</b></p>
              <p>Email:Email Address of the User</p>
              <hr></hr>
              <img src={require("./../img/profile.png")} height="75" width="75"></img>
            </div>
          </div>
          <hr></hr>
          <div className="row">
            <div className="col-sm-4">
              <h5>PREFERENCES</h5>
            </div>
            <div className="col-sm-8">
              <p><b>Order of Categories</b></p>
              <ul class="list-group">
                <li class="list-group-item">Movies</li>
                <li class="list-group-item">Music</li>
                <li class="list-group-item">Social</li>
                <li class="list-group-item">Text</li>
                <li class="list-group-item">Audio</li>
              </ul>
              <hr></hr>
              <p><b>Number of Searches Displayed:3</b></p>
            </div>
          </div>
          <hr></hr>
          <br></br>
          <br></br>
          <div className="row">
          <div className="col-sm-6 text-center  ">
          <button type="button" class="btn btn-primary">Edit</button>
          </div>
          <div className="col-sm-6 text-center">
          <button type="button" class="btn btn-danger">Delete</button>
          </div>
        </div>
        <br></br>
        <br></br>
        </div>
        <div className="col-sm-2"></div>
      </div>
    </body>


    <footer>
          <div class = "footer text-center">
              <p>Mine Copyright &copy; 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja Tewari</p>
          </div>
    </footer>

    </div>
  );
 }
}
