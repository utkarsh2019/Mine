export function setCurrentUser(name, email, profilePicUrl, provider, noOfSearches, categoryPreferences) {
    localStorage.setItem("name", name);
    localStorage.setItem("email", email);
    if (profilePicUrl != undefined && profilePicUrl != null) {
        localStorage.setItem("profilePicUrl", profilePicUrl);
    }
    else {
        localStorage.removeItem("profilePicUrl");
    }
    localStorage.setItem("provider", provider);
    localStorage.setItem("noOfSearches", noOfSearches);
    localStorage.setItem("categoryPreferences", categoryPreferences);
}

export function getCurrentUser() {
    return {
        "name": localStorage.getItem("name"),
        "email": localStorage.getItem("email"),
        "profilePicUrl": localStorage.getItem("profilePicUrl"),
        "provider": localStorage.getItem("provider"),
        "noOfSearches": localStorage.getItem("noOfSearches"),
        "categoryPreferences": localStorage.getItem("categoryPreferences")
    }
}

export function getCurrentUserField(field) {
    return localStorage.getItem(field);
}

export function clearCurrentUser() {
    localStorage.clear();
}