export function setSearchInput(input, category) {
    localStorage.setItem("searchInput", input);
}

export function getSearchInput() {
    return {
        "searchInput": localStorage.getItem("searchInput"),
    }
}
export function getSearchField(field) {
    return localStorage.getItem(field);
}
