import React, {Component} from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import Profile from "./components/Profile";
import Signup from "./components/Signup";
import Login from "./components/Login";
import ForgotPassword from "./components/ForgotPassword";
import OAuth2RedirectHandler from "./oauth2/OAuth2RedirectHandler";
import Alert from "react-s-alert";
import NotFound from "./components/NotFound";
import Edit from "./components/Edit"
import Dashboard from "./components/Dashboard";
import Trending from "./components/Trending";
import Search from "./components/Search";
import ForgotPasswordUpdate from "./components/ForgotPasswordUpdate";
import VerifyAccount from "./components/VerifyAccount";
import EditPassword from "./components/ChangePassword";


export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: false
    }

    // this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    // this.handleLogout = this.handleLogout.bind(this);
  }

  // loadCurrentlyLoggedInUser() {
  //   this.setState({
  //     loading: true
  //   });

  //   getCurrentUser()
  //   .then(response => {
  //     this.setState({
  //       currentUser: response,
  //       authenticated: true,
  //       loading: false
  //     });
  //   }).catch(error => {
  //     this.setState({
  //       loading: false
  //     });  
  //   });    
  // }

  // handleLogout() {
  //   localStorage.removeItem(ACCESS_TOKEN);
  //   this.setState({
  //     authenticated: false,
  //     currentUser: null
  //   });
  //   Alert.success("You're safely logged out!");
  // }

  // componentDidMount() {
  //   this.loadCurrentlyLoggedInUser();
  // }

  render () {
  return (
    <div className="app">    
      <div className="app-body">
        <Switch>
          <Route exact path="/" component={Home}></Route>           
          <Route path="/login"
            render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/signup"
            render={(props) => <Signup authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/forgotpassword"
            render={(props) => <ForgotPassword authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>  
          <Route path="/dashboard"
            render={(props) => <Dashboard authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/profile"
            render={(props) => <Profile authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/edit"
            render={(props) => <Edit authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/trending"
            render={(props) => <Trending authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/search"
            render={(props) => <Search authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/forgotpasswordupdate"
            render={(props) => <ForgotPasswordUpdate authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/verifyaccount"
            render={(props) => <VerifyAccount authenticated={this.state.authenticated} {...props} />}></Route>
           <Route path="/editpassword"
            render={(props) => <EditPassword authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/editimage"
            render={(props) => <EditImage authenticated={this.state.authenticated} {...props} />}></Route>
          <Route component={NotFound}></Route>
        </Switch>
      </div>
      <Alert stack={{limit: 3}} 
        timeout = {3000}
        position="top-right" effect="slide" offset={65} />
    </div>
  );

  }
}
