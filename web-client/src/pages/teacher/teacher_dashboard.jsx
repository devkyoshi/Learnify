import {DashSidebar} from "@components/templates/dash-sidebar.jsx";
import {DashNavbar} from "@components/templates/dash-navbar.jsx";

export const TeacherDashboard = () => {
  return(
      <div className={'w-screen '}>
        <div >
            <DashNavbar/>
          <DashSidebar/>
        </div>
      </div>
  );
};
