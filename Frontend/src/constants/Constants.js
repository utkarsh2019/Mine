export const API_BASE_URL = "http://api.mineapp.tech";
export const ACCESS_TOKEN = "accessToken";

export const OAUTH2_REDIRECT_URI = "http://mineapp.tech/oauth2/redirect"

export const GOOGLE_AUTH_URL = API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + "/oauth2/authorize/facebook?redirect_uri=" + OAUTH2_REDIRECT_URI;

export const API_IMAGES = {
    "youtube": "./../images/youtubelogo.png",
    "vimeo": "./../images/vimeologo.png",
    "dailymotion": "./../images/dailymotionlogo.png",
    "tmdb": "./../images/minelogo.png",
    "tvmaze": "./../images/minelogo.png"
}