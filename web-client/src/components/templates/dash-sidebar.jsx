import {Avatar, Card, Typography} from "@material-tailwind/react";
import profileImage from "@assets/images/teacher-default.jpg";
import {getUser} from "@utils/cookie_utils.js";

export const DashSidebar = () => {
    const user = getUser();
    console.log(user); //TODO: Should get user details
    return(
        <div>
            sidebar
        </div>
    )
}