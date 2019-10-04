import React, {Component} from 'react';
import '../css/verifyaccount.css';
import '../css/bootstrap.css';
import axios from 'axios';


export default class VerifyAccount extends Component{


  update(){
    
  };

  render () {
    return (
      <div>  
        <div class="container-fluid" >

            <div class="row regcontain" id="regid">
                <div class="col">
                    <img class = "center-block" src={require("../img/minelogo.png")}></img>
                </div>

                <div class="col">
                    <div class = "verifyaccount" >
                    <form>
                        <h2>Verify Account</h2>
                        <hr></hr>
                       
                        <button type="button" class="btn btn-primary" onClick={this.update}>Verify</button>
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
    document.body.className = 'bodyVerifyAccount'
  }
} 
