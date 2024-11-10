import { Typography } from "@material-tailwind/react";
import logo from "@assets/images/logo.png";
import React from "react";

export const Footer = () => {
  const currentYear = new Date().getFullYear();
  return (
    <div className="w-full text-secondary border-t text-center p-4 gap-2 justify-center flex items-center">
      <div className="flex items-center">
        <img src={logo} className="w-5 h-5" />
        <Typography className="text-sm text-secondary">
          <strong className="text-primary font-semibold">Learnify</strong> |
          Created By{" "}
          <strong className="text-primary font-semibold">
            {" "}
            Ashan Tharindu
          </strong>{" "}
          |
        </Typography>
      </div>
      <Typography className="text-sm text-secondary">
        ©{currentYear} All rights reserved
      </Typography>
    </div>
  );
};
