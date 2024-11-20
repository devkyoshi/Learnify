import {LucideArrowUp, LucideBell, LucideGlobe, LucideSearch, LucideTag} from "lucide-react";
import {Avatar, Typography} from "@material-tailwind/react";
import profileImage from "@assets/images/teacher-default.jpg";

const navLinks = [
    {title: 'Help Center', path: '/' , icon: <LucideGlobe size={16} className={' ml-4 text-primaryDark'}/>},
    {title: 'My BookMarks', path: '/bookmarks', icon: <LucideTag size={16} className={' ml-4 text-primaryDark'}/>},
    {title: 'Upgrade', path: '/services', icon: <LucideArrowUp size={16} className={' ml-4 text-primaryDark'}/>}
]

export const DashNavbar = () => {
    return (
        <div className={'ml-2 bg-primaryLight h-14 w-full flex justify-between items-center'}>
            <div className={'flex gap-4'}>
                <div className={'flex gap-4'}>
                    {navLinks.map((link, index) => (
                        <div key={index} className={'flex items-center gap-2'}>
                            {link.icon}
                            <Typography className={'text-primaryDark text-sm'}>{link.title}</Typography>
                        </div>
                    ))}
                </div>
            </div>
            <div className={'flex gap-5 mr-4 items-center'}>
               <LucideSearch size={16} className={'text-primaryDark font-semibold'}/>
                <LucideBell size={16} className={'text-primaryDark'}/>
                <Avatar  src={profileImage} size="sm" color="lightBlue"/>
            </div>
        </div>
    )
}