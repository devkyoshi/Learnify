import { getUser } from "../utils/cookie_utils";
import { Navigate, Outlet } from "react-router-dom";

export const PrivateRoute = () => {
  const user = getUser();
  if (!user) {
    return <Navigate to="/" />;
  } else {
    return <Outlet />;
  }
};
