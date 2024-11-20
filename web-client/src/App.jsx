import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/common/home.jsx";
import { NavigationBar } from "./components/navigation_bar.jsx";
import { Toaster } from "react-hot-toast";
import { PrivateRoute } from "./pages/privateRoute.jsx";
import { TeacherDashboard } from "@pages/teacher/teacher_dashboard.jsx";
import { Footer } from "./components/footer.jsx";
import { StudentDashboard } from "@pages/student/student_dashboard.jsx";
import { useLocation } from "react-router-dom";

const App = () => {
    return (
        <BrowserRouter>
            <AppContent />
        </BrowserRouter>
    );
};

const AppContent = () => {
    const location = useLocation();

    const hideHeaderFooter =
        location.pathname === "/teacher-dashboard" || location.pathname === "/student-dashboard";

    return (
        <>
            <Toaster position="top-center" reverseOrder={true} />
            {!hideHeaderFooter && <NavigationBar />}
            <Routes>
                <Route path="/" element={<Home />} />
                <Route element={<PrivateRoute />}>
                    <Route path="/teacher-dashboard" element={<TeacherDashboard />} />
                    <Route path="/student-dashboard" element={<StudentDashboard />} />
                </Route>
            </Routes>
            {!hideHeaderFooter && <Footer />}
        </>
    );
};

export default App;
