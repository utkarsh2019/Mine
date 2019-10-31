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
  cookies[1] = cookies[1].trim();
  let accessToken = "accessToken=";
  let tokenType = "tokenType=";
  if(document.cookie.indexOf(accessToken) == -1 || document.cookie.indexOf(tokenType) == -1 ){
    return false;
  }
  return true;
}