import {
  LucideArrowUp,
  LucideBell,
  LucideGlobe,
  LucideSearch,
  LucideTag,
} from "lucide-react";
import { Avatar, Typography } from "@material-tailwind/react";
import profileImage from "@assets/images/teacher-default.jpg";

const navLinks = [
  {
    title: "Help Center",
    path: "/",
    icon: <LucideGlobe size={16} className={" ml-4 text-primaryDark"} />,
  },
  {
    title: "My BookMarks",
    path: "/bookmarks",
    icon: <LucideTag size={16} className={" ml-4 text-primaryDark"} />,
  },
  {
    title: "Upgrade",
    path: "/services",
    icon: <LucideArrowUp size={16} className={" ml-4 text-primaryDark"} />,
  },
];

export const DashNavbar = () => {
  return (
    <div className="pl-2 bg-primaryLight h-14 w-full flex justify-between items-center">
      {/* Navigation Links */}
      <div className="flex gap-4">
        <div className="flex gap-4  sm:flex">
          {navLinks.map((link, index) => (
            <div key={index} className="flex items-center gap-2">
              {link.icon}
              <Typography className="text-primaryDark text-sm">
                {link.title}
              </Typography>
            </div>
          ))}
        </div>
      </div>

      {/* Search, Notifications, and Profile */}
      <div className="flex gap-5 mr-4 items-center">
        {/* Search Icon */}
        <div>
          <LucideSearch size={16} className="text-primaryDark font-semibold" />
        </div>

        {/* Notification Icon */}
        <div>
          <LucideBell size={16} className="text-primaryDark" />
        </div>

        {/* Avatar */}
        <div>
          <Avatar src={profileImage} size="xs" color="lightBlue" />
        </div>
      </div>
    </div>
  );
};
