import {
    LucideBook,
    LucideChevronsLeft,
    LucideChevronsRight,
    LucideSettings,
    LucideUsers
} from "lucide-react";
import logo from "@assets/images/logo.png";
import { useState } from "react";
import { Typography } from "@material-tailwind/react";
import { APP_CONFIG } from "@config/constants.js";
import PropTypes from "prop-types";

const mainNavigationItems = [
    { id: 1, label: "Learning Content", icon: LucideBook },
    { id: 2, label: "Students", icon: LucideUsers },
];

const footerNavigationItems = [
    { id: 2, label: "Settings", icon: LucideSettings, path: "/settings" },
];

export const DashSidebar = ({tabSelector, selectedTab}) => {
    const [sidebarOpen, setSidebarOpen] = useState(false);

    const handleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
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
            <div className="flex flex-col flex-1 gap-4 mt-5 px-2">
                {mainNavigationItems.map((item) => {
                    const Icon = item.icon;
                    return (
                        <div
                            key={item.id}
                            onClick={() => tabSelector(item.id)}
                            className={`flex hover:bg-primaryLight p-2 hover:bg-opacity-5 rounded-lg cursor-pointer w-full items-center ${selectedTab === item.id && 'bg-white bg-opacity-10 border border-secondaryDark text-secondaryDark'} ${
                                sidebarOpen ? "gap-2 pl-4" : "justify-center"
                            }`}
                        >
                            <Icon size={16} className={`text-white ${selectedTab === item.id && 'text-secondaryDark'}`} />
                            {sidebarOpen && (
                                <Typography className="text-white font-semibold text-sm">
                                    {item.label}
                                </Typography>
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
                        <div
                            key={item.id}
                            onClick={() => tabSelector(item.id)}
                            className={`flex hover:bg-primaryLight p-2 hover:bg-opacity-5 rounded-lg cursor-pointer w-full items-center ${selectedTab === item.id && 'bg-white bg-opacity-10 border border-secondaryDark text-secondaryDark'} ${
                                sidebarOpen ? "gap-2 pl-4" : "justify-center"
                            }`}
                        >
                            <Icon size={16} className={`${selectedTab === item.id && 'text-secondaryDark'} text-white`} />
                            {sidebarOpen && (
                                <Typography className="text-white font-semibold text-sm">
                                    {item.label}
                                </Typography>
                            )}
                        </div>
                    );
                })}
            </div>

            {/* Toggle Button */}
            <div
                onClick={handleSidebar}
                className={`absolute top-2 right-0 transform translate-x-1/2 bg-primaryDark p-2 rounded-full cursor-pointer`}
            >
                {sidebarOpen ? (
                    <LucideChevronsLeft className="text-white" />
                ) : (
                    <LucideChevronsRight className="text-white" />
                )}
            </div>
        </div>
    );
};


DashSidebar.propTypes = {
    tabSelector: PropTypes.func.isRequired,
    selectedTab: PropTypes.number.isRequired
};