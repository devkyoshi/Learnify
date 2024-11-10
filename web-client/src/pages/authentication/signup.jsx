import React from "react";
import FormDialogTemplate from "@components/templates/form-dialog";
import { Button, Input, Typography } from "@material-tailwind/react";
import LoginCard from "./login";

export default function SignUpCard({ isOpen, onClose }) {
  const [openLogin, setOpenLogin] = React.useState(false);

  const handleLoginOpen = () => {
    onClose();
    setOpenLogin(true);
  };

  return (
    <div>
      <FormDialogTemplate isOpen={isOpen} onClose={onClose}>
        <div className="flex flex-col md:flex-row gap-4 md:gap-2 items-center">
          <div className="hidden md:block md:w-1/2">
            <img
              alt="signup-illustration"
              className="w-full h-auto rounded-lg"
              src="https://img.freepik.com/free-vector/sign-up-concept-illustration_114360-7885.jpg?t=st=1731213187~exp=1731216787~hmac=6fa0c6ecba22fbecfa87b11d23736f76cb046e8cab784e39cbe3c409b2ca53f2&w=740"
            />
          </div>
          {/* Form section */}
          <div className="w-full md:w-1/2 ">
            <div className="mb-6 text-center">
              <Typography className="text-lg md:text-2xl font-bold text-primary">
                Join the Learning Adventure!
              </Typography>
              <Typography className="text-xs md:text-sm text-secondary font-semibold mt-2">
                Empower your skills and unlock new knowledge. Begin your journey
                with us today!
              </Typography>
            </div>
            <div className="overflow-y-auto h-[50vh] md:h-96 p-2">
              <Typography className="text-sm text-secondary font-semibold mb-2">
                Personal Info
              </Typography>
              <Typography className="text-xs text-secondary font-semibold">
                Your Name
              </Typography>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
                <Input
                  type="text"
                  label="First Name"
                  containerProps={{ className: "mb-2" }}
                />
                <Input
                  type="text"
                  label="Last Name"
                  containerProps={{ className: "mb-2" }}
                />
              </div>
              <Typography className="text-xs text-secondary font-semibold">
                Address
              </Typography>
              <Input
                type="text"
                label="Address"
                containerProps={{ className: "mb-2" }}
              />

              <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
                <Input
                  type="text"
                  label="City"
                  containerProps={{ className: "mb-2" }}
                />
                <Input
                  type="text"
                  label="District"
                  containerProps={{ className: "mb-2" }}
                />
              </div>
              <Input
                type="tel"
                label="Phone Number"
                containerProps={{ className: "mb-2" }}
              />

              <div>
                <Typography className="text-sm text-secondary font-semibold mb-2">
                  Account Info
                </Typography>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
                  <div>
                    <Typography className="text-xs text-secondary font-semibold">
                      Email
                    </Typography>
                    <Input
                      type="email"
                      label="Email"
                      containerProps={{ className: "mb-2" }}
                    />
                  </div>
                  <div>
                    <Typography className="text-xs text-secondary font-semibold">
                      Username
                    </Typography>
                    <Input
                      type="text"
                      label="Username"
                      containerProps={{ className: "mb-2" }}
                    />
                  </div>
                </div>
              </div>
              <Typography className="text-xs text-secondary font-semibold">
                Enter Password
              </Typography>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
                <Input
                  type="password"
                  label="Password"
                  containerProps={{ className: "mb-4" }}
                />
                <Input
                  type="password"
                  label="Confirm Password"
                  containerProps={{ className: "mb-4" }}
                />
              </div>
            </div>
            <Button
              fullWidth
              className="mt-4 bg-secondary hover:bg-primary"
              buttonType="filled"
              ripple="light"
            >
              Sign Up
            </Button>

            <div className="flex justify-center mt-4">
              <Typography className="text-sm text-secondary">
                Already have an account?{" "}
                <strong
                  onClick={handleLoginOpen}
                  className="text-primary font-semibold cursor-pointer"
                >
                  Sign In
                </strong>
              </Typography>
            </div>
          </div>
        </div>
      </FormDialogTemplate>

      {/* Render LoginCard modal when openLogin is true */}
      {openLogin && (
        <LoginCard isOpen={openLogin} onClose={() => setOpenLogin(false)} />
      )}
    </div>
  );
}
