import React, {Component} from 'react';
import '../css/bootstrap.css';
import '../css/Profile.css';

export default class Profile extends Component {
 render (){
  return (
    <div className="Profile">  
      <div>
        <nav className = "navbar navbar-expand-lg navbar-light fixed-top">
        <img src={require("../img/minelogo.png")} className="imgsize"></img>
        <ul>
        <a class="text-white">Home</a>
        </ul>
        </nav>
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
