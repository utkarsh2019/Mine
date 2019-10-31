export function getJwtToken() {
  let cookie = document.cookie.split(";");
  let cookie1 = cookie[0].split("=");
  let cookie2 = cookie[1].split("=");
  let type, token;
  if (cookie1[0] === "tokenType") {
    type = cookie1[1];
    token = cookie2[1];
  } else {
    type = cookie2[1];
    token = cookie1[1];
  }
  return [type, token];
}

export function deleteCookies() {
  document.cookie = "accessToken= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
  document.cookie = "tokenType= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
}

export function setCookies(token, type) {
  document.cookie = "accessToken=" + token + ";path=/";
  document.cookie = "tokenType=" + type + ";path=/";
}

export function checkUserLoggedIn() {
  let cookies = document.cookie.split(";");
  if (cookies.length < 2) {
    return false;
  }
  let accessToken = "accessToken=";
  let tokenType = "tokenType=";
  if ((!cookies[0].startsWith(accessToken) && !cookies[0].startsWith(tokenType)) ||
    (!cookies[1].startsWith(accessToken) && !cookies[1].startsWith(tokenType))) {
      return false;
  }
  return true;
}