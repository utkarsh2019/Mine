import React from "react";
import { Redirect } from "react-router-dom"

export function redirectToProfile(location) {
    return <Redirect to={{
        pathname: "/profile",
        state: { from: location }
    }}/>;
}

export function redirectToDashboard(location) {
    return <Redirect to={{
        pathname: "/dashboard",
        state: { from: location }
    }}/>;
}

export function redirectToLogin(location, error) {
    return <Redirect to={{
        pathname: "/login",
        state: { from: location,
                 error: error
                }
    }}/>;
}

export function redirectToHome(location) {
    return <Redirect to={{
        pathname: "/",
        state: { from: location }
    }}/>;
}