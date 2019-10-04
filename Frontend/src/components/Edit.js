import React, {Component} from 'react';
import '../css/bootstrap.css';
import '../css/Edit.css';
import axios from 'axios';

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
                <div class="form-group">
                    <label for="nameinput">Name</label>
                    <input type="text" class="form-control" id="nameinput"></input>
                </div>              
                <div class="form-group">
                        <label for="emailinput">Email address</label>
                        <input type="email" class="form-control" id="emailinput"></input>
                </div>
                <div class="form-group">
                        <label for="passwordinput" align="right">Password</label>
                        <a class="align-right" href="#">Change Password</a>
                </div>
              <hr></hr>
              <div class="form-group">
              <img src={require("./../img/profile.png")} height="75" width="75"></img>
              <span class="align-right"><input type="file" class= "form-control-file" id="exampleFormControlFile1"></input></span>
              </div>
            </div>
          </div>
          <hr></hr>
          <div className="row">
            <div className="col-sm-4">
              <h5>PREFERENCES</h5>
            </div>
            <div className="col-sm-8">
            <p><b>Option of the following categories:</b> Movies, Music, Social, Text, Audio</p>
            <p>Please input each category as a preference only once.</p>
              <form>
                <div class="form-group">
                  <input type="text" class="form-control" id="preferenceInput1" placeholder="First Preference">
                  </input>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="preferenceInput2" placeholder="Second Preference">
                  </input>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="preferenceInput3" placeholder="Third Preference">
                  </input>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="preferenceInput4" placeholder="Fourth Preference">
                  </input>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="preferenceInput5" placeholder="Fifth Preference">
                  </input>
                </div>
              </form>
              
              <hr></hr>
              <div class = "row">
              <p><b>Number of Searches Displayed</b></p>
              <br></br>
              <br></br>
              <br></br>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"></input>
                <label class="form-check-label" for="inlineRadio1">1</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"></input>
                <label class="form-check-label" for="inlineRadio2">3</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"></input>
                <label class="form-check-label" for="inlineRadio3">5</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"></input>
                <label class="form-check-label" for="inlineRadio3">7</label>
              </div>
              </div>
            </div>
          </div>
          <hr></hr>
          <br></br>
          <br></br>
          
          <div className="row">
          
          <button type="button" class="btn btn-primary" onClick={this.updateInfo}>Update</button>
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
  updateInfo(){
    /*let name = document.getElementById("nameinput").value;
    console.log(name);
    if(name.value=="")
    {

    }
    let email = document.getElementById("emailinput").value;
    console.log(email);*/
    const prefcategories = ["movies", "music", "social", "text", "audio"];

    let firstPref = document.getElementById("preferenceInput1").value;
    firstPref = firstPref.toLowerCase();
    let secondPref = document.getElementById("preferenceInput2").value;
    secondPref = secondPref.toLowerCase();
    let thirdPref = document.getElementById("preferenceInput3").value;
    thirdPref = thirdPref.toLowerCase();
    let fourthPref = document.getElementById("preferenceInput4").value;
    fourthPref = fourthPref.toLowerCase();
    let fifthPref = document.getElementById("preferenceInput5").value;
    fifthPref = fifthPref.toLowerCase();

    let prefinputs = [firstPref, secondPref, thirdPref, fourthPref, fifthPref];
    let prefset = new Set(prefinputs);
    let flag=0;
      prefinputs.forEach(input => {
        if(!prefcategories.includes(input)){
          flag=1;
        }
      })
      if(flag==1)
      {
        alert("Not a valid category, please input valid category inputs");
      }
      if(prefset.size != prefinputs.length)
      {
        flag=2;
      }
      if(flag==2)
      {
        alert("Error! Only one category per preference please!");
      }

  }

}