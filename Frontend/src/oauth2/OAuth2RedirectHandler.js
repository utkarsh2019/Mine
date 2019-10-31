import React, { Component } from "react";
import { ACCESS_TOKEN } from "../constants/Constants";
import { Redirect } from "react-router-dom"
import { setCookies } from "../utils/CookieUtil";
import { getUrlParameter } from "../utils/UrlUtil"

class OAuth2RedirectHandler extends Component {

    render() {        
        const token = getUrlParameter("token", this.props.location.search);
        const error = getUrlParameter("error", this.props.location.search);

        if(token) {
            setCookies(token, "Bearer");
            return <Redirect to={{
                pathname: "/profile",
                state: { from: this.props.location }
            }}/>; 
        } else {
            return <Redirect to={{
                pathname: "/login",
                state: { 
                    from: this.props.location,
                    error: error 
                }
            }}/>; 
        }
    }
}

export default OAuth2RedirectHandler;