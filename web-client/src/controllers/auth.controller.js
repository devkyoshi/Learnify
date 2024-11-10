import { api } from "../config/app_config";
import { removeAuthData, setToken, setUser } from "../utils/cookie_utils";
import { createToast } from "../utils/helper_functions";

export const login = async (loginData) => {
  try {
    // Call the createToast function and pass the API request with messages
    const response = await createToast(api.post("/user/login", loginData), {
      loading: "Logging in...",
      success: (response) => response?.data?.message || "Login successful!",
      error: (error) => error?.response?.data?.message || "Login failed",
    });

    if (response?.success && response?.data?.token) {
      setToken(response.data.token);
      setUser(response.data.userId, response.data.role);
    }

    return response?.success;
  } catch (error) {
    console.log("Error during login:", error);
  }
};

export const logout = () => {
  removeAuthData();
};
