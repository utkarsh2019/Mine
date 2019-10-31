import React, { Component } from "react";
import { ACCESS_TOKEN } from "../constants/Constants";
import { Redirect } from "react-router-dom"

class OAuth2RedirectHandler extends Component {
    getUrlParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");

        var results = regex.exec(this.props.location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    };

    render() {        
        const token = this.getUrlParameter("token");
        const error = this.getUrlParameter("error");

        if(token) {
            document.cookie = "accessToken=" + token + ";path=/";
            document.cookie = "tokenType=Bearer;path=/";
            // window.location.replace("/profile");

            // localStorage.setItem(ACCESS_TOKEN, token);
            return <Redirect to={{
                pathname: "/profile",
                state: { from: this.props.location }
            }}/>; 
        } else {
            // window.location.replace("/login");
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