import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from './components/Home';
import Profile from './components/Profile';
import Register from './components/Register';
import Login from './components/Login';


export default class App extends Component {
  render () {
   return (
    <Router>
    <div>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path ="/profile" component={Profile}/>
        <Route path="/register" component={Register}/>
        <Route path="/login" component={Login}/>
      </Switch>
    </div>
  </Router>
  );
   }
}
