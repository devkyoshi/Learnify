import { api } from "../config/app_config";
import { removeAuthData, setToken, setUser } from "../utils/cookie_utils";
import { createToast } from "../utils/helper_functions";

export const login = async (loginData) => {
  try {
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

export const registerTeacher = async (registerData) => {
    try {
        const response = await createToast(
        api.post("user/register/teacher", registerData),
        {
            loading: "Registering Teacher...",
            success: (response) => response?.data?.message || "Registration successful!",
            error: (error) => error?.response?.data?.message || "Registration failed",
        }
        );

        return response?.success;
    } catch (error) {
        console.log("Error during teacher registration:", error);
    }
}

export const registerStudent = async (registerData) => {
    try {
        const response = await createToast(
        api.post("user/register/student", registerData),
        {
            loading: "Registering Student...",
            success: (response) => response?.data?.message || "Registration successful!",
            error: (error) => error?.response?.data?.message || "Registration failed",
        }
        );

        return response?.success;
    } catch (error) {
        console.log("Error during student registration:", error);
    }
}

export const logout = () => {
  removeAuthData();
};
