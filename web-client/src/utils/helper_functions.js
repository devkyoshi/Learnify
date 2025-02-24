import toast from "react-hot-toast";

import axios from "axios";
import {ERROR_MESSAGES} from "@config/constants.js";

const cloudName = import.meta.env.VITE_REACT_CLOUDINARY_CLOUD_NAME;

// Function to upload image to Cloudinary
export const uploadImageToCloudinary = async (file) => {
    const cloudinaryURL = `https://api.cloudinary.com/v1_1/${cloudName}/image/upload`;
    const formData = new FormData();

    formData.append("file", file);
    formData.append("upload_preset", "react-learnify");

    try {
        const response = await axios.post(cloudinaryURL, formData);
        return response.data.secure_url;
    } catch (error) {
        toast.error(ERROR_MESSAGES.UPLOAD_IMAGE_ERROR, {
            style: {
                borderRadius: "10px",
                background: "#333",
                color: "#fff",
            }
        });
        console.error("Cloudinary upload error:", error);
        return null;
    }
};
// Function to create a toast notifications
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
