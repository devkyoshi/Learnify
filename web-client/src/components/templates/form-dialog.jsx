import { UilMultiply } from "@iconscout/react-unicons";
import { Card, IconButton } from "@material-tailwind/react";
import propTypes from "prop-types";

export default function FormDialogTemplate({ isOpen, onClose, children }) {
  const handleClickOutside = (e) => {
    if (e.target.id === "overlay") {
      onClose();
    }
  };

  return (
    <>
      {isOpen && (
        <div
          id="overlay"
          onClick={handleClickOutside}
          className={`fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 z-50 
            ${
              isOpen ? "opacity-100" : "opacity-0"
            } transition-opacity duration-300 ease-in-out`}
        >
          <Card
            onClick={(e) => e.stopPropagation()}
            className={`max-w-screen-lg max-h-screen p-8 relative transform transition-transform duration-300 ease-in-out 
              ${isOpen ? "scale-100 opacity-100" : "scale-95 opacity-0"}`}
          >
            <IconButton variant="text" onClick={onClose} className="-mb-8 ">
              <UilMultiply size={20} className="text-secondary" />
            </IconButton>
            {children}
          </Card>
        </div>
      )}
    </>
  );
}


FormDialogTemplate.propTypes = {
    isOpen: propTypes.bool.isRequired,
    onClose: propTypes.func.isRequired,
    children: propTypes.node.isRequired,
}