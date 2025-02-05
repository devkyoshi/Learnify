import { Separator } from "@/components/ui/separator.tsx";
import logoImage from "@/assets/images/learnify_logo.png";
import { LoginForm } from "@/components/login-form.tsx";
import React from "react";
import { ModeToggle } from "@/components/ui/theme-toggle.tsx";

const navItems = ["Home", "About Us"];
export const NavigationBar = () => {
  return (
    <header className="flex sticky justify-between top-0 bg-background h-12 shrink-0 items-center gap-2 border-b px-2">
      <div className="h-8 w-8">
        <img
          src={logoImage}
          alt={"app-logo"}
          className="h-full w-full object-contain"
        />
      </div>
      <div className={"flex flex-row items-center"}>
        {navItems.map((item, index) => (
          <React.Fragment key={index}>
            <p className={"text-sm "}>{item}</p>
            {index < navItems.length - 1 && (
              <Separator orientation="vertical" className="mx-2 h-4" />
            )}
          </React.Fragment>
        ))}
      </div>
      <div className={"flex flex-row gap-2 items-center"}>
        <div className={"text-sm hidden sm:block"}>
          New to this platform?{" "}
          <span
            className={
              "font-semibold text-primary dark:hover:text-primary-foreground cursor-pointer hover:text-black"
            }
          >
            Sign Up
          </span>
        </div>
        <div className={"text-sm sm:hidden"}>
          <span
            className={
              "font-semibold text-primary dark:hover:text-primary-foreground cursor-pointer hover:text-black"
            }
          >
            Sign Up
          </span>
        </div>
        <Separator orientation="vertical" className="mx-2 h-4" />
        <LoginForm />
        <ModeToggle />
      </div>
    </header>
  );
};
