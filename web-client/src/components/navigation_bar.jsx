import {IconButton, Input, Typography} from "@material-tailwind/react";
import {UilSearch, UilSignin, UilSignInAlt} from "@iconscout/react-unicons";
import { COLORS } from "@config/colors.js";
import logo from "@assets/images/logo.png";
import { APP_CONFIG } from "@config/constants.js";

export const NavigationBar = () => {
    return (
        <div className="w-full p-4 flex justify-between items-center flex-wrap sm:flex-nowrap">
            {/* Logo and App Name */}
            <div className="flex items-center gap-2 mb-4 sm:mb-0">
                <img src={logo} alt="logo-img" className="w-8 h-8 object-cover sm:w-10 sm:h-10" />
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
            {/*<IconButton size={"sm"} variant={"outlined"} className={'border-primary border-2 rounded-full '}>*/}
            {/*    <UilSignInAlt  size={20} color={COLORS.primary} />*/}
            {/*</IconButton>*/}

             {/*Auth Options*/}
            <div className="bg-secondary px-6 py-2 rounded-full">
                <Typography className="text-white text-sm">
                    Login
                </Typography>
            </div>
        </div>
    );
};

const styles = {
    pages: "text-secondary text-sm hover:text-primary hover:font-bold cursor-pointer",
};
