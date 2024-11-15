import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Typography,
} from "@material-tailwind/react";
import PropTypes from "prop-types";


export const Home = () => {
  return (
    <div className={"w-full"}>
      {/* Hero section */}
      <div className="flex flex-col items-center justify-center pt-20 pb-10 bg-gray-100 px-4">
        <Typography className="text-primary text-4xl md:text-5xl font-bold text-center">
          Empowering Lifelong Learning
        </Typography>

        <Typography className="text-secondary text-xl md:text-2xl mt-4 text-center font-semibold">
          &quot;For Every Student From Anywhere&quot;
        </Typography>

        <Typography className="mt-2  text-sm text-center max-w-lg mx-auto text-secondary md:text-sm lg:text-sm">
          Discover a world of opportunities with Learnify. Whether you&#39;re here
          to learn new skills or inspire others, our platform provides the
          resources, guidance, and community you need to succeed. Join us and
          take the next step toward reaching your goals.
        </Typography>

        <Button className="mt-5 rounded-full bg-primary md:px-8 px-8 py-2 text-white text-sm md:text-base">
          Start learning
        </Button>
      </div>

      <div className="flex flex-col md:flex-row gap-8 mt-5 p-4 items-center justify-center">
        {/* Student Card */}
        <JoinUsCards
          button={"Join as a Student"}
          topic={"Unlock Your Potential with Learnify"}
          details={`Join Learnify to gain knowledge through structured lessons and hands-on sessions. Connect with mentors, access resources, and reach new heights in your learning journey!`}
          image={
            "https://img.freepik.com/premium-photo/photo-3d-illustration-cartoon-teenage-girl-generative-ai_742418-8865.jpg?w=740"
          }
        />

        {/* Teacher Card */}
        <JoinUsCards
          button={"Join as a Teacher"}
          topic={"Inspire and Educate with Learnify"}
          details={`Become part of Learnify’s community and share your expertise with eager learners. Gain access to tools for effective teaching and support students in achieving their goals.`}
          image={
            "https://img.freepik.com/premium-vector/professional-teacher-woman-3d-cartoon-vector_1338461-2739.jpg?w=740"
          }
        />
      </div>
    </div>
  );
};

const JoinUsCards = ({ image, topic, details, button }) => {
  return (
    <Card className="mt-6 w-96">
      <CardHeader
        floated={false}
        shadow={false}
        color="blue-gray"
        className="relative h-56"
      >
        <img src={image} alt="card-image" />
      </CardHeader>
      <CardBody>
        <Typography variant="h5" color="blue-gray" className="mb-2 text-center">
          {topic}
        </Typography>
        <Typography className={"text-center text-sm"}>
          &quot; {details} &quot;
        </Typography>
      </CardBody>
      <CardFooter className="pt-0 items-center flex justify-center">
        <Button className={"rounded-full bg-secondary hover:bg-primary"}>
          {button}
        </Button>
      </CardFooter>
    </Card>
  );
};

JoinUsCards.propTypes = {
    image: PropTypes.string,
    topic: PropTypes.string,
    details: PropTypes.string,
    button: PropTypes.string,
}
