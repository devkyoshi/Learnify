import FormDialogTemplate from "@components/templates/form-dialog";
import student from "@assets/images/student-default.jpg";
import teacher from "@assets/images/teacher-default.jpg";

import propTypes from "prop-types";
import {
  Avatar,
  Button,
  Card,
  Input,
  Step,
  Stepper,
  Typography,
} from "@material-tailwind/react";
import { useState } from "react";
import {
  registerStudent,
  registerTeacher,
} from "@/controllers/auth.controller.js";
import toast from "react-hot-toast";

export default function SignUpCard({ isOpen, onClose }) {
  const nextStep = () =>
    setCurrentStep((prev) => Math.min(prev + 1, steps.length - 1));
  const prevStep = () => setCurrentStep((prev) => Math.max(prev - 1, 0));

  const [currentStep, setCurrentStep] = useState(0);
  const [userType, setUserType] = useState("");
  const [registrationData, setRegistrationData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    address: "",
    city: "",
    district: "",
    zip: "",
    username: "",
    password: "",
    confirmPassword: "",
  });

  const checkUserDetails = () => {
    return (
      registrationData.firstName &&
      registrationData.lastName &&
      registrationData.email &&
      registrationData.phone &&
      registrationData.address &&
      registrationData.city &&
      registrationData.district &&
      registrationData.zip &&
      registrationData.username &&
      registrationData.password
    );
  };

  const handleUserRegistration = async () => {
    if (!checkUserDetails()) {
      toast.error("Please fill all the fields");
      return;
    }

    if (registrationData.password !== registrationData.confirmPassword) {
      toast.error("Passwords do not match");
      return;
    }

    const response = await registerUserByType(userType, registrationData);
    if (response) {
      onClose();
    }
  };

  const registerUserByType = async (userType, registrationData) => {
    console.log("Submitted Data:", registrationData);
    if (userType === "student") {
      return await registerStudent(registrationData);
    } else if (userType === "teacher") {
      return await registerTeacher(registrationData);
    }
    return null;
  };

  const handleInputChange = (e) => {
    setRegistrationData({
      ...registrationData,
      [e.target.name]: e.target.value,
    });
    console.log(registrationData);
  };

  const steps = [
    <UserSelection
      key={"user-info"}
      selectUserType={setUserType}
      next={nextStep}
    />,
    <PersonalInfo
      key={"personal-info"}
      registrationData={registrationData}
      inputChangeFunction={handleInputChange}
      next={nextStep}
    />,
    <Submission
      key={"submission"}
      registrationData={registrationData}
      register={handleUserRegistration}
      inputChangeFunction={handleInputChange}
    />,
  ];

  return (
    <FormDialogTemplate isOpen={isOpen} onClose={onClose}>
      <div className="flex flex-col h-full mt-10">
        {/* Stepper */}
        <div className="px-8 py-4">
          <StepperSlide
            steps={steps.length}
            activeStep={currentStep}
            onStepClick={setCurrentStep}
          />
        </div>
        {/* Fixed Size Container for Step Content */}
        <div className="flex-grow p-4 overflow-auto flex justify-center items-center">
          <div className="w-[850px] h-[300px] flex justify-center items-center">
            {steps[currentStep]}
          </div>
        </div>
        {currentStep !== 0 && (
          <hr className="w-full border-t border-gray-300 " />
        )}
        {/* Navigation Buttons */}
        <div className="flex justify-between p-4">
          <div className="h-10">
            {currentStep !== 0 && <Button onClick={prevStep}>Back</Button>}
          </div>
          <div className="h-10">
            {currentStep < steps.length - 1 && currentStep !== 0 && (
              <Button onClick={nextStep}>Next</Button>
            )}
          </div>
        </div>
      </div>
    </FormDialogTemplate>
  );
}

const UserSelection = ({ selectUserType, next }) => {
  const handleUserSelection = (userType) => {
    selectUserType(userType);
    next();
  };
  return (
    <div className="text-center ">
      <Typography className="text-secondary" variant="h4">
        Who are you?
      </Typography>
      <div className="grid grid-cols-2 gap-4 justify-center mt-5">
        <Card
          onClick={() => handleUserSelection("student")}
          className="hover:animate-pulse flex flex-col items-center p-4 w-52 border-2 group hover:border-primary hover:border-2 cursor-pointer hover:bg-primary hover:bg-opacity-10"
        >
          <Avatar alt="Student Avatar" size={"xxl"} src={student} />
          <Typography
            className={"text-secondary mt-5 group-hover:text-primary"}
            variant="h6"
          >
            Student
          </Typography>
        </Card>
        <Card
          onClick={() => handleUserSelection("teacher")}
          className="hover:animate-pulse flex flex-col items-center p-4 w-52 border-2 group hover:border-primary hover:border-2 cursor-pointer hover:bg-primary hover:bg-opacity-10"
        >
          <Avatar alt="Teacher Avatar" size={"xxl"} src={teacher} />
          <Typography
            className={"text-secondary mt-5 group-hover:text-primary"}
            variant="h6"
          >
            Teacher
          </Typography>
        </Card>
      </div>
    </div>
  );
};
const PersonalInfo = ({ inputChangeFunction, registrationData }) => {
  return (
    <div className="w-full h-full">
      <Typography className="text-secondary" variant="h4">
        Mind Sharing a bit about yourself?
      </Typography>
      <div className=" mt-5">
        <Typography className={"text-secondary text-sm mb-2"}>
          Your Name
        </Typography>
        <div className={"grid grid-cols-2 gap-2"}>
          <Input
            value={registrationData.firstName}
            onChange={inputChangeFunction}
            name={"firstName"}
            label={"First Name"}
          />
          <Input
            value={registrationData.lastName}
            onChange={inputChangeFunction}
            name={"lastName"}
            label={"Last Name"}
          />
        </div>
        <Typography className={"text-secondary text-sm  mb-2 mt-2"}>
          Address
        </Typography>
        <Input
          value={registrationData.address}
          onChange={inputChangeFunction}
          name={"address"}
          label={"Address"}
        />
        <div className={"grid grid-cols-3 gap-2 mt-2"}>
          <Input
            value={registrationData.district}
            onChange={inputChangeFunction}
            name={"district"}
            label={"District"}
          />
          <Input
            value={registrationData.city}
            onChange={inputChangeFunction}
            name={"city"}
            label={"City"}
          />
          <Input
            value={registrationData.zip}
            onChange={inputChangeFunction}
            name={"zip"}
            label={"Zip Code"}
          />
        </div>
        <Typography className={"text-secondary text-sm  mb-2 mt-2"}>
          How can we contact you?
        </Typography>
        <div className={"grid grid-cols-2 gap-2"}>
          <Input
            value={registrationData.email}
            onChange={inputChangeFunction}
            name={"email"}
            label={"Email"}
          />
          <Input
            value={registrationData.phone}
            onChange={inputChangeFunction}
            name={"phone"}
            label={"Mobile Number"}
          />
        </div>
      </div>
    </div>
  );
};

const Submission = ({ inputChangeFunction, register, registrationData }) => {
  return (
    <div className="w-full ">
      <Typography className="text-secondary" variant="h4">
        Almost There...
      </Typography>
      <Typography className={"text-secondary text-sm"}>
        Enter a user name and a password
      </Typography>
      <div className=" mt-5">
        <Typography className={"text-secondary text-sm mb-2"}>
          User Name
        </Typography>
        <Input
          value={registrationData.username}
          onChange={inputChangeFunction}
          name={"username"}
          label={"User Name"}
        />

        <Typography className={"text-secondary text-sm  mb-2 mt-2"}>
          Password
        </Typography>
        <div className={"grid grid-cols-2 gap-2"}>
          <Input
            value={registrationData.password}
            type={"password"}
            onChange={inputChangeFunction}
            name={"password"}
            label={"Password"}
          />
          <Input
            value={registrationData.confirmPassword}
            onChange={inputChangeFunction}
            type={"password"}
            label={"Re-Enter Password"}
          />
        </div>
      </div>

      <div className={"mt-10 flex items-center justify-center"}>
        <Button onClick={register} className={"bg-primary w-64"}>
          Register
        </Button>
      </div>
    </div>
  );
};

const StepperSlide = ({ steps, activeStep, onStepClick }) => {
  return (
    <Stepper activeStep={activeStep}>
        {Array.from({ length: steps}, (_, index) => (
        <Step
          key={index}
          className="h-4 w-4"
          onClick={() => onStepClick(index)}
        />
      ))}
    </Stepper>
  );
};

SignUpCard.propTypes = {
  isOpen: propTypes.bool.isRequired,
  onClose: propTypes.func.isRequired,
};

UserSelection.propTypes = {
  selectUserType: propTypes.func.isRequired,
  next: propTypes.func.isRequired,
};

PersonalInfo.propTypes = {
  next: propTypes.func.isRequired,
  inputChangeFunction: propTypes.func.isRequired,
  registrationData: propTypes.object.isRequired,
};

Submission.propTypes = {
  inputChangeFunction: propTypes.func.isRequired,
  register: propTypes.func.isRequired,
  registrationData: propTypes.object.isRequired,
};

StepperSlide.propTypes = {
  steps: propTypes.number.isRequired,
  activeStep: propTypes.number.isRequired,
  onStepClick: propTypes.func.isRequired,
};
