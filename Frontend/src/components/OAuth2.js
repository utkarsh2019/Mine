import React, { Component } from "react";
import { getJwtToken, setCookies } from "../utils/CookieUtil";
import { getUrlParameter } from "../utils/UrlUtil"
import { redirectToDashboard, redirectToLogin } from "../utils/RedirectUtil";
import axios from "axios";
import { setCurrentUser } from "../utils/StorageUtil";
import { API_BASE_URL } from "../constants/Constants";

class OAuth2 extends Component {

    setUser = () => {
        let jwt = getJwtToken();
        let type = jwt[0];
        let token = jwt[1];
    
        axios({
            method: "get",
            url: API_BASE_URL + "/user/me",
            headers: {
            Authorization: type + " " + token
            }
        })
            .then(function(response) {
                setCurrentUser(
                    response.data.responseObject.name,
                    response.data.responseObject.email,
                    response.data.responseObject.profilePicUrl,
                    response.data.responseObject.provider,
                    response.data.responseObject.noOfSearches,
                    response.data.responseObject.categoryPreferences
                );
            })
            .catch(function(error) {
                alert(error);
            });
    };
    
    render() {        
        const token = getUrlParameter("token", this.props.location.search);
        const error = getUrlParameter("error", this.props.location.search);

        if(token) {
            setCookies(token, "Bearer");
            this.setUser();
            return redirectToDashboard(this.props.location);
        } else {
            return redirectToLogin(this.props.location, error);
        }
    }
}

export default OAuth2;