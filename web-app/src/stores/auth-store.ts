import {create} from "zustand";
import {persist} from "zustand/middleware";

type AuthState = {
    isLoggedIn: boolean;
    currentUser: { token: string; role: string; userId: string; email: string ; username:string, firstName:string, lastName:string} | null;
    loginUser: (user: { token: string; role: string; userId: string; email: string; username: string; firstName:string, lastName:string }) => void;
    logoutUser: () => void;
}

const useAuthStore = create(
    persist<AuthState>(
        (set) => ({
            isLoggedIn: false,
            currentUser: null,
            loginUser: (user) => set({ isLoggedIn: true, currentUser: user }),
            logoutUser: () => {
                //clear cookies
                document.cookie = "jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

                // Clear the local storage
                set({isLoggedIn: false, currentUser: null})

            }
        }),
        {
            name: 'learnify-auth',
        }
    )


);

export default useAuthStore;
