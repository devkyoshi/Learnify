import {
  LucideBook,
  LucideChevronsLeft,
  LucideChevronsRight,
  LucideSettings,
  LucideUsers,
  LucideChevronDown,
  LucideChevronUp,
} from "lucide-react";
import logo from "@assets/images/logo.png";
import { useState } from "react";
import { Typography } from "@material-tailwind/react";
import { APP_CONFIG } from "@config/constants.js";
import PropTypes from "prop-types";

const mainNavigationItems = [
  {
    id: 1,
    label: "Learning Content",
    icon: LucideBook,
    subItems: [
      { id: 4, label: "Video Libraries" },
      { id: 5, label: "Tutorials" },
      { id: 6, label: "Courses" },
    ],
  },
  { id: 2, label: "Students", icon: LucideUsers },
];

const footerNavigationItems = [
  { id: 3, label: "Settings", icon: LucideSettings, path: "/settings" },
];

export const DashSidebar = ({ tabSelector, selectedTab }) => {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [expandedMenu, setExpandedMenu] = useState(null);

  const handleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const handleExpand = (id) => {
    setExpandedMenu((prev) => (prev === id ? null : id));
  };

  return (
    <div
      className={`bg-primaryDark h-full flex flex-col relative transition-all duration-300 ${
        sidebarOpen ? "w-64" : "w-16 items-center"
      }`}
    >
      {/* Logo Section */}
      <div
        className={`flex p-4 ${
          sidebarOpen ? "items-center gap-2" : "items-center justify-center"
        }`}
      >
        <img src={logo} alt="logo" className="w-8 h-8 object-cover" />
        {sidebarOpen && (
          <Typography className="text-white font-semibold text-lg">
            {APP_CONFIG.APP_NAME}
          </Typography>
        )}
      </div>

      {/* Main Navigation */}
      <div className="flex flex-col flex-1 gap-4 mt-5 px-2 relative">
        {mainNavigationItems.map((item) => {
          const Icon = item.icon;
          const isExpanded = expandedMenu === item.id;

          return (
            <div key={item.id} className="relative w-full">
              {/* Indicator */}
              {selectedTab === item.id && sidebarOpen && (
                <span className="absolute left-0 top-0 bottom-0 w-1 bg-secondaryDark rounded transition-all" />
              )}
              <div
                onClick={() => {
                  if (item.subItems) handleExpand(item.id);
                  else tabSelector(item.id);
                }}
                className={`flex hover:bg-primaryLight p-2 hover:bg-opacity-5 rounded-lg cursor-pointer w-full items-center ${
                  !sidebarOpen &&
                  selectedTab === item.id &&
                  "border-2 border-secondaryDark"
                } ${sidebarOpen ? "gap-2 pl-4" : "justify-center"}`}
              >
                <Icon
                  size={16}
                  className={`text-white ${
                    selectedTab === item.id && "text-secondaryDark"
                  }`}
                />
                {sidebarOpen && (
                  <>
                    <Typography className="text-white font-semibold text-sm">
                      {item.label}
                    </Typography>
                    {item.subItems && (
                      <div className="ml-auto">
                        {isExpanded ? (
                          <LucideChevronUp className="text-white" />
                        ) : (
                          <LucideChevronDown className="text-white" />
                        )}
                      </div>
                    )}
                  </>
                )}
              </div>

              {/* Sub-navigation */}
              {isExpanded && sidebarOpen && item.subItems && (
                <div className="pl-8 flex flex-col gap-2">
                  {item.subItems.map((subItem) => (
                    <div key={subItem.id} className="relative w-full">
                      {/* Indicator for Subtopics */}
                      {selectedTab === subItem.id && (
                        <span className="absolute -left-3 top-1 bottom-1 w-1 bg-secondaryDark rounded transition-all" />
                      )}
                      <div
                        onClick={() => tabSelector(subItem.id)}
                        className={`flex hover:bg-primaryLight p-2 hover:bg-opacity-5 rounded-lg cursor-pointer w-full items-center 
                                      `}
                      >
                        <Typography
                          className={`text-white text-sm ${
                            selectedTab === subItem.id && "text-secondaryDark"
                          }`}
                        >
                          {subItem.label}
                        </Typography>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          );
        })}
      </div>

      {/* Footer Navigation */}
      <div className="flex flex-col gap-4 mb-4 px-2">
        {footerNavigationItems.map((item) => {
          const Icon = item.icon;
          return (
            <div key={item.id} className="relative w-full">
              {/* Indicator */}
              {selectedTab === item.id && sidebarOpen && (
                <span className="absolute left-1 top-1 bottom-0 w-1 bg-secondaryDark rounded transition-all" />
              )}
              <div
                onClick={() => tabSelector(item.id)}
                className={`flex hover:bg-primaryLight p-2 hover:bg-opacity-5 rounded-lg cursor-pointer w-full items-center ${
                  !sidebarOpen &&
                  selectedTab === item.id &&
                  "border-2 border-secondaryDark"
                } ${sidebarOpen ? "gap-2 pl-4" : "justify-center"}`}
              >
                <Icon
                  size={16}
                  className={`${
                    selectedTab === item.id && "text-secondaryDark"
                  } text-white`}
                />
                {sidebarOpen && (
                  <Typography className="text-white font-semibold text-sm">
                    {item.label}
                  </Typography>
                )}
              </div>
            </div>
          );
        })}
      </div>

      {/* Toggle Button */}
      <div
        onClick={handleSidebar}
        className={`absolute top-2 right-1  transform translate-x-1/2 bg-primaryDark p-2 rounded-full cursor-pointer`}
      >
        {sidebarOpen ? (
          <LucideChevronsLeft className="text-white hover:text-secondaryDark" />
        ) : (
          <LucideChevronsRight className="text-white hover:text-secondaryDark" />
        )}
      </div>
    </div>
  );
};

DashSidebar.propTypes = {
  tabSelector: PropTypes.func.isRequired,
  selectedTab: PropTypes.number.isRequired,
};
