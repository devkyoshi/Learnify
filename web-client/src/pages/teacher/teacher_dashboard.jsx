import {DashSidebar} from "@components/templates/dash-sidebar.jsx";
import {DashNavbar} from "@components/templates/dash-navbar.jsx";
import {useState} from "react";

export const TeacherDashboard = () => {

    const [selectedTab, setSelectedTab] = useState(1);



  return(
      <div className={'w-screen h-screen flex'}>
         <DashSidebar tabSelector={setSelectedTab} selectedTab={selectedTab}/>
          <div className={'flex-1 w-full px-2'}>
              <DashNavbar/>
              {selectedTab === 1 && <div>Dashboard</div>}
                {selectedTab === 2 && <div>Students</div>}
          </div>
      </div>
  );
};
