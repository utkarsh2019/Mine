import React, {Component} from 'react';
import '../css/bootstrap.css';
import '../css/Profile.css';
import axios from 'axios';


export default class Profile extends Component {

  logout = () => {
    document.cookie = "accessToken= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
    document.cookie = "tokenType= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
    window.location.replace('\login');
  }

  load = () => {
    let cookie = document.cookie.split(';');
    let cookie1 = cookie[0].split('=');
    let cookie2 = cookie[1].split('=');
    let type, token;
    if(cookie1[0] === 'tokenType'){
      type=cookie1[1];
      token=cookie2[1];
    }
    else{
      type=cookie2[1];
      token=cookie1[1];
    }

    axios({
      method:'put',
      url:'http://localhost:8080/user/me',
      headers:{
          Authorization: (type + ' ' + token),
      }
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
      alert(error);
  });
  }

  
  deleteAccount = () => {
    let cookie = document.cookie.split(';');
    let cookie1 = cookie[0].split('=');
    let cookie2 = cookie[1].split('=');
    let type, token;
    if(cookie1[0] === 'tokenType'){
      type=cookie1[1];
      token=cookie2[1];
    }
    else{
      type=cookie2[1];
      token=cookie1[1];
    }
   

    axios({
      method:'delete',
      url:'http://localhost:8080/user/me',
      headers:{
          Authorization: (type + ' ' + token),
      }
  })
  .then(function (response) {
    console.log(response);
    document.getElementById('name').value="Name: "+response.responseObject.name;
    document.getElementById('email').value="Email: "+response.responseObject.email;
    document.getElementById('num').value="Number of Searches Displayed: "+response.responseObject.noOfPreviousSearches;
    
    let pref = response.responseObject.userPreferences.split(',');
    document.getElementById('item1').value=pref[0];
    document.getElementById('item2').value=pref[1];
    document.getElementById('item3').value=pref[2];
    document.getElementById('item4').value=pref[3];
    document.getElementById('item5').value=pref[4];
  })
  .catch(function (error) {
      alert(error);
  });

  };


 render (){
  return (
    <div className="Profile" onLoad={this.load}>  
      <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"><img src={require("./../img/minelogo.png")} width="50" height="50" class="d-inline-block" alt=""></img>Mine</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link">Home</a>
            <a class="nav-item nav-link">Trending</a>
            <a class="nav-item nav-link">Search</a>

          </div>
        </div>
        <ul class="nav justify-content-end">
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog">Account</button>
          </li>
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog" onClick={this.logout}>Logout</button>
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
          <h2>Account</h2>
          <hr></hr>
          <div className="row">
            <div className="col-sm-4">
              <h5>MY PROFILE</h5>
            </div>
            <div className="col-sm-8">
              <p id="name"><b>Name:Name of the User</b></p>
              <p id="email">Email:Email Address of the User</p>
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
                <li class="list-group-item" id="item1">Movies</li>
                <li class="list-group-item" id="item2">Music</li>
                <li class="list-group-item" id="item3">Social</li>
                <li class="list-group-item" id="item4">Text</li>
                <li class="list-group-item" id="item5">Audio</li>
              </ul>
              <hr></hr>
              <p id="num"><b>Number of Searches Displayed:3</b></p>
            </div>
          </div>
          <hr></hr>
          <br></br>
          <br></br>
          <div className="row">
          <div className="col-sm-6 text-center  ">
          <a href="\edit"><button type="button" class="btn btn-primary">Edit</button></a>
          </div>
          <div className="col-sm-6 text-center">
          <button type="button" class="btn btn-danger" onClick={() => { if (window.confirm('Are you sure you wish to delete this item?')) this.deleteAccount() } }>Delete</button>
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
