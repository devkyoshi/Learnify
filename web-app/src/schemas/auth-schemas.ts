import { z } from "zod";

export const LoginRequestSchema = z.object({
  username: z.string().min(1, { message: "Username is required" }),
  password: z
    .string()
    .min(6, { message: "Password must be at least 6 characters" }),
});

export const LoginResponseSchema = z.object({
  data: z
    .object({
      userId: z.string(),
      token: z.string(),
      role: z.string(),
      firstName: z.string(),
      lastName: z.string(),
      email: z.string(),
      username: z.string(),
    })
    .optional(),
  success: z.boolean(),
  code: z.string().nullable(),
  message: z.string(),
});

export type LoginRequest = z.infer<typeof LoginRequestSchema>;
export type LoginResponse = z.infer<typeof LoginResponseSchema>;
