import React, {Component} from 'react';
import logo from '../img/minelogo.png';
import '../css/forgotpassword.css';
import '../css/bootstrap.css';


export default class ForgotPassword extends Component{

  render () {
    return (
      <div>  
        <div class="container-fluid" >

            <div class="row regcontain" id="regid">
                <div class="col">
                    <img class = "center-block" src={require("../img/minelogo.png")}></img>
                </div>

                <div class="col">
                    <div class = "forgotpassword" >
                    <form>
                        <h2>Forgot Password</h2>
                        <hr></hr>
                        
                        <div class="form-group">
                        <label for="emailinput">Email address</label>
                        <input type="email" class="form-control" id="emailinput" placeholder="name@example.com"></input>
                        </div>
                       
                        <button type="button" class="btn btn-primary">Submit</button>
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
    document.body.className = 'bodyForgotPassword'
  }
} 
