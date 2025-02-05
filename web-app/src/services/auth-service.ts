import apiClient from "@/config/axios.config.ts";
import {
  LoginRequest,
  LoginRequestSchema,
  LoginResponse,
  LoginResponseSchema,
} from "@/schemas/auth-schemas.ts";
import axios from "axios";
import { z } from "zod";
import { toast } from "@/hooks/use-toast.ts";

export const login = async (
  loginData: LoginRequest,
): Promise<LoginResponse | undefined> => {
  try {
    LoginRequestSchema.parse(loginData);
  } catch (e) {
    if (e instanceof z.ZodError) {
      toast({
        title: "Login Validation Error Occurred",
        description: e.errors[0]?.message || "Login validation failed",
        variant: "destructive",
      });
      // alert(e.errors[0]?.message || "Login validation failed");
      return;
    }
  }

  try {
    const response = await apiClient.post("user/login", loginData);
    toast({
      title: "Login Successful",
      description: "You have successfully logged in",
      variant: "default",
    });
    return LoginResponseSchema.parse(response.data);
  } catch (e) {
    if (axios.isAxiosError(e)) {
      toast({
        title: "Login Validation Error Occurred",
        description: e.response?.data?.message || "Login failed",
        variant: "destructive",
      });
    } else {
      toast({
        title: "Unexpected Error Occurred while logging in",
        description: "Please try again later",
        variant: "destructive",
      });
    }
  }
};
