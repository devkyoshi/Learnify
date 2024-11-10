import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/common/home.jsx";
import { NavigationBar } from "./components/navigation_bar.jsx";
import { Toaster } from "react-hot-toast";
import { PrivateRoute } from "./pages/privateRoute.jsx";
import { TeacherProfile } from "./pages/teacherProfile.jsx";
import { Footer } from "./components/footer.jsx";

const App = () => {
  return (
    <BrowserRouter>
      <Toaster position="top-center" reverseOrder={true} />
      <NavigationBar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route element={<PrivateRoute />}>
          <Route path="/profile" element={<TeacherProfile />} />
        </Route>
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default App;
