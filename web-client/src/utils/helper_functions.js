import toast from "react-hot-toast";

export const createToast = async (apiCall, { loading, success, error }) => {
  try {
    const response = await toast.promise(
      apiCall,
      {
        loading: loading || "Loading...",
        success: success || "Success!",
        error: error || "An error occurred",
      },
      {
        style: {
          borderRadius: "10px",
          background: "#333",
          color: "#fff",
        },
      }
    );

    return response.data;
  } catch (err) {
    console.error("Toast error:", err);
    throw err;
  }
};
