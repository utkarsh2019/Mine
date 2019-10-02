import React, {Component} from 'react';
import logo from '../img/logo.svg';
import '../css/login.css';
import '../css/bootstrap.css';


export default class Login extends Component{

  render () {
    return (
      <div className="App">  
      <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <a class="navbar-brand" href="#">Mine</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link" href="#aboutnav">About</a>
            <a class="nav-item nav-link" href="#teamnav">Team</a>
            <a class="nav-item nav-link" href="#contactnav">Contact Us</a>
          </div>
        </div>
        <ul class="nav justify-content-end">
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog">Signup</button>
          </li>
          <li class="nav-item">
              <button type="button" class="btn btn-info navsignlog">Login</button>
          </li>
        </ul>
      </nav>

      <div class="main parallax">
          <img class = "center-block" src={require("../img/minelogo.png")}></img>
      </div>

      <div class="about text-center" id="aboutnav">
          <h1 >About</h1>
          <p1>Online content is easily accessible today by millions of people worldwide. Streaming companies have made vast troves of media available at your fingertips. This rapid proliferation of online content has paradoxically created another problem for people - indecisiveness. It can be hard to decide what content to consume in a world with increasingly short attention spans and tighter schedules. Furthermore, it can be a very tedious process to login and search for various different platforms in order to find something that fits. Mine aims to solve this problem by aggregating popular online content stores in order to present the user with the most relevant media available online - all in a single location. A user can simply specify a search term and obtain relevant results - aggregated neatly by category so that they can easily pick and choose what they want.</p1>
      </div>

      <div class="team text-center" id="teamnav">
          <h1 >Team</h1>
          <div class="container">
            <div class="row">
              <div class="col-6">
                  <div class="card" >
                    <img src={require("../img/pooja.jpg")} class="card-img-top" alt="..."></img>
                    <div class="card-body">
                      <h5 class="card-title">Pooja Tewari</h5>
                      <p class="card-text">Front-end developer</p>
                    </div>
                  </div>
              </div>
              <div class="col-6">
                  <div class="card" >
                    <img src={require("../img/shivangi.jpg")} class="card-img-top" alt="..."></img>
                    <div class="card-body">
                      <h5 class="card-title">Shivangi Chand</h5>
                      <p class="card-text">Front-end developer</p>
                    </div>
                  </div>
              </div>
            </div>
            <div class="row">
              <div class="col-6">
                  <div class="card" >
                    <img src={require("../img/amol.jpg")} class="card-img-top" alt="..."></img>
                    <div class="card-body">
                      <h5 class="card-title">Amol Jha</h5>
                      <p class="card-text">Back-end developer</p>
                    </div>
                  </div>
              </div>
              <div class="col-6">
                  <div class="card" >
                    <img src={require("../img/utkarsh.jpg")} class="card-img-top" alt="..."></img>
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
          <h1 >Contact Us</h1>
          <form>
              <div class="form row">
                  <div class="col">
                  <input type="text" class="form-control" placeholder="First name"></input>
                  </div>
                  <div class="col">
                  <input type="text" class="form-control" placeholder="Last name"></input>
                  </div>
              </div>
              <hr></hr>
              <div class="form row">
                  <div class="col">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Email"></input>
                  </div>
              </div>
              <hr></hr>
              <div class="form row">
                  
                  <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" placeholder="Message"></textarea>
                  
              </div>
              <hr></hr>
              <button type="submit" class="btn btn-info">Submit</button>
          </form>
      </div>
      <footer>
          <div class = "footer text-center">
              <p>Mine Copyright &copy; 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja Tewari</p>
          </div>
      </footer>

      </div>
    );
  }

  componentDidMount(){
    document.body.className = 'appBody'
  }

} 
