import { useState } from "react";
import { uploadImageToCloudinary } from "@utils/helper_functions.js";
import { Input } from "@material-tailwind/react";

const ImageUploader = () => {
  const [imageUrl, setImageUrl] = useState("");

  const handleFileChange = async (event) => {
    const file = event.target.files[0];
    if (!file) return;

    // Call the upload function
    const uploadedUrl = await uploadImageToCloudinary(file);

    if (uploadedUrl) {
      setImageUrl(uploadedUrl); // Save the image URL
      console.log("Uploaded Image URL:", uploadedUrl);
    }
  };

  return (
    <div>
      <Input type="file" accept="image/*" onChange={handleFileChange} />
      {imageUrl && (
        <div>
          <p>Uploaded Image:</p>
          <img src={imageUrl} alt="Uploaded" style={{ width: "300px" }} />
        </div>
      )}
    </div>
  );
};

export default ImageUploader;
