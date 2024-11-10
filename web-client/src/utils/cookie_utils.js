import Cookies from "js-cookie";

// Function to get the token from cookies
export const getToken = () => {
  return Cookies.get("token");
};

// Function to get user data from cookies
export const getUser = () => {
  const user = Cookies.get("user");
  return user ? JSON.parse(user) : null;
};

// Function to set the token in cookies
export const setToken = (token) => {
  Cookies.set("token", token, { expires: 1, path: "/" }); // 1-day expiry
};

// Function to set user data in cookies
export const setUser = (userId, role) => {
  Cookies.set("user", JSON.stringify({ userId, role }), {
    expires: 1,
    path: "/",
  });
};

// Function to remove token and user data
export const removeAuthData = () => {
  Cookies.remove("token");
  Cookies.remove("user");
};
