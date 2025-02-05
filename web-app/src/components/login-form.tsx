import logoImage from "@/assets/images/learnify_logo.png";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import React, { useState } from "react";
import { LoginRequest } from "@/schemas/auth-schemas.ts";
import { login } from "@/services/auth-service.ts";
import {
  Dialog,
  DialogContent,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog.tsx";
import loginImg from "../assets/images/login_img.png";
import { CircleUser } from "lucide-react";
import { DialogDescription } from "@radix-ui/react-dialog";
import { useNavigate } from "react-router-dom";
import useAuthStore from "@/stores/auth-store.ts";

export function LoginForm() {
  const [open, setOpen] = React.useState(true);
  const navigate = useNavigate();
  const {loginUser} = useAuthStore();

  const [form, setForm] = useState<LoginRequest>({
    username: "",
    password: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async () => {
    const response = await login(form);
    if (response?.success) {
      setOpen(false);
      loginUser({
        token: response.data?.token as string,
        role: response.data?.role as string,
        userId: response.data?.userId as string,
        email: response.data?.email as string,
        username: response.data?.username as string,
        firstName: response.data?.firstName as string,
        lastName: response.data?.lastName as string,
      });
      navigate("/dash-teacher");
    }
  };
  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button className={"bg-primary"} size={"sm"}>
          <CircleUser />
          Login
        </Button>
      </DialogTrigger>
      <DialogContent className="dark:bg-blend-darken  p-2 overflow-hidden  md:max-h-[500px] md:max-w-[700px] lg:max-w-[800px] flex flex-col md:flex-row items-center">
        <DialogTitle hidden={true} />
        <DialogDescription hidden={true} />

        <div className="w-full md:w-1/2 flex justify-center md:block">
          <img
            src={loginImg}
            alt="Login image"
            className="w-full h-auto object-contain max-h-[200px] md:max-h-none"
          />
        </div>
        <Card className="mx-auto max-w-sm border-none shadow-none w-full md:w-1/2 bg-muted/50 ">
          <CardHeader>
            <CardTitle className="text-2xl flex items-center gap-2 flex-row">
              <img alt={"logo-img"} src={logoImage} className={"h-8 w-8"} />
              Welcome Back!
            </CardTitle>
            <CardDescription>
              Enter your email below to login to your account
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="grid gap-4">
              <div className="grid gap-2">
                <Label htmlFor="username">User Name</Label>
                <Input
                  id="username"
                  type="text"
                  name="username"
                  placeholder="username"
                  value={form.username}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="password">Password</Label>
                </div>
                <Input
                  id="password"
                  type="password"
                  name={"password"}
                  required
                  placeholder={"password"}
                  value={form.password}
                  onChange={handleChange}
                />
                <a href="/" className="ml-auto inline-block text-sm underline">
                  Forgot your password?
                </a>
              </div>
              <Button onClick={handleLogin} className="w-full">
                Login
              </Button>
              <Button variant="outline" className="w-full">
                Login with Google
              </Button>
            </div>
            <div className="mt-4 text-center text-sm">
              Don&apos;t have an account?{" "}
              <a href="/" className="underline">
                Sign up
              </a>
            </div>
          </CardContent>
        </Card>
      </DialogContent>
    </Dialog>
  );
}
