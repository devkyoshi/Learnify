import React from "react";
import { Input, Button, Typography } from "@material-tailwind/react";
import FormDialogTemplate from "@components/templates/form-dialog";
import SignUpCard from "@pages/authentication/signup";

export default function LoginCard({ isOpen, onClose }) {
  const [openSignUp, setOpenSignUp] = React.useState(false);

  const handleSignUpOpen = () => {
    onClose();
    setOpenSignUp(true);
  };

  return (
    <div>
      <FormDialogTemplate isOpen={isOpen} onClose={onClose}>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-2">
          <div className="hidden md:block">
            <img
              alt="login-img"
              className="w-full h-auto"
              src="https://img.freepik.com/free-vector/sign-page-abstract-concept-illustration_335657-3875.jpg"
            />
          </div>
          <div>
            <div className="mb-6">
              <Typography
                variant="h4"
                className="text-center text-primary text-2xl md:text-3xl"
              >
                Welcome Back!
              </Typography>
              <Typography className="text-sm md:text-base text-center text-secondary font-semibold mt-2">
                Let’s get you signed in and back to what matters.
              </Typography>
            </div>
            <div>
              <div className="mb-4 pt-4">
                <Typography className="text-sm text-secondary font-semibold mb-2">
                  User Name
                </Typography>
                <Input
                  variant="outlined"
                  label="Username"
                  id="username"
                  type="text"
                  color="blue"
                  className="text-sm"
                />
              </div>
              <div className="mb-4">
                <Typography className="text-sm text-secondary font-semibold mb-2">
                  Password
                </Typography>
                <Input
                  variant="outlined"
                  label="Password"
                  id="password"
                  type="password"
                  color="blue"
                  className="text-sm"
                />
                <Typography className="md:text-sm text-xs mt-2 flex justify-end font-semibold text-secondary hover:text-primary cursor-pointer">
                  Forgot password?
                </Typography>
              </div>
              <div className="w-full flex flex-col gap-2">
                <Button
                  variant="filled"
                  fullWidth
                  className="mt-4 bg-secondary shadow-lg text-white"
                  type="button"
                >
                  Sign In
                </Button>
                <Button
                  size="sm"
                  variant="outlined"
                  className="flex items-center gap-3 justify-center hover:shadow-lg"
                >
                  <img
                    src="https://docs.material-tailwind.com/icons/google.svg"
                    alt="google"
                    className="h-6 w-6"
                  />
                  Login with Google
                </Button>
              </div>
            </div>
            <div className="mt-4 flex justify-center">
              <Typography className="text-xs md:text-sm text-secondary">
                Don't have an account?{" "}
                <strong
                  onClick={handleSignUpOpen}
                  className="font-bold text-secondary hover:text-primary cursor-pointer"
                >
                  Sign Up
                </strong>
              </Typography>
            </div>
          </div>
        </div>
      </FormDialogTemplate>
      {openSignUp && (
        <SignUpCard isOpen={openSignUp} onClose={() => setOpenSignUp(false)} />
      )}
    </div>
  );
}
