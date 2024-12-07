import axios from "axios";

//create an axios instance
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_REACT_API_URL || "http://localhost:8080",
  withCredentials: true,
});

apiClient.interceptors.request.use(
  (config) => {
    const token = getCookie("jwt"); //assuming cookie name is "jwt"

    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    console.log("Request error: ", error);
    return Promise.reject(error);
  },
);

//Helper to extract token from cookies
function getCookie(name: string): string | null {
  const match = document.cookie.match(new RegExp(`(^| )${name}=([^;]+)`));
  return match ? match[2] : null;
}

export default apiClient;
