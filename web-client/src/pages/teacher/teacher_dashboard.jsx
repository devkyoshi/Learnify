import { DashSidebar } from "@components/templates/dash-sidebar.jsx";
import { DashNavbar } from "@components/templates/dash-navbar.jsx";
import { useState } from "react";
import { CoursesTab } from "@pages/teacher/courses-tab.jsx";

export const TeacherDashboard = () => {
  const [selectedTab, setSelectedTab] = useState(1);

  return (
    <div className={"w-screen h-screen flex overflow-hidden"}>
      <DashSidebar tabSelector={setSelectedTab} selectedTab={selectedTab} />
      <div className={"flex-1 w-full "}>
        <DashNavbar />
        {selectedTab === 1 && <div>Dashboard</div>}
        {selectedTab === 4 && <div>Students</div>}
        {selectedTab === 6 && <CoursesTab />}
      </div>
    </div>
  );
};
