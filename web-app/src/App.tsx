import { Toaster } from "@/components/ui/toaster.tsx";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Home } from "@/pages/home.tsx";
import PrivateRoute from "@/config/private-route.tsx";
import { DashboardTeacher } from "@/pages/teacher/dash-teacher.tsx";

function App() {
  return (
    <>
      <Toaster />
      <BrowserRouter>
        <Routes>
          <Route path={"/"} element={<Home />} />
          <Route element={<PrivateRoute />}>
            <Route path={"/dash-teacher"} element={<DashboardTeacher />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
