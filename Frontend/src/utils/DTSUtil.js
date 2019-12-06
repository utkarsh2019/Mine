export function setSearchInput(input, category) {
    localStorage.setItem("searchInput", input);
    localStorage.setItem("searchCategory", category);
}

export function getSearchInput() {
    return {
        "searchInput": localStorage.getItem("searchInput"),
        "searchCategory": localStorage.getItem("searchCategory")
    }
}
export function getSearchField(field) {
    return localStorage.getItem(field);
}
export function clearSearchCurrentInput() {
    localStorage.clear();
}
