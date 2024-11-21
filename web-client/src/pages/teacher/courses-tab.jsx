import {
  Button,
  ButtonGroup,
  Card,
  Input,
  Typography,
} from "@material-tailwind/react";
import {
  LucideEllipsis,
  LucideLayoutGrid,
  LucideLayoutList,
  LucidePlus,
  LucideSearch,
} from "lucide-react";
import { useState } from "react";
import PropTypes from "prop-types";

export const CoursesTab = () => {
  return (
    <div className="p-4 h-full flex flex-col">
      <div>
        <div className="flex flex-col lg:flex-row lg:justify-between lg:items-center gap-4">
          {/* Title */}
          <Typography className="text-primaryDark text-xl lg:text-3xl font-semibold">
            Courses
          </Typography>

          {/* New Course Button */}
          <Button
            size="sm"
            className="flex items-center bg-secondaryDark w-full sm:w-auto"
          >
            <LucidePlus color="white" size={16} className="mr-2" />
            <Typography className="text-xs text-white font-semibold">
              New Course
            </Typography>
          </Button>
        </div>
        {/* Navigation Bar */}
        <CoursesNavBar />
      </div>
      <div className="flex-1 overflow-y-auto mt-6 lg:mt-0">
        <CourseList />
      </div>
    </div>
  );
};

const CoursesNavBar = () => {
  const [selectedTab, setSelectedTab] = useState(1);

  const navList = [
    {
      label: "Courses",
      id: 1,
    },
    {
      label: "Memberships & Bundles",
      id: 2,
    },
  ];

  return (
    <div className="mt-8 flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
      {/* Navigation Tabs */}
      <div className="flex flex-row gap-4 justify-center lg:justify-start">
        {navList.map((item) => (
          <div
            key={item.id}
            onClick={() => setSelectedTab(item.id)}
            className="cursor-pointer pb-2 relative"
          >
            <Typography
              className={`text-sm font-semibold ${
                selectedTab === item.id ? "text-primaryDark" : "text-gray-600"
              }`}
            >
              {item.label}
            </Typography>
            {selectedTab === item.id && (
              <div className="absolute rounded-full bottom-0 left-0 right-0 h-[3px] bg-secondaryDark transition-all" />
            )}
          </div>
        ))}
      </div>

      <div className="flex flex-row gap-2 mt-4 lg:mt-0 pb-4">
        {/* Search Bar */}
        <div className="flex justify-center lg:justify-end w-full lg:w-auto">
          <Input
            label="Search your course..."
            icon={<LucideSearch size={16} className="text-primaryDark" />}
            type="search"
            className="w-full lg:w-auto"
          />
        </div>
        <ButtonGroup size="sm">
          <Button className="bg-primaryDark" color="lightBlue" size="sm">
            <LucideLayoutGrid size={14} className="mr-2" />
          </Button>
          <Button className="bg-primaryDark" color="lightBlue" size="sm">
            <LucideLayoutList size={14} className="mr-2" />
          </Button>
        </ButtonGroup>
      </div>
    </div>
  );
};

const CourseList = () => {
  const courses = [
    {
      id: 1,
      name: "Software Process Model",
      subjectsPublished: 1,
      status: "Published",
    },
    {
      id: 2,
      name: "Introduction to Programming",
      subjectsPublished: 4,
      status: "Published",
    },
    {
      id: 3,
      name: "Computer Networks",
      subjectsPublished: 7,
      status: "Draft",
    },
    {
      id: 4,
      name: "Data Structures and Algorithms",
      subjectsPublished: 4,
      status: "Published",
    },
    {
      id: 5,
      name: "Interactive Media",
      subjectsPublished: 6,
      status: "Draft",
    },
    {
      id: 6,
      name: "User Experience Engineering",
      subjectsPublished: 5,
      status: "Published",
    },
    {
      id: 7,
      name: "Mathematics of Computing",
      subjectsPublished: 1,
      status: "Draft",
    },
  ];

  return (
    <div className="grid grid-cols-1 bg-gray-50 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4 mt-5 w-full h-full p-2 mb-20">
      {courses.map((course) => (
        <CourseCard
          key={course.id}
          courseName={course.name}
          publishedSubjects={course.subjectsPublished}
          status={course.status}
        />
      ))}
    </div>
  );
};

const CourseCard = ({ courseName, publishedSubjects, status }) => {
  return (
    <div>
      <Card className="max-w-[15rem] h-[18rem] overflow-hidden flex flex-col justify-between">
        <img
          src="https://img.freepik.com/free-vector/hand-drawn-flat-design-literature-illustration_52683-81526.jpg?t=st=1732200121~exp=1732203721~hmac=e8f669923cfc38501df9a9a04a08b15e63a9aa52ec1c12b1a807809aed09b640&w=900"
          alt="ui/ux review check"
          className="w-full h-2/3 object-cover"
        />

        <div className="p-4 flex flex-col justify-between flex-grow">
          <Typography className="text-primaryDark font-semibold text-sm text-center">
            {courseName}
          </Typography>

          <div className="flex flex-row justify-between mt-4">
            <div className="flex flex-row gap-2">
              <Typography className="text-primaryDark text-xs ">
                <span className="font-semibold">{publishedSubjects} </span>
                Courses
              </Typography>
              <Typography
                className={`text-primaryDark text-xs ${
                  status === "Published" && "text-secondaryDark"
                }`}
              >
                {status}
              </Typography>
            </div>
            <LucideEllipsis size={16} className="text-primaryDark" />
          </div>
        </div>
      </Card>
    </div>
  );
};

CourseCard.propTypes = {
  courseName: PropTypes.string.isRequired,
  publishedSubjects: PropTypes.number.isRequired,
  status: PropTypes.string.isRequired,
};

CoursesTab.propTypes = {
  courseName: PropTypes.string.isRequired,
  publishedSubjects: PropTypes.number.isRequired,
  status: PropTypes.string.isRequired,
};
