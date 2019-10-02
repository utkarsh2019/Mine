import React, {Component} from 'react';
import logo from '../img/minelogo.png';
import '../css/login.css';
import '../css/bootstrap.css';


export default class Login extends Component{

  render () {
    return (
      <div className="App">  
        <div class="container-fluid" >

            <div class="row regcontain" id="regid">
                <div class="col">
                    <img class = "center-block" src={require("../img/minelogo.png")}></img>
                </div>

                <div class="col">
                    <div class = "register" >
                    <form>
                        <h2>Login</h2>
                        <hr></hr>
                       
                        <div class="form-group">
                        <label for="usernameinput">Username</label>
                        <input type="text" class="form-control" id="usernameinput" placeholder="Enter username"></input>
                        </div>
                        
                        <div class="form-group">
                        <label for="passwordinput">Password</label>
                        <input type="password" class="form-control" id="passwordinput" placeholder="Enter Password"></input>
                        </div>
                       
                        <button type="button" class="btn btn-primary">Login</button>

                    </form>
                    </div>
                </div>
            </div>
        </div>
      <footer>
          <div class = "footer text-center">
              <p>Mine Copyright &copy; 2019. Amol Jha, Shivangi Chand, Utkarsh Agarwal, Pooja Tewari</p>
          </div>
      </footer>
    </div>
    );
  }
} 
