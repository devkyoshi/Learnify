import {
  Avatar,
  IconButton,
  Input,
  Typography,
} from "@material-tailwind/react";
import {
  UilSearch,
  UilAngleDown,
  UilUser,
  UilSetting,
  UilSignout,
} from "@iconscout/react-unicons";
import { COLORS } from "@config/colors.js";
import logo from "@assets/images/logo.png";
import { APP_CONFIG } from "@config/constants.js";
import { useState } from "react";
import LoginCard from "@pages/authentication/login";
import { getUser } from "../utils/cookie_utils";
import defaultProfile from "@assets/images/default_profile.jpg";
import { logout } from "../controllers/auth.controller";
import {useNavigate} from "react-router-dom";

const profileMenu = [
  { title: "Dashboard", icon: <UilUser size={20} /> },
  { title: "Settings", icon: <UilSetting size={20} /> },
  { title: "Logout", icon: <UilSignout size={20} /> },
];

export const NavigationBar = () => {
  const navigate = useNavigate();
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);
  const user = getUser();

  const handleLoginOpen = () => {
    setIsLoginOpen(!isLoginOpen);
  };

  const handleLogout = () => {
    logout();
    setIsProfileMenuOpen(false);
  };

  return (
    <div>
      <div className="w-full p-4 flex justify-between items-center flex-wrap sm:flex-nowrap">
        {/* Logo and App Name */}
        <div className="flex items-center gap-2 mb-4 sm:mb-0">
          <img
            src={logo}
            alt="logo-img"
            className="w-8 h-8 object-cover sm:w-10 sm:h-10"
          />
          <Typography className="text-base sm:text-lg font-semibold text-primary">
            {APP_CONFIG.APP_NAME}
          </Typography>
        </div>

        {/* Navigation Links (Hidden on mobile) */}
        <div className="hidden sm:flex flex-row gap-4">
          <Typography className={styles.pages}>Home</Typography>
          <Typography className={styles.pages}>Courses</Typography>
          <Typography className={styles.pages}>Teachers</Typography>
        </div>

        {/* Search Input (Hidden on small screens) */}
        <div className="hidden sm:block">
          <Input
            placeholder="Search"
            containerProps={{
              className: "w-full sm:w-60 bg-gray-100 hover:bg-gray-200",
            }}
            icon={<UilSearch size={15} color={COLORS.primary} />}
          />
        </div>
        {/* User Profile */}
        {user ? (
          <div className="flex items-center gap-2 border-2 p-1 rounded-full ">
            <Avatar size="sm" src={defaultProfile} />

            <IconButton
              size="sm"
              variant="text"
              className={`rounded-full text-secondary ${
                isProfileMenuOpen ? "rotate-180" : ""
              }`}
              onClick={() => setIsProfileMenuOpen(!isProfileMenuOpen)}
            >
              <UilAngleDown size={20} />
            </IconButton>

            {isProfileMenuOpen && (
              <div className="absolute top-16 right-4 w-48 bg-white rounded-lg shadow-lg p-2">
                {profileMenu.map((menu) => (
                  <div
                    onClick={menu.title === "Logout" ? handleLogout
                        : ((menu.title === "Dashboard" && user.role === "TEACHER") ? () => navigate("/teacher-dashboard")
                        : ((menu.title === "Dashboard" && user.role === "STUDENT") ? () => navigate("/student-dashboard") : null))}
                    key={menu.title}
                    className=" flex items-center gap-2 p-2 hover:bg-gray-100 cursor-pointer rounded-lg hover:text-primary text-secondary "
                  >
                    {menu.icon}
                    <Typography className="text-sm ">{menu.title}</Typography>
                  </div>
                ))}
              </div>
            )}
          </div>
        ) : (
          <div
            onClick={handleLoginOpen}
            className="bg-secondary px-6 py-2 rounded-full group"
          >
            <Typography className="text-white text-sm">Login</Typography>
          </div>
        )}
      </div>

      <LoginCard isOpen={isLoginOpen} onClose={handleLoginOpen} />
    </div>
  );
};

const styles = {
  pages:
    "text-secondary text-sm hover:text-primary hover:font-bold cursor-pointer",
};
