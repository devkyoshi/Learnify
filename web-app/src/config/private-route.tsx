import { Navigate, Outlet } from "react-router-dom";
import useAuthStore from "@/stores/auth-store.ts";

const PrivateRoute = () => {
  const { isLoggedIn } = useAuthStore();
  if (!isLoggedIn) {
    return <Navigate to="/" />;
  }
  return <Outlet />;
};

export default PrivateRoute;
