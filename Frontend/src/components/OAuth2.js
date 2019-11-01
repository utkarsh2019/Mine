import React, { Component } from "react";
import { setCookies } from "../utils/CookieUtil";
import { getUrlParameter } from "../utils/UrlUtil"
import { redirectToDashboard, redirectToLogin } from "../utils/RedirectUtil";

class OAuth2 extends Component {

    render() {        
        const token = getUrlParameter("token", this.props.location.search);
        const error = getUrlParameter("error", this.props.location.search);

        if(token) {
            setCookies(token, "Bearer");
            return redirectToDashboard(this.props.location);
        } else {
            return redirectToLogin(this.props.location, error);
        }
    }
}

export default OAuth2;