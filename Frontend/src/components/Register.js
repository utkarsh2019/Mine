import React, {Component} from 'react';
import logo from '../img/minelogo.png';
import '../css/register.css';
import '../css/bootstrap.css';


export default class Register extends Component{

  render () {
    return (
      <div>  
        <div class="container-fluid" >

            <div class="row regcontain" id="regid">
                <div class="col">
                    <img class = "center-block" src={require("../img/minelogo.png")}></img>
                </div>

                <div class="col">
                    <div class = "register" >
                    <form>
                        <h2>Register</h2>
                        <hr></hr>
                        <div class="form-group">
                        <label for="nameinput">Name</label>
                        <input type="text" class="form-control" id="nameinput" placeholder="Enter name"></input>
                        </div>
                        <div class="form-group">
                        <label for="emailinput">Email address</label>
                        <input type="email" class="form-control" id="emailinput" placeholder="name@example.com"></input>
                        </div>
                        <div class="form-group">
                        <label for="passwordinput">Password</label>
                        <input type="password" class="form-control" id="passwordinput" placeholder="Enter Password"></input>
                        </div>
                        <div class="form-group">
                        <label for="confirmpasswordinput">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmpasswordinput" placeholder="Re-enter Password"></input>
                        </div>
                        <button type="button" class="btn btn-primary">Sign up</button>
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

  componentDidMount(){
    document.body.className = 'bodyRegister'
  }
} 
