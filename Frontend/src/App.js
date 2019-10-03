import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from './components/Home';
import Profile from './components/Profile';
import Register from './components/Register';
import Login from './components/Login';
import OAuth2RedirectHandler from './oauth2/OAuth2RedirectHandler';
import { getCurrentUser } from './util/APIUtils';
import Alert from 'react-s-alert';
import { ACCESS_TOKEN } from './constants/index';
import PrivateRoute from './common/PrivateRoute';
import AppHeader from './common/AppHeader';
import NotFound from './common/NotFound';



export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: false
    }

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  loadCurrentlyLoggedInUser() {
    this.setState({
      loading: true
    });

    getCurrentUser()
    .then(response => {
      this.setState({
        currentUser: response,
        authenticated: true,
        loading: false
      });
    }).catch(error => {
      this.setState({
        loading: false
      });  
    });    
  }

  handleLogout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.setState({
      authenticated: false,
      currentUser: null
    });
    Alert.success("You're safely logged out!");
  }

  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }

  render () {
  return (
    <div className="app">
      <div className="app-top-box">
        <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout} />
      </div>
      <div className="app-body">
        <Switch>
          <Route exact path="/" component={Home}></Route>           
          <PrivateRoute path="/profile" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
            component={Profile}></PrivateRoute>
          <Route path="/login"
            render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/register"
            render={(props) => <Register authenticated={this.state.authenticated} {...props} />}></Route>
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>  
          <Route component={NotFound}></Route>
        </Switch>
      </div>
      <Alert stack={{limit: 3}} 
        timeout = {3000}
        position='top-right' effect='slide' offset={65} />
    </div>
  );

  }
}
