import { CATEGORY_TYPES } from "../constants/Constants";

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
    let categoryPref = categoryPreferences.split(",");
    let categoryPrefString = "";
    for(let i=0; i < categoryPref.length; i++){
        if (i != 0) {
            categoryPrefString += ",";
        }
        categoryPrefString += CATEGORY_TYPES.get(categoryPref[i]);
    }
    localStorage.setItem("categoryPreferences", categoryPrefString);
}

export function setSearchCategory(category) {
    localStorage.setItem("searchCategory", category);
}

export function setSearchQuery(query) {
    localStorage.setItem("searchQuery", query);
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

export function getByValue(map, searchValue) {
    for (let [key, value] of map.entries()) {
        if (value === searchValue) {
            return key;
        }
    }
}